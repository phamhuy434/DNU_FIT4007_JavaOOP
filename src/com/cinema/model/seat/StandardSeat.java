package com.cinema.model.seat;

public class StandardSeat extends Seat {
    private double basePrice;

    public StandardSeat(String seatId, double basePrice) {
        super(seatId);
        this.basePrice = basePrice;
    }
    @Override public double getPrice() { return basePrice; }
}
