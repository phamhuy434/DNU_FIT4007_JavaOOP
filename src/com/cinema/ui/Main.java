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

public class Main {
    private static MovieRepository movieRepo = new MovieRepository();
    private static RoomRepository roomRepo = new RoomRepository();
    private static ShowtimeRepository showRepo = new ShowtimeRepository();
    private static BookingService bookingService = new BookingService();
    private static ReportService reportService = new ReportService();

    public static void main(String[] args) {
        ensureSampleData();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("== HỆ THỐNG QUẢN LÝ RẠP CHIẾU PHIM ==");
            System.out.println("1. Danh sách phim");
            System.out.println("2. Thêm phim mới");
            System.out.println("3. Danh sách suất chiếu");
            System.out.println("4. Tạo suất chiếu");
            System.out.println("5. Đặt vé");
            System.out.println("6. Báo cáo (Top 3 doanh thu)");
            System.out.println("0. Thoát chương trình");
            System.out.print("Chọn chức năng: ");
            String cho = sc.nextLine();
            try {
                switch (cho) {
                    case "1":
                        System.out.println("=== DANH SÁCH PHIM ===");
                        movieRepo.findAll().forEach(System.out::println);
                        break;
                    case "2":
                        System.out.println("=== THÊM PHIM MỚI ===");
                        System.out.print("Tên phim: ");
                        String title = sc.nextLine();
                        String id = UUID.randomUUID().toString();
                        Movie m = new FeatureFilm(id, title, "Thể loại", 100, 13, "Mô tả");
                        movieRepo.add(m);
                        System.out.println("Thêm phim thành công" + id);
                        break;
                    case "3":
                        System.out.println("=== DANH SÁCH SUẤT CHIẾU ===");
                        showRepo.findAll().forEach(System.out::println);
                        break;
                    case "4":
                        System.out.println("=== TẠO SUẤT CHIẾU ===");
                        System.out.print("ID phim: ");
                        String mid = sc.nextLine();
                        System.out.print("ID phòng chiếu: ");
                        String rid = sc.nextLine();
                        String sid = UUID.randomUUID().toString();
                        Showtime s = new Showtime(sid, mid, rid,
                                LocalDate.now(), LocalTime.of(18, 0));
                        showRepo.add(s);
                        System.out.println("Tạo suất chiếu thành công: " + sid);
                        break;
                    case "5":
                        System.out.println("=== ĐẶT VÉ ===");
                        System.out.print("ID suất chiếu: ");
                        String sh = sc.nextLine();
                        System.out.print("Mã ghế: ");
                        String seatId = sc.nextLine();
                        bookingService.book(sh, seatId);
                        System.out.println("Đặt vé thành công.");
                        break;
                    case "6":
                        System.out.println("=== TOP 3 PHIM DOANH THU CAO NHẤT ===");
                        System.out.println("Top3: " + reportService.top3Movies());
                        break;
                    case "0":
                        System.out.println("Đang lưu dữ liệu và thoát...");
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
