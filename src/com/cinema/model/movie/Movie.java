package com.cinema.model.movie;

import java.io.Serializable;

public abstract class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    private String movieId;
    private String title;
    private String genre;
    private int durationMinutes;
    private int ageRating;
    private String description;

    public Movie(String movieId, String title, String genre, int durationMinutes, int ageRating, String description) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.ageRating = ageRating;
        this.description = description;
    }

    public abstract String getMovieType();

    // --- Getters ---
    public String getMovieId() { return movieId; }
    public String getTitle() { return title; }
    public int getDurationMinutes() { return durationMinutes; }
    public int getAgeRating() { return ageRating; }

    @Override
    public String toString() {
        return String.format("[%s] %s (%d phút) - Giới hạn: %d+", movieId, title, durationMinutes, ageRating);
    }
}