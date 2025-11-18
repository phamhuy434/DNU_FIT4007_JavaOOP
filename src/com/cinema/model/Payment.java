package com.cinema.model;

import com.cinema.interfaces.Billable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Payment implements Serializable, Billable {

    private final String paymentId;
    private final List<Ticket> tickets; // Thanh toán có thể gồm nhiều vé
    private final LocalDateTime paymentTime;
    private final double totalAmount;

    public Payment(List<Ticket> tickets) {
        this.paymentId = UUID.randomUUID().toString().substring(0, 8);
        this.tickets = tickets;
        this.paymentTime = LocalDateTime.now();
        // Tính toán tổng tiền ngay khi tạo Payment
        this.totalAmount = calculateTotalAmount();
    }

    // --- Getters ---
    public String getPaymentId() { return paymentId; }
    public List<Ticket> getTickets() { return tickets; }
    public LocalDateTime getPaymentTime() { return paymentTime; }
    public double getTotalAmount() { return totalAmount; }

    // --- Triển khai Interface Billable ---

    @Override
    public double calculateTotalAmount() {
        // Dùng Stream API để tính tổng giá vé từ danh sách vé
        return tickets.stream().mapToDouble(Ticket::getPrice).sum();
    }

    @Override
    public String generateBillDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- HOA DON THANH TOAN --- \n");
        sb.append(String.format("Ma hoa don: %s\n", paymentId));
        sb.append(String.format("Thoi gian: %s\n", paymentTime.toString().substring(0, 19).replace('T', ' ')));
        sb.append("---------------------------\n");

        // Liệt kê chi tiết từng vé
        for (Ticket ticket : tickets) {
            sb.append(String.format("Vé: %s | Ghế: %s%d | Giá: %.0f VND\n",
                    ticket.getShowtime().getMovie().getTitle(),
                    ticket.getSeat().getRow(), ticket.getSeat().getNumber(),
                    ticket.getPrice()));
        }
        sb.append("---------------------------\n");
        sb.append(String.format("TONG CONG: %.0f VND\n", totalAmount));
        sb.append("---------------------------\n");
        return sb.toString();
    }
}