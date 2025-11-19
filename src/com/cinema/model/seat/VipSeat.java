package com.cinema.model.seat;

public class VipSeat extends Seat {
    private double surcharge;

    public VipSeat(String seatId, double surcharge) {
        super(seatId);
        this.surcharge = surcharge;
    }
    @Override public double getPrice() { return 100 + surcharge; } // sample
}
