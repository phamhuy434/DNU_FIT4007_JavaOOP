package com.cinema.model.movie;

import com.cinema.model.Movie;

public class FeatureFilm extends Movie {

    // Có thể thêm thuộc tính riêng (VD: dao dien) nếu cần
    private String director;

    public FeatureFilm(String movieId, String title, String genre, int durationMinutes, int ageLimit, String description, String director) {
        super(movieId, title, genre, durationMinutes, ageLimit, description);
        this.director = director;
    }

    @Override
    public double calculateTicketModifier() {
        // Phim dien anh thuong co he so chuan (khong thay doi gia)
        return 1.0;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Loai: Dien Anh | Dao dien: %s", director);
    }
}