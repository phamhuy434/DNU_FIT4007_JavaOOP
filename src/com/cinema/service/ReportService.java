package com.cinema.service;

import com.cinema.repository.RoomRepository;
import com.cinema.repository.ShowtimeRepository;
import com.cinema.repository.MovieRepository;

public class ReportService {

    private MovieRepository movieRepo = new MovieRepository();
    private ShowtimeRepository showRepo = new ShowtimeRepository();
    private RoomRepository roomRepo = new RoomRepository();

    // Top 3 phim
    public Object top3Movies() {
        return movieRepo.findAll().stream().limit(3).toList();
    }

    // Tổng doanh thu
    public double totalRevenue() {
        return roomRepo.findAll().stream()
                .flatMap(room -> room.getSeats().values().stream())
                .filter(seat -> seat.isBooked())
                .mapToDouble(seat -> seat.getPrice())
                .sum();
    }
}
