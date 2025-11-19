package com.cinema.model.seat;

public class CoupleSeat extends Seat {
    private double priceForTwo;

    public CoupleSeat(String seatId, double priceForTwo) {
        super(seatId);
        this.priceForTwo = priceForTwo;
    }
    @Override public double getPrice() { return priceForTwo; }
}
