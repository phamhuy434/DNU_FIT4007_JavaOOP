package com.cinema.repository;

import com.cinema.model.Room;
import com.cinema.ui.CsvUtil;

import java.io.IOException;
import java.util.*;

public class RoomRepository {
    private String csv = "data/rooms.csv";
    private Map<String, Room> map = new LinkedHashMap<>();

    public RoomRepository() { load(); }

    public void load() {
        try {
            List<String[]> rows = CsvUtil.readAll(csv);
            for (String[] r : rows) {
                if (r.length < 3) continue;
                String id = r[0], code = r[1], type = r[2];
                Room room = new Room(id, code, type);
                map.put(id, room);
            }
        } catch (IOException e) {}
    }

    public void save() {
        List<String[]> rows = new ArrayList<>();
        for (Room r : map.values()) {
            rows.add(new String[] { r.getRoomId(), r.getRoomCode(), r.getRoomType() });
        }
        try { CsvUtil.writeAll(csv, rows); } catch (IOException e) { e.printStackTrace(); }
    }

    public void add(Room r) { map.put(r.getRoomId(), r); save(); }
    public Room findById(String id) { return map.get(id); }
    public Collection<Room> findAll() { return map.values(); }
}
