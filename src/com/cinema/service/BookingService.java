package com.cinema.service;

import com.cinema.exception.NotFoundException;
import com.cinema.exception.SeatAlreadyBookedException;
import com.cinema.model.Room;
import com.cinema.model.Showtime;
import com.cinema.model.Ticket;
import com.cinema.model.movie.Movie;
import com.cinema.model.seat.Seat;
import com.cinema.repository.*;

import java.util.*;

public class BookingService {
    private MovieRepository movieRepo = new MovieRepository();
    private RoomRepository roomRepo = new RoomRepository();
    private ShowtimeRepository showRepo = new ShowtimeRepository();
    private TicketRepository ticketRepo = new TicketRepository();

    public Ticket book(String showtimeId, String seatId) throws Exception {
        Showtime s = showRepo.findById(showtimeId);
        if (s == null) throw new NotFoundException("Showtime not found");
        Room r = roomRepo.findById(s.getRoomId());
        if (r == null) throw new NotFoundException("Room not found");
        Seat seat = r.getSeats().get(seatId);
        if (seat == null) throw new NotFoundException("Seat not found");
        if (seat.isBooked()) throw new SeatAlreadyBookedException("Seat already booked");

        // Calculate price
        double price = seat.getPrice() * (movieRepo.findById(s.getMovieId()).calculateTicketModifier());

        // book in showtime
        s.bookSeat(seatId);
        // mark seat in room
        seat.setStatus(com.cinema.model.seat.SeatStatus.BOOKED);

        Ticket t = new Ticket(showtimeId, s.getMovieId(), r.getRoomId(), seatId, price);
        ticketRepo.add(t);
        showRepo.save();
        roomRepo.save();
        return t;
    }
}
