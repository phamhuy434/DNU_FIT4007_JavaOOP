package com.cinema.model.seat;

public class VipSeat extends Seat {

    public VipSeat(String seatId) {
        super(seatId);
    }

    @Override
    public double getPrice() {
        return 90000;
    }
}