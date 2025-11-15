package com.cinema.model.seat;

import java.io.Serializable;

public abstract class Seat implements Serializable {

    private String seatId;
    private SeatStatus status;

    public Seat(String seatId) {
        this.seatId = seatId;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getSeatId() {
        return seatId;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public boolean isBooked() {
        return this.status == SeatStatus.BOOKED;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    // Abstract -> các lớp con override
    public abstract double getPrice();
}
