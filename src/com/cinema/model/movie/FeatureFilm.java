package com.cinema.model.movie;

public class FeatureFilm extends Movie {
    public FeatureFilm(String movieId, String title, String genre,
                       int durationMinutes, int minAge, String description) {
        super(movieId, title, genre, durationMinutes, minAge, description);
    }
    @Override public String getType() { return "Feature"; }
    @Override public double calculateTicketModifier() { return 1.2; }
    @Override public String toString() { return super.toString() + " | Type: Feature"; }
}
