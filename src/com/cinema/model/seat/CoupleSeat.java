package com.cinema.model.seat;

public class CoupleSeat extends Seat {

    private static final double STANDARD_SEAT_PRICE = 50000.0;
    private static final double COUPLE_SURCHARGE = 20000.0;

    public CoupleSeat(String seatId) {
        super(seatId);
    }

    @Override
    public double getPrice() {
        // Giá trị trả về đã tính cho cả 2 người
        return (STANDARD_SEAT_PRICE * 2) + COUPLE_SURCHARGE;
        // Ví dụ: (50,000 * 2) + 20,000 = 120,000 VND
    }
}