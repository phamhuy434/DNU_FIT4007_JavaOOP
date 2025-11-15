package com.cinema.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

// Bill đại diện cho một giao dịch thanh toán hoàn chỉnh
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;

    private String billId;
    private LocalDateTime issueDate;
    private List<Ticket> tickets; // Các vé thuộc hóa đơn này
    private double totalAmount;

    // Constructor
    public Bill(String billId, List<Ticket> tickets) {
        this.billId = billId;
        this.issueDate = LocalDateTime.now();
        this.tickets = tickets;

        // Tự động tính tổng tiền
        this.totalAmount = calculateTotalAmount(tickets);
    }

    /**
     * Tính tổng tiền bằng cách cộng giá của tất cả các vé trong danh sách.
     */
    public static double calculateTotalAmount(List<Ticket> tickets) {
        return tickets.stream()
                .mapToDouble(Ticket::getPrice)
                .sum();
    }

    // --- Getters ---
    public String getBillId() {
        return billId;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}