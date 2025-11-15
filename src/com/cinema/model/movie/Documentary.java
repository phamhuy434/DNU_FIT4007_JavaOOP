package com.cinema.model.movie;

public class Documentary extends Movie {

    public Documentary(String movieId, String title, String genre, int durationMinutes, int ageRating, String description) {
        super(movieId, title, genre, durationMinutes, ageRating, description);
    }

    @Override
    public String getMovieType() {
        return "Documentary";
    }
}