package com.cinema.model;

/**
 * Lop chua cac hang so ve gia ve.
 * Giup cac lop Seat tham chieu gia tien de dang hon.
 */
public final class PriceConstants {
    private PriceConstants() {} // Ngăn không cho tạo instance

    // Giá vé cơ bản cho ghế thường
    public static final double BASE_PRICE = 50000.0;

    // Phụ phí cho Ghế VIP (cộng thêm vào BASE_PRICE)
    public static final double VIP_SURCHARGE = 20000.0;

    // Phụ phí cho Ghế Đôi (cộng thêm vào BASE_PRICE * 2)
    public static final double COUPLE_SURCHARGE = 20000.0;
}