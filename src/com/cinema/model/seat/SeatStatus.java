package com.cinema.model.seat;

public enum SeatStatus {
    AVAILABLE, // Trống, sẵn sàng để đặt
    BOOKED,    // Đã được đặt
    RESERVED   // (Tùy chọn) Có thể dùng cho mục đích bảo trì, hoặc đã chọn nhưng chưa thanh toán
}