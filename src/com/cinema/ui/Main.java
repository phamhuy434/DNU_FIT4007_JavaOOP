package com.cinema.ui;

import com.cinema.model.movie.FeatureFilm;
import com.cinema.service.MovieService;

public class Main {
    public static void main(String[] args) {

        MovieService movieService = new MovieService();

        movieService.addMovie(
                new FeatureFilm("M01", "Inception", "Sci-Fi", 148, 13, "Dream heist")
        );

        System.out.println("Danh sách phim:");
        movieService.getAll().forEach(System.out::println);
    }
}
