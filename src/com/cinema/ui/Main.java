package com.cinema.ui;

import com.cinema.model.movie.*;
import com.cinema.model.Room;
import com.cinema.model.Showtime;
import com.cinema.model.seat.Seat;
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
    private static BookingService bookingService = new BookingService(showRepo, roomRepo);
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
            System.out.println("5. Đặt vé (có thể đặt nhiều ghế)");
            System.out.println("6. Báo cáo Top 3 doanh thu");
            System.out.println("7. Tìm kiếm phim");
            System.out.println("8. Danh sách phòng và ghế");
            System.out.println("9. Xem ghế đã đặt theo suất chiếu");
            System.out.println("10. Hủy vé");
            System.out.println("11. Xem chi tiết ghế phòng chiếu");
            System.out.println("12. Tổng doanh thu chi tiết");
            System.out.println("13. Lọc phim theo thể loại và độ tuổi");
            System.out.println("0. Thoát");

            System.out.print("Chọn chức năng: ");
            String choice = sc.nextLine();

            try {
                switch (choice) {
                    case "1":
                        movieRepo.findAll().forEach(System.out::println);
                        break;

                    case "2":
                        System.out.print("Tên phim: ");
                        String title = sc.nextLine();
                        Movie m = new FeatureFilm(
                                UUID.randomUUID().toString(),
                                title, "Thể loại", 100, 13, "Mô tả"
                        );
                        movieRepo.add(m);
                        System.out.println("Thêm phim thành công!");
                        break;

                    case "3":
                        showRepo.findAll().forEach(System.out::println);
                        break;

                    case "4":
                        System.out.print("ID phim: ");
                        String mid = sc.nextLine();
                        System.out.print("ID phòng: ");
                        String rid = sc.nextLine();

                        Showtime s = new Showtime(
                                UUID.randomUUID().toString(),
                                mid, rid,
                                LocalDate.now(),
                                LocalTime.of(18, 0)
                        );
                        showRepo.add(s);
                        System.out.println("Tạo suất chiếu thành công!");
                        break;

                    case "5":
                        System.out.print("ID suất chiếu: ");
                        String sh = sc.nextLine();
                        System.out.print("Nhập mã ghế, cách nhau bằng dấu phẩy: ");
                        String[] seats = sc.nextLine().split(",");

                        for (String seatId : seats) {
                            try {
                                bookingService.book(sh, seatId.trim());
                                System.out.println("Đặt ghế " + seatId + " thành công.");
                            } catch (Exception e) {
                                System.out.println("Lỗi đặt ghế " + seatId + ": " + e.getMessage());
                            }
                        }
                        break;

                    case "6":
                        System.out.println("Top 3 doanh thu: " + reportService.top3Movies());
                        break;

                    case "7":
                        System.out.print("Từ khóa: ");
                        String keyword = sc.nextLine().toLowerCase();
                        movieRepo.findAll().stream()
                                .filter(mv -> mv.getTitle().toLowerCase().contains(keyword)
                                        || mv.getGenre().toLowerCase().contains(keyword))
                                .forEach(System.out::println);
                        break;

                    case "8":
                        roomRepo.findAll().forEach(r -> {
                            System.out.println("Phòng " + r.getRoomCode() + " (" + r.getRoomType() + ")");
                            r.getSeats().values().forEach(seat -> {
                                String status = seat.isBooked() ? "[X]" : "[O]";
                                System.out.println("  " + seat.getSeatId() + " " + status + " - " + seat.getPrice() + "k");
                            });
                        });
                        break;

                    case "9":
                        System.out.print("ID suất chiếu: ");
                        String sid9 = sc.nextLine();
                        Showtime st9 = showRepo.findById(sid9);
                        if (st9 == null) {
                            System.out.println("Không tồn tại suất chiếu.");
                            break;
                        }
                        Room rr9 = roomRepo.findById(st9.getRoomId());
                        System.out.println("Ghế phòng " + rr9.getRoomCode() + ":");
                        int count = 0;
                        for (Seat seat : rr9.getSeats().values()) {
                            System.out.print(seat.isBooked() ? "[X]" : "[O]");
                            count++;
                            if (count % 5 == 0) System.out.println();
                        }
                        System.out.println("\n[X] = Đã đặt, [O] = Còn trống");
                        break;

                    case "10":
                        System.out.print("ID suất chiếu: ");
                        String sid10 = sc.nextLine();
                        System.out.print("Mã ghế: ");
                        String seat10 = sc.nextLine();

                        Showtime stCancel = showRepo.findById(sid10);
                        if (stCancel == null) {
                            System.out.println("Suất chiếu không tồn tại.");
                            break;
                        }
                        Room roomCancel = roomRepo.findById(stCancel.getRoomId());
                        if (roomCancel.getSeats().get(seat10) == null) {
                            System.out.println("Ghế không tồn tại.");
                            break;
                        }

                        bookingService.cancel(sid10, seat10);
                        System.out.println("Hủy vé thành công.");
                        break;

                    case "11":
                        System.out.print("ID phòng: ");
                        String rid11 = sc.nextLine();
                        Room room11 = roomRepo.findById(rid11);
                        if (room11 == null) {
                            System.out.println("Không có phòng này!");
                            break;
                        }
                        System.out.println("Ghế phòng " + room11.getRoomCode() + ":");
                        room11.getSeats().values().forEach(seat -> {
                            String status = seat.isBooked() ? "[X]" : "[O]";
                            System.out.println(seat.getSeatId() + " " + status + " - " + seat.getPrice() + "k");
                        });
                        break;

                    case "12":
                        double total = 0;
                        for (Showtime shw : showRepo.findAll()) {
                            Room r = roomRepo.findById(shw.getRoomId());
                            double revenue = r.getSeats().values().stream()
                                    .filter(Seat::isBooked)
                                    .mapToDouble(Seat::getPrice)
                                    .sum();
                            System.out.println("Suất " + shw.getShowtimeId() + " phòng " + r.getRoomCode() + ": " + revenue + "k");
                            total += revenue;
                        }
                        System.out.println("Tổng doanh thu: " + total + "k");
                        break;

                    case "13":
                        System.out.print("Nhập thể loại: ");
                        String g = sc.nextLine().toLowerCase();
                        System.out.print("Độ tuổi tối đa (0 bỏ qua): ");
                        int ageLimit = Integer.parseInt(sc.nextLine());

                        movieRepo.findAll().stream()
                                .filter(mv -> mv.getGenre().toLowerCase().contains(g))
                                .filter(mv -> ageLimit == 0 || mv.getMinAge() <= ageLimit)  // dùng getMinAge()
                                .forEach(System.out::println);
                        break;

                    case "0":
                        System.out.println("Thoát...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Chức năng không hợp lệ.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            System.out.println();
        }
    }

    private static void ensureSampleData() {
        if (movieRepo.findAll().isEmpty()) {
            movieRepo.add(new FeatureFilm("m-1", "Inception", "Sci-Fi", 148, 13, "Dream heist"));
            movieRepo.add(new Animation("m-2", "Doraemon", "Kids", 95, 0, "Kid movie"));
        }

        if (roomRepo.findAll().isEmpty()) {
            Room r = new Room("r-1", "R01", "IMAX");
            for (int i = 1; i <= 20; i++) {
                r.addSeat(new com.cinema.model.seat.StandardSeat("A" + i, 80));
            }
            roomRepo.add(r);
        }

        if (showRepo.findAll().isEmpty()) {
            showRepo.add(new Showtime("s-1", "m-1", "r-1", LocalDate.now(), LocalTime.of(18, 30)));
        }
    }
}
