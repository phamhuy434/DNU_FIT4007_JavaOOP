package com.cinema.repository;

import com.cinema.model.Showtime;
import com.cinema.util.PersistenceUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowtimeRepository {

    private List<Showtime> showtimes;
    private static final String FILE_PATH = "data/showtimes.dat";

    public ShowtimeRepository() {
        // Tải dữ liệu từ file khi khởi tạo
        this.showtimes = PersistenceUtil.load(FILE_PATH);
        if (this.showtimes.isEmpty()) {
            System.out.println("Khoi tao ShowtimeRepository voi danh sach trong.");
        }
    }

    // Ghi tất cả dữ liệu vào file
    private void saveAll() {
        PersistenceUtil.save(showtimes, FILE_PATH);
    }

    // Thêm mới hoặc cập nhật
    public void save(Showtime showtime) {
        // Giả định Showtime đã có ID được sinh ra trong constructor
        if (findById(showtime.getShowtimeId()).isPresent()) {
            // Logic cập nhật: tìm và thay thế (tùy chọn)
        } else {
            showtimes.add(showtime);
        }
        saveAll();
    }

    public Optional<Showtime> findById(String id) {
        return showtimes.stream()
                .filter(s -> s.getShowtimeId().equals(id))
                .findFirst();
    }

    public List<Showtime> findAll() {
        return new ArrayList<>(showtimes);
    }

    // Tùy chọn: Phương thức tìm suất chiếu theo phòng (cần cho logic trùng lịch)
    public List<Showtime> findByRoomId(String roomId) {
        return showtimes.stream()
                .filter(s -> s.getRoom().getRoomId().equals(roomId))
                .toList();
    }

    public boolean delete(String id) {
        boolean removed = showtimes.removeIf(s -> s.getShowtimeId().equals(id));
        if (removed) {
            saveAll();
        }
        return removed;
    }
}