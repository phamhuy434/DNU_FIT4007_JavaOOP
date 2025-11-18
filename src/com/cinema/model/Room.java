package com.cinema.model;

import com.cinema.model.seat.Seat;
import com.cinema.model.seat.StandardSeat;
import com.cinema.model.seat.VipSeat;
import com.cinema.model.seat.CoupleSeat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable {

    // Enum cho loại phòng chiếu
    public enum RoomType {
        STANDARD, VIP, IMAX
    }

    private final String roomId;
    private final RoomType type;
    private final int totalRows;
    private final int totalCols;
    private List<Seat> seats; // Danh sách các đối tượng ghế

    public Room(String roomId, RoomType type, int totalRows, int totalCols) {
        this.roomId = roomId;
        this.type = type;
        this.totalRows = totalRows;
        this.totalCols = totalCols;
        this.seats = new ArrayList<>();
        // Khởi tạo sơ đồ ghế ngay khi phòng được tạo
        initializeSeats();
    }

    // --- Getters ---
    public String getRoomId() { return roomId; }
    public RoomType getType() { return type; }
    public List<Seat> getSeats() { return seats; }

    // --- Logic khởi tạo sơ đồ ghế (có thể tùy chỉnh) ---
    private void initializeSeats() {
        char currentRow = 'A';
        for (int i = 0; i < totalRows; i++) {
            for (int j = 1; j <= totalCols; j++) {
                String rowName = String.valueOf(currentRow);
                String seatId = this.roomId + "-" + rowName + j;
                Seat newSeat;

                // Logic phân loại ghế theo hàng và cột (Ví dụ)
                if (rowName.equals("A")) {
                    newSeat = new StandardSeat(seatId, rowName, j); // Hàng A thường
                } else if (rowName.equals("C") && j >= 4 && j <= 7) {
                    newSeat = new VipSeat(seatId, rowName, j); // Ghế VIP ở vị trí tốt
                } else if (rowName.equals("D") && j == totalCols) {
                    newSeat = new CoupleSeat(seatId, rowName, j); // Ghế đôi cuối góc
                } else {
                    newSeat = new StandardSeat(seatId, rowName, j); // Ghế thường mặc định
                }
                this.seats.add(newSeat);
            }
            currentRow++; // Chuyển sang hàng tiếp theo (B, C, D...)
        }
    }

    @Override
    public String toString() {
        return String.format("Phòng %s (%s) - So luong ghe: %d", roomId, type, seats.size());
    }
}