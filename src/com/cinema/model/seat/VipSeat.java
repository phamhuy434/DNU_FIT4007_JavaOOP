package com.cinema.model.seat;

import com.cinema.model.PriceConstants;

public class VipSeat extends Seat {

    // Constructor đã cập nhật
    public VipSeat(String seatId, String row, int number) {
        super(seatId, row, number);
    }

    @Override
    public double getPrice() {
        // Giá VIP = Giá Cơ bản + Phụ phí VIP
        return PriceConstants.BASE_PRICE + PriceConstants.VIP_SURCHARGE;
    }

    @Override
    public String toString() {
        return String.format("Ghế VIP [%s%d - Giá: %.0f VND, Trạng thái: %s]",
                getRow(), getNumber(), getPrice(), getStatus());
    }
}