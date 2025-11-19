package com.cinema.model;

import com.cinema.model.seat.Seat;
import com.cinema.model.seat.SeatStatus;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Room implements Serializable {
    private String roomId;
    private String roomCode;
    private String roomType; // NORMAL, VIP, IMAX
    private Map<String, Seat> seats = new LinkedHashMap<>();

    public Room(String roomId, String roomCode, String roomType) {
        this.roomId = roomId;
        this.roomCode = roomCode;
        this.roomType = roomType;
    }

    public String getRoomId() { return roomId; }
    public String getRoomCode() { return roomCode; }
    public String getRoomType() { return roomType; }

    public Map<String, Seat> getSeats() { return seats; }

    public void addSeat(Seat seat) { seats.put(seat.getSeatId(), seat); }

    public void resetSeatsToAvailable() {
        seats.values().forEach(s -> s.setStatus(SeatStatus.AVAILABLE));
    }
}
