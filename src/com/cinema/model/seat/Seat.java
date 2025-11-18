package com.cinema.model.seat;

import java.io.Serializable;

public abstract class Seat implements Serializable {

    private String seatId;
    private SeatStatus status;
    private String row;     // Ví dụ: 'A', 'B', 'C'
    private int number;     // Ví dụ: 1, 2, 3

    // Constructor đã cập nhật để bao gồm vị trí ghế
    public Seat(String seatId, String row, int number) {
        this.seatId = seatId;
        this.status = SeatStatus.AVAILABLE;
        this.row = row;
        this.number = number;
    }

    // --- Getters ---
    public String getSeatId() {
        return seatId;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public String getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    // --- Setters / Utility ---
    public boolean isBooked() {
        return this.status == SeatStatus.BOOKED;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    // Phương thức trừu tượng (đa hình)
    public abstract double getPrice();

    // Phương thức toString cơ bản
    @Override
    public String toString() {
        return String.format("%s%d - Trang thai: %s", row, number, status);
    }
}