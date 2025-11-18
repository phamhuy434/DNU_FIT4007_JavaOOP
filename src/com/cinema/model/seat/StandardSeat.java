package com.cinema.model.seat;

import com.cinema.model.PriceConstants;

public class StandardSeat extends Seat {

    // Constructor đã cập nhật
    public StandardSeat(String seatId, String row, int number) {
        super(seatId, row, number);
    }

    @Override
    public double getPrice() {
        // Tham chiếu giá cơ bản từ PriceConstants
        return PriceConstants.BASE_PRICE;
    }

    @Override
    public String toString() {
        return String.format("Ghế Thường [%s%d - Giá: %.0f VND, Trạng thái: %s]",
                getRow(), getNumber(), getPrice(), getStatus());
    }
}