package com.cinema.service;

import com.cinema.model.movie.Movie;
import java.util.*;

public class MovieService {

    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie m) {
        movies.add(m);
    }

    public List<Movie> getAll() {
        return movies;
    }
}
