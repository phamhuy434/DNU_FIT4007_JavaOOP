package com.cinema.model;

import com.cinema.model.seat.Seat; // FIX: Import lớp Seat
import com.cinema.model.seat.VipSeat; // FIX: Import lớp VipSeat
import com.cinema.model.seat.StandardSeat; // FIX: Import lớp StandardSeat
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import com.cinema.model.seat.Seat;
import com.cinema.model.seat.VipSeat;
import com.cinema.model.seat.StandardSeat;

public class Room implements Serializable {

    // Đảm bảo các trường này được khai báo ở đây hoặc ở trên constructor (nếu bị thiếu)
    private String roomId;
    private String type;
    private List<Seat> seats;

    // Constructor và logic thêm ghế (đã có trong ảnh)

    // PHẢI CÓ: Getter cho ID phòng (Ticket cần)
    public String getRoomId() {
        return roomId;
    }

    // PHẢI CÓ: Phương thức tìm ghế (Repository cần)
    public Seat getSeatById(String id) {
        return seats.stream()
                .filter(s -> s.getSeatId().equals(id))
                .findFirst()
                .orElse(null);
    }
}