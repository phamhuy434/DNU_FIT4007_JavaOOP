package com.cinema.ui;

import com.cinema.model.movie.*;
import com.cinema.model.Room;
import com.cinema.model.Showtime;
import com.cinema.repository.*;
import com.cinema.service.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.UUID;
import java.time.format.DateTimeParseException;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final MovieRepository movieRepo = new MovieRepository();
    private static final RoomRepository roomRepo = new RoomRepository();
    private static final ShowtimeRepository showRepo = new ShowtimeRepository();
    private static final BookingService bookingService = new BookingService();
    private static final ReportService reportService = new ReportService();

    public static void main(String[] args) {
        ensureSampleData();
        while (true) {
            System.out.println("== Quản Lý Rạp Phim ==");
            System.out.println("1. Danh sách phim");
            System.out.println("2. Thêm phim");
            System.out.println("3. Lịch chiếu phim");
            System.out.println("4. Tạo thời gian chiếu phim");
            System.out.println("5. Đặt vé");
            System.out.println("6. Báo cáo doanh thu( top 3)");
            System.out.println("7. Tìm lịch chiếu theo ngày và phim");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            String cho = sc.nextLine();
            try {
                switch (cho) {
                    case "1":
                        movieRepo.findAll().forEach(System.out::println);
                        break;
                    case "2":
                        System.out.print("Title: "); String title = sc.nextLine();
                        String id = UUID.randomUUID().toString();
                        Movie m = new FeatureFilm(id, title, "Genre", 100, 13, "desc");
                        movieRepo.add(m);
                        System.out.println("Added " + id);
                        break;
                    case "3":
                        showRepo.findAll().forEach(System.out::println);
                        break;
                    case "4":
                        System.out.print("MovieId: "); String mid = sc.nextLine();
                        System.out.print("RoomId: "); String rid = sc.nextLine();
                        String sid = UUID.randomUUID().toString();
                        Showtime s = new Showtime(sid, mid, rid,
                                LocalDate.now(), LocalTime.of(18, 0));
                        showRepo.add(s);
                        System.out.println("Show created: " + sid);
                        break;
                    case "5":
                        System.out.print("ShowtimeId: "); String sh = sc.nextLine();
                        System.out.print("SeatId: "); String seatId = sc.nextLine();
                        bookingService.book(sh, seatId);
                        System.out.println("Booked.");
                        break;
                    case "6":
                        System.out.println("Top3: " + reportService.top3Movies());
                        break;
                    case "7":
                        System.out.print("Nhập Movie ID: ");
                        String searchMovieId = sc.nextLine();
                        System.out.print("Nhập Ngày (YYYY-MM-DD): ");
                        String dateStr = sc.nextLine();

                        try {
                            LocalDate searchDate = LocalDate.parse(dateStr);
                            System.out.println("--- LỊCH CHIẾU NGÀY " + searchDate + " CHO PHIM " + searchMovieId + " ---");

                            showRepo.findAll().stream()
                                    .filter(show -> show.getDate().equals(searchDate) && show.getMovieId().equalsIgnoreCase(searchMovieId))
                                    .forEach(System.out::println);

                        } catch (DateTimeParseException e) {
                            System.out.println("Lỗi: Định dạng ngày không hợp lệ (cần YYYY-MM-DD).");
                        }
                        break;
                    case "0":
                        System.out.println("Save & exit.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            System.out.println();
        }
    }

    // create sample minimal data if none exists
    private static void ensureSampleData() {
        if (movieRepo.findAll().isEmpty()) {
            movieRepo.add(new FeatureFilm("m-1", "Inception", "Sci-Fi", 148, 13, "Dream heist"));
            movieRepo.add(new Animation("m-2", "Doraemon", "Kids", 95, 0, "Kid movie"));
        }
        if (roomRepo.findAll().isEmpty()) {
            Room r = new Room("r-1", "R01", "IMAX");
            // create seats
            for (int i=1;i<=20;i++) r.addSeat(new com.cinema.model.seat.StandardSeat("A"+i, 80));
            roomRepo.add(r);
        }
        if (showRepo.findAll().isEmpty()) {
            showRepo.add(new Showtime("s-1", "m-1", "r-1", LocalDate.now(), LocalTime.of(18,30)));
        }
    }
}
