package com.cinema.repository;

import com.cinema.model.Ticket;
import com.cinema.ui.CsvUtil;

import java.io.IOException;
import java.util.*;

public class TicketRepository {
    private String csv = "data/tickets.csv";
    private Map<String, Ticket> map = new LinkedHashMap<>();

    public TicketRepository() { load(); }

    public void load() {
        try {
            List<String[]> rows = CsvUtil.readAll(csv);
            for (String[] r : rows) {
                if (r.length < 6) continue;
                // For simplicity we do not fully reconstruct bookedAt time here
                Ticket t = new Ticket(r[1], r[2], r[3], r[4], Double.parseDouble(r[5]));
                map.put(t.getTicketId(), t);
            }
        } catch (IOException e) {}
    }

    public void save() {
        List<String[]> rows = new ArrayList<>();
        for (Ticket t : map.values()) {
            rows.add(new String[] {
                    t.getTicketId(),
                    t.getShowtimeId(),
                    t.getMovieId(),
                    t.getRoomId(),
                    t.getSeatId(),
                    Double.toString(t.getPrice())
            });
        }
        try { CsvUtil.writeAll(csv, rows); } catch (IOException e) { e.printStackTrace(); }
    }

    public void add(Ticket t) { map.put(t.getTicketId(), t); save(); }
    public Collection<Ticket> findAll() { return map.values(); }
}
