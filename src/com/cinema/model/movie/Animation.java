package com.cinema.model.movie;

public class Animation extends Movie {
    public Animation(String movieId, String title, String genre,
                     int durationMinutes, int minAge, String description) {
        super(movieId, title, genre, durationMinutes, minAge, description);
    }
    @Override public String getType() { return "Animation"; }
    @Override public double calculateTicketModifier() { return 1.0; }
    @Override public String toString() { return super.toString() + " | Type: Animation"; }
}
