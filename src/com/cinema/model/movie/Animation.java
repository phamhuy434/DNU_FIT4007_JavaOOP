package com.cinema.model.movie;

public class Animation extends Movie {

    public Animation(String movieId, String title, String genre, int durationMinutes, int ageRating, String description) {
        super(movieId, title, genre, durationMinutes, ageRating, description);
    }

    @Override
    public String getMovieType() {
        return "Animation";
    }
}