package com.cinema.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket implements Serializable {
    private String ticketId;
    private String showtimeId;
    private String movieId;
    private String roomId;
    private String seatId;
    private double price;
    private LocalDateTime bookedAt;
    private String status; // PAID, PENDING

    public Ticket(String showtimeId, String movieId, String roomId, String seatId, double price) {
        this.ticketId = UUID.randomUUID().toString();
        this.showtimeId = showtimeId;
        this.movieId = movieId;
        this.roomId = roomId;
        this.seatId = seatId;
        this.price = price;
        this.bookedAt = LocalDateTime.now();
        this.status = "PAID";
    }

    // getters
    public String getTicketId() { return ticketId; }
    public String getSeatId() { return seatId; }
    public double getPrice() { return price; }

    public String getMovieId() { return movieId; }
    public String getShowtimeId() { return showtimeId; }
    public String getRoomId() { return roomId; }
    public LocalDateTime getBookedAt() { return bookedAt; }
    public String getStatus() { return status; }
}
