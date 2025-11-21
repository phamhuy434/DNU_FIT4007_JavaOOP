package com.cinema.model;

import com.cinema.exception.NotFoundException;
import com.cinema.exception.SeatAlreadyBookedException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Showtime implements Serializable {

    private String showtimeId;
    private String movieId;
    private String roomId;
    private LocalDate date;
    private LocalTime time;

    private List<String> bookedSeatIds = new ArrayList<>();

    public Showtime(String showtimeId, String movieId, String roomId,
                    LocalDate date, LocalTime time) {
        this.showtimeId = showtimeId;
        this.movieId = movieId;
        this.roomId = roomId;
        this.date = date;
        this.time = time;
    }

    public String getShowtimeId() { return showtimeId; }
    public String getMovieId() { return movieId; }
    public String getRoomId() { return roomId; }
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
    public List<String> getBookedSeatIds() { return bookedSeatIds; }

    // -------------------------------
    // ⭐ Thêm lại hàm BOOK SEAT
    // -------------------------------
    public void bookSeat(String seatId) throws SeatAlreadyBookedException {
        if (bookedSeatIds.contains(seatId)) {
            throw new SeatAlreadyBookedException("Seat " + seatId + " already booked");
        }
        bookedSeatIds.add(seatId);
    }

    // -------------------------------
    //Kiểm tra ghế đã đặt
    // -------------------------------
    public boolean isSeatBooked(String seatId) {
        return bookedSeatIds.contains(seatId);
    }

    // -------------------------------
    //Check trùng suất chiếu
    // -------------------------------
    public boolean isConflict(Showtime other) {
        if (!this.roomId.equals(other.roomId)) return false;
        if (!this.date.equals(other.date)) return false;

        LocalTime thisEnd = this.time.plusHours(2);
        LocalTime otherEnd = other.time.plusHours(2);

        return this.time.isBefore(otherEnd) && other.time.isBefore(thisEnd);
    }
}
