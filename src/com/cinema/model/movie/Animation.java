package com.cinema.model.movie;

import com.cinema.model.Movie;

public class Animation extends Movie {

    public Animation(String movieId, String title, String genre, int durationMinutes, int ageLimit, String description) {
        super(movieId, title, "Hoat Hinh", durationMinutes, ageLimit, description);
    }

    @Override
    public double calculateTicketModifier() {
        // Có thể áp dụng hệ số chuẩn hoặc hệ số riêng (tuỳ chính sách)
        return 1.0;
    }

    @Override
    public String toString() {
        return super.toString() + " | Loai: Hoat Hinh";
    }
}