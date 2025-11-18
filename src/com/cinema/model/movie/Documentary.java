package com.cinema.model.movie;

import com.cinema.model.Movie;

public class Documentary extends Movie {

    private static final double DISCOUNT_RATE = 0.9; // Giam 10%

    public Documentary(String movieId, String title, String genre, int durationMinutes, int ageLimit, String description) {
        super(movieId, title, "Tai Lieu", durationMinutes, ageLimit, description);
    }

    @Override
    public double calculateTicketModifier() {
        // Ap dung he so giam gia 10%
        return DISCOUNT_RATE;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Loai: Tai Lieu (Giam 10%%)");
    }
}