package com.cinema.model.movie;

import java.io.Serializable;

public abstract class Movie implements Serializable {
    protected String movieId;
    protected String title;
    protected String genre;
    protected int durationMinutes;
    protected int minAge;
    protected String description;

    public Movie(String movieId, String title, String genre,
                 int durationMinutes, int minAge, String description) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.minAge = minAge;
        this.description = description;
    }

    public String getMovieId() { return movieId; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getDurationMinutes() { return durationMinutes; }
    public int getMinAge() { return minAge; }
    public String getDescription() { return description; }

    public abstract String getType();
    public double calculateTicketModifier() { return 1.0; }

    @Override
    public String toString() {
        return movieId + " | " + title + " | " + genre + " | " + durationMinutes + "m";
    }
}
