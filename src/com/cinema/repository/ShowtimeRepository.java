package com.cinema.repository;

import com.cinema.model.Showtime;
import com.cinema.ui.CsvUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ShowtimeRepository {

    private String csv = "data/showtimes.csv";
    private Map<String, Showtime> map = new LinkedHashMap<>();

    public ShowtimeRepository() {
        load();
    }

    public void load() {
        try {
            List<String[]> rows = CsvUtil.readAll(csv);
            for (String[] r : rows) {
                if (r.length < 5) continue;
                String id = r[0], movieId = r[1], roomId = r[2];
                LocalDate date = LocalDate.parse(r[3]);
                LocalTime time = LocalTime.parse(r[4]);
                Showtime s = new Showtime(id, movieId, roomId, date, time);
                map.put(id, s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        List<String[]> rows = new ArrayList<>();
        for (Showtime s : map.values()) {
            rows.add(new String[]{
                    s.getShowtimeId(),
                    s.getMovieId(),
                    s.getRoomId(),
                    s.getDate().toString(),
                    s.getTime().toString()
            });
        }
        try {
            CsvUtil.writeAll(csv, rows);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(Showtime s) {
        map.put(s.getShowtimeId(), s);
        save();
    }

    public Showtime findById(String id) {
        return map.get(id);
    }

    // --------------------------
    // ⭐ Trả về List để Service dùng được
    // --------------------------
    public List<Showtime> findAll() {
        return new ArrayList<>(map.values());
    }
}
