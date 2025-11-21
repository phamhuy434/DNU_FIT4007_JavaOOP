package com.cinema.service;

import com.cinema.model.Room;
import com.cinema.model.Showtime;
import com.cinema.repository.RoomRepository;
import com.cinema.repository.ShowtimeRepository;

public class BookingService {

    private final ShowtimeRepository showRepo;
    private final RoomRepository roomRepo;

    // Constructor đúng chuẩn
    public BookingService(ShowtimeRepository showRepo, RoomRepository roomRepo) {
        this.showRepo = showRepo;
        this.roomRepo = roomRepo;
    }

    // Đặt vé
    public void book(String showId, String seatId) {
        Showtime s = showRepo.findById(showId);
        if (s == null) throw new RuntimeException("Không tồn tại suất chiếu");

        Room r = roomRepo.findById(s.getRoomId());
        if (r == null) throw new RuntimeException("Không tồn tại phòng");

        var seat = r.getSeats().get(seatId);
        if (seat == null) throw new RuntimeException("Không có mã ghế");
        if (seat.isBooked()) throw new RuntimeException("Ghế đã được đặt!");

        seat.setBooked(true);
    }

    // Hủy vé
    public void cancel(String showId, String seatId) {
        Showtime s = showRepo.findById(showId);
        if (s == null) throw new RuntimeException("Không tồn tại suất chiếu");

        Room r = roomRepo.findById(s.getRoomId());
        if (r == null) throw new RuntimeException("Không tồn tại phòng");

        var seat = r.getSeats().get(seatId);
        if (seat == null) throw new RuntimeException("Không có mã ghế");
        if (!seat.isBooked()) throw new RuntimeException("Ghế chưa được đặt!");

        seat.setBooked(false);
    }
}
