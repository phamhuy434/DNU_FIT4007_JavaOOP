package com.cinema.model.showtime;

import com.cinema.model.movie.Movie;
import com.cinema.model.seat.*;

import java.io.Serializable;


public class Showtime implements Serializable {

    private String showtimeId;
    private Movie movie;
    private Seat[] seats; // Ví dụ 50 ghế trong phòng

    public Showtime(String showtimeId, Movie movie, int seatCount) {
        this.showtimeId = showtimeId;
        this.movie = movie;
        this.seats = new Seat[seatCount];

        // Khởi tạo ghế mặc định (STANDARD)
        for (int i = 0; i < seatCount; i++) {
            seats[i] = new StandardSeat("S" + (i + 1));
        }
    }

    public Seat getSeatById(String seatId) throws Exception {
        for (Seat seat : seats) {
            if (seat.getSeatId().equalsIgnoreCase(seatId)) {
                return seat;
            }
        }
        throw new Exception("Seat not found");
    }

    public boolean bookSeat(String seatId) throws Exception {
        Seat seat = getSeatById(seatId);

        if (seat.isBooked()) {
            return false; // Ghế đã được đặt
        }

        seat.setStatus(SeatStatus.BOOKED);
        return true;
    }

    public void displaySeats() {
        for (Seat seat : seats) {
            System.out.println(seat.getSeatId() + " - " +
                    (seat.isBooked() ? "BOOKED" : "AVAILABLE"));
        }
    }
}
