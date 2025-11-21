package com.cinema.service;

import com.cinema.model.Showtime;
import com.cinema.repository.ShowtimeRepository;

import java.util.List;

import com.cinema.exception.NotFoundException;
public class ShowtimeService {

    private final ShowtimeRepository showtimeRepository;

    public ShowtimeService(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    public boolean addNewShowtime(Showtime newShowtime) {

        List<Showtime> allShowtimes = showtimeRepository.findAll();

        for (Showtime existing : allShowtimes) {
            if (newShowtime.isConflict(existing)) {
                System.err.println("Lỗi: Suất chiếu trùng lịch!");
                return false;
            }
        }

        showtimeRepository.add(newShowtime);
        return true;
    }

    public List<Showtime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }
}