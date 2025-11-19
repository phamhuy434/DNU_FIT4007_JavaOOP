package com.cinema.repository;

import com.cinema.model.movie.*;
import com.cinema.ui.CsvUtil;

import java.io.IOException;
import java.util.*;

public class MovieRepository {
    private String csv = "data/movies.csv";
    private Map<String, Movie> map = new LinkedHashMap<>();

    public MovieRepository() { load(); }

    public void load() {
        try {
            List<String[]> rows = CsvUtil.readAll(csv);
            for (String[] r : rows) {
                if (r.length < 6) continue;
                String id = r[0], title = r[1], genre = r[2];
                int duration = Integer.parseInt(r[3]);
                int minAge = Integer.parseInt(r[4]);
                String desc = r[5];
                String type = r.length > 6 ? r[6] : "Feature";
                Movie m;
                switch (type) {
                    case "Animation": m = new Animation(id, title, genre, duration, minAge, desc); break;
                    case "Documentary": m = new Documentary(id, title, genre, duration, minAge, desc); break;
                    default: m = new FeatureFilm(id, title, genre, duration, minAge, desc);
                }
                map.put(id, m);
            }
        } catch (IOException e) { /* ignore */ }
    }

    public void save() {
        List<String[]> rows = new ArrayList<>();
        for (Movie m : map.values()) {
            rows.add(new String[] {
                    m.getMovieId(),
                    m.getTitle(),
                    m.getGenre(),
                    Integer.toString(m.getDurationMinutes()),
                    Integer.toString(m.getMinAge()),
                    m.getDescription(),
                    m.getType()
            });
        }
        try { CsvUtil.writeAll(csv, rows); } catch (IOException e) { e.printStackTrace(); }
    }

    public void add(Movie m) { map.put(m.getMovieId(), m); save(); }
    public Movie findById(String id) { return map.get(id); }
    public Collection<Movie> findAll() { return map.values(); }
    public void delete(String id) { map.remove(id); save(); }
}
