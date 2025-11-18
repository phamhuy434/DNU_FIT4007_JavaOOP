package com.cinema.model.movie;

import java.io.Serializable;

public abstract class Movie implements Serializable {

    protected String movieId;
    protected String title;
    protected String genre;
    protected int durationMinutes;
    protected int ageLimit;
    protected String description;

    public Movie(String movieId, String title, String genre, int durationMinutes, int ageLimit, String description) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.ageLimit = ageLimit;
        this.description = description;
    }

    // --- Getters Hoàn Chỉnh (Cần thiết để các lớp khác truy cập) ---
    public String getMovieId() { return movieId; }
    public String getTitle() { return title; } // FIX LỖI "Cannot resolve method 'getTitle'"
    public String getGenre() { return genre; }
    public int getDurationMinutes() { return durationMinutes; } // FIX LỖI "Cannot resolve method 'getDurationMinutes'"
    public int getAgeLimit() { return ageLimit; }
    public String getDescription() { return description; }

    // --- Phương thức Trừu tượng (Đa hình) ---
    public abstract double calculateTicketModifier();

    @Override
    public String toString() {
        return String.format("ID: %s | Ten: %s | Thoi luong: %d phut | Gioi han tuoi: %d",
                movieId, title, durationMinutes, ageLimit);
    }
}