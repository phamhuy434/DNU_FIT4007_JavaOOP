package com.cinema.model;

import com.cinema.model.seat.Seat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket implements Serializable {

    private final String ticketId;
    private final Showtime showtime; // Vé thuộc suất chiếu nào
    private final Seat seat;         // Ghế nào đã được đặt
    private final double price;      // Giá vé cuối cùng
    private final LocalDateTime bookingTime; // Thời điểm đặt vé

    /**
     * Constructor tạo vé mới
     * @param showtime Suất chiếu
     * @param seat Ghế được chọn
     * @param price Giá vé tính theo loại ghế
     */
    public Ticket(Showtime showtime, Seat seat, double price) {
        this.ticketId = UUID.randomUUID().toString().substring(0, 8);
        this.showtime = showtime;
        this.seat = seat;
        this.price = price;
        this.bookingTime = LocalDateTime.now(); // Ghi nhận thời điểm tạo vé
    }

    // --- Getters ---
    public String getTicketId() { return ticketId; }
    public Showtime getShowtime() { return showtime; }
    public Seat getSeat() { return seat; }
    public double getPrice() { return price; }
    public LocalDateTime getBookingTime() { return bookingTime; }

    // --- Hiển thị thông tin ---
    @Override
    public String toString() {
        return String.format("VE %s | %s | Ghe: %s%d (%s) | Gia: %.0f VND",
                ticketId,
                showtime.getMovie().getTitle(),
                seat.getRow(), seat.getNumber(), seat.getClass().getSimpleName().replace("Seat", ""),
                price);
    }
}