package com.cinema.model.seat;

// Không cần import thêm vì Seat nằm cùng gói

public class StandardSeat extends Seat {

    // Lỗi 'Cannot resolve method super' sẽ được giải quyết khi Seat.java có constructor Seat(String seatId)
    public StandardSeat(String seatId) {
        super(seatId);
    }

    // Lỗi cú pháp và @Override được giải quyết khi Seat.java có abstract double getPrice()
    @Override
    public double getPrice() {
        return 50000; // FIX: Thiếu dấu chấm phẩy và sai cú pháp (đã sửa)
    }
}