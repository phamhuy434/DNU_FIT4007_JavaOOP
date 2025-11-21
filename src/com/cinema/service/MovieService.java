package com.cinema.service;

import com.cinema.model.movie.Movie;
import com.cinema.repository.MovieRepository;

import java.util.Collection;
import java.util.stream.Collectors;

public class MovieService {
    private MovieRepository repo = new MovieRepository();

    public void addMovie(Movie m) { repo.add(m); }

    public Movie findById(String id) { return repo.findById(id); }

    public Collection<Movie> getAll() { return repo.findAll(); }

    // Lọc phim theo độ tuổi tối đa
    public Collection<Movie> filterByAge(int maxAge) {
        return repo.findAll().stream()
                .filter(m -> m.getMinAge() <= maxAge)  // ✅ dùng getMinAge()
                .collect(Collectors.toList());
    }

    // Lọc phim theo thể loại
    public Collection<Movie> filterByGenre(String genre) {
        return repo.findAll().stream()
                .filter(m -> m.getGenre().toLowerCase().contains(genre.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Lọc theo thể loại + độ tuổi cùng lúc
    public Collection<Movie> filterByGenreAndAge(String genre, int maxAge) {
        return repo.findAll().stream()
                .filter(m -> m.getGenre().toLowerCase().contains(genre.toLowerCase()))
                .filter(m -> maxAge == 0 || m.getMinAge() <= maxAge)
                .collect(Collectors.toList());
    }
}
