package com.cinema.service;

import com.cinema.model.movie.Movie;
import com.cinema.repository.MovieRepository;

import java.util.Collection;

public class MovieService {
    private MovieRepository repo = new MovieRepository();

    public void addMovie(Movie m) { repo.add(m); }
    public Movie findById(String id) { return repo.findById(id); }
    public Collection<Movie> getAll() { return repo.findAll(); }
}
