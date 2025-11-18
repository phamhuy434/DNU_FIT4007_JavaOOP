package com.cinema.model;

import com.cinema.model.movie.Movie; // Import lớp Movie
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Showtime implements Serializable {

    private final String showtimeId;
    private final Movie movie;
    private final Room room;
    private final LocalDateTime startTime;
    private LocalDateTime endTime;

    public Showtime(Movie movie, Room room, LocalDateTime startTime) {
        this.showtimeId = UUID.randomUUID().toString().substring(0, 8);
        this.movie = movie;
        this.room = room;
        this.startTime = startTime;

        // Dòng này đã được sửa bằng cách gọi public getter
        int totalTime = movie.getDurationMinutes() + 15;
        this.endTime = startTime.plus(totalTime, ChronoUnit.MINUTES);
    }

    // --- Getters ---
    public String getShowtimeId() { return showtimeId; }
    public Movie getMovie() { return movie; }
    public Room getRoom() { return room; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isConflict(Showtime other) {
        if (!this.room.getRoomId().equals(other.room.getRoomId())) {
            return false;
        }
        return this.startTime.isBefore(other.endTime) && this.endTime.isAfter(other.startTime);
    }

    @Override
    public String toString() {
        return String.format("Suat %s | Phim: %s | Phong: %s | Bat dau: %s - Ket thuc: %s",
                showtimeId, movie.getTitle(), room.getRoomId(),
                startTime.toLocalTime(), endTime.toLocalTime());
    }
}