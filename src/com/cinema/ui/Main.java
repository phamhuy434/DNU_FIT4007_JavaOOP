package com.cinema.service;

import com.cinema.model.*;
import com.cinema.model.seat.Seat;
import com.cinema.repo.*;
import com.cinema.exceptions.SeatAlreadyBookedException; // Cần import ngoại lệ

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CinemaService {

    // Khai báo các Repository phụ thuộc
    private final MovieRepository movieRepo;
    private final RoomRepository roomRepo;
    private final ShowtimeRepository showtimeRepo;
    private final TicketRepository ticketRepo;
    private final PaymentRepository paymentRepo;
    private final RevenueRepository revenueRepo;

    // Constructor (Dependency Injection)
    public CinemaService(
            MovieRepository movieRepo,
            RoomRepository roomRepo,
            ShowtimeRepository showtimeRepo,
            TicketRepository ticketRepo,
            PaymentRepository paymentRepo,
            RevenueRepository revenueRepo
    ) {
        this.movieRepo = movieRepo;
        this.roomRepo = roomRepo;
        this.showtimeRepo = showtimeRepo;
        this.ticketRepo = ticketRepo;
        this.paymentRepo = paymentRepo;
        this.revenueRepo = revenueRepo;
    }

    // --- CÁC PHƯƠNG THỨC NGHIỆP VỤ CỐT LÕI ---

    /**
     * Phương thức đặt vé chính
     */
    public Ticket bookTicket(String showtimeId, String seatId) throws SeatAlreadyBookedException, IllegalArgumentException {
        // 1. Tìm suất chiếu và ghế
        Showtime showtime = showtimeRepo.findById(showtimeId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy suất chiếu."));

        Seat seat = showtime.getRoom().getSeatById(seatId);
        if (seat == null) {
            throw new IllegalArgumentException("Không tìm thấy ghế.");
        }

        // 2. Kiểm tra và đặt ghế (Sử dụng logic từ Showtime.java)
        showtime.bookSeat(seat);

        // 3. Tạo đối tượng Ticket
        String ticketId = "T" + (ticketRepo.findAll().size() + 1); // Logic tạo ID
        Ticket newTicket = new Ticket(ticketId, showtime, seat);

        // 4. Lưu Ticket và cập nhật Showtime
        ticketRepo.save(newTicket);
        showtimeRepo.update(showtime); // Cập nhật trạng thái ghế đã đặt

        // 5. Tính toán thanh toán và doanh thu (logic sẽ phức tạp hơn)
        // paymentRepo.processPayment(newTicket);
        // revenueRepo.recordRevenue(newTicket);

        return newTicket;
    }

    /**
     * Phương thức lấy danh sách suất chiếu khả dụng
     */
    public List<Showtime> getAvailableShowtimes() {
        // Giả sử logic lọc suất chiếu
        return showtimeRepo.findAll();
    }

    // ... Thêm các phương thức khác (addMovie, addRoom, getRevenueReport, v.v.)
}