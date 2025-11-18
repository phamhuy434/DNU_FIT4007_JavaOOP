package com.cinema.repository;

import com.cinema.model.Room;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// TODO: Import File I/O Class sau khi tạo

public class RoomRepository {

    private List<Room> rooms;
    private static final String FILE_PATH = "data/rooms.dat";

    public RoomRepository() {
        // Tải dữ liệu từ file khi khởi tạo
        this.rooms = loadFromFile();
        if (this.rooms.isEmpty()) {
            initializeDefaultRooms(); // Nếu không có dữ liệu, tạo phòng mặc định
        }
    }

    // --- Các hàm CRUD cơ bản ---
    public void save(Room room) {
        // ... (Logic thêm hoặc cập nhật)
    }

    public Optional<Room> findById(String id) {
        return rooms.stream()
                .filter(r -> r.getRoomId().equals(id))
                .findFirst();
    }

    public List<Room> findAll() {
        return new ArrayList<>(rooms);
    }

    // --- Logic Lưu trữ và Tải ---
    private List<Room> loadFromFile() {
        // TODO: Triển khai logic đọc file (Sử dụng File I/O hoặc Serializable)
        return new ArrayList<>();
    }

    public void saveToFile() {
        // TODO: Triển khai logic ghi dữ liệu vào FILE_PATH
    }

    // --- Khởi tạo dữ liệu mẫu ---
    private void initializeDefaultRooms() {
        // Thêm phòng mẫu để thử nghiệm
        rooms.add(new Room("R01", Room.RoomType.STANDARD, 5, 10)); // 50 ghế
        rooms.add(new Room("R02", Room.RoomType.VIP, 4, 8)); // 32 ghế
        saveToFile(); // Lưu ngay sau khi khởi tạo
    }
}