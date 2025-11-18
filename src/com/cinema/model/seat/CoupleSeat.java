package com.cinema.model.seat;

import com.cinema.model.PriceConstants;

public class CoupleSeat extends Seat {

    // Constructor đã cập nhật
    public CoupleSeat(String seatId, String row, int number) {
        super(seatId, row, number);
    }

    @Override
    public double getPrice() {
        // Giá Ghế Đôi = (Giá cơ bản * 2) + Phụ phí đôi
        return (PriceConstants.BASE_PRICE * 2) + PriceConstants.COUPLE_SURCHARGE;
    }

    @Override
    public String toString() {
        return String.format("Ghế Đôi [%s%d - Giá: %.0f VND, Trạng thái: %s]",
                getRow(), getNumber(), getPrice(), getStatus());
    }
}