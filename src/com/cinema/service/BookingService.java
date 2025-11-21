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
    public String printInvoice(String ticketId) throws Exception {
        Ticket t = ticketRepo.findById(ticketId);
        if (t == null) throw new Exception("Ticket not found");

        Showtime s = showtimeRepo.findById(t.getShowtimeId());
        Movie m = movieRepo.findById(s.getMovieId());
        Room r = roomRepo.findById(s.getRoomId());

        StringBuilder bill = new StringBuilder();

        bill.append("\n========== CINEMA INVOICE ==========\n");
        bill.append("Ticket ID  : ").append(t.getId()).append("\n");
        bill.append("Movie      : ").append(m.getTitle()).append("\n");
        bill.append("Room       : ").append(r.getName()).append("\n");
        bill.append("Seat       : ").append(t.getSeatId()).append("\n");
        bill.append("Date       : ").append(s.getDate()).append("\n");
        bill.append("Time       : ").append(s.getTime()).append("\n");
        bill.append("-------------------------------------\n");
        bill.append("Price      : ").append(t.getPrice()).append(" VND\n");
        bill.append("Paid by    : ").append(t.getPayment().getMethod()).append("\n");
        bill.append("=====================================\n");

        return bill.toString();
    }

}
