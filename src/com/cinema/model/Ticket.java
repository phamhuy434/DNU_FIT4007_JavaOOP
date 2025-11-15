package com.cinema.model;

import com.cinema.model.seat.Seat;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    private String ticketId;
    private Showtime showtime;
    private Seat seat;
    private double price;
    private LocalDateTime bookingTime;

    public Ticket(String ticketId, Showtime showtime, Seat seat) {
        this.ticketId = ticketId;
        this.showtime = showtime;
        this.seat = seat;

        this.price = seat.getPrice();
        this.bookingTime = LocalDateTime.now();
    }

    // --- Getters ---
    public double getPrice() { return price; }
    // ... (các getters khác)

    @Override
    public String toString() {
        // Mã này đã hoạt động sau khi sửa getMovie/getRoom/getRoomId
        return String.format("Mã vé: %s | Phim: %s | Phòng: %s | Ghế: %s | Giá: %.0f VNĐ",
                ticketId,
                showtime.getMovie().getTitle(),
                showtime.getRoom().getRoomId(),
                seat.getSeatId(),
                price);
    }
}