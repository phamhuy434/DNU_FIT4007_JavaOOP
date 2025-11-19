package com.cinema.model;

import com.cinema.model.interfaces.Billable; // ⭐ Sửa Import để trỏ đúng đến package interfaces ⭐
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Payment implements Serializable, Billable {

    private final String paymentId;
    private final List<Ticket> tickets;
    private final LocalDateTime paymentTime;
    private final double totalAmount;

    public Payment(List<Ticket> tickets) {
        this.paymentId = UUID.randomUUID().toString().substring(0, 8);
        this.tickets = tickets;
        this.paymentTime = LocalDateTime.now();
        this.totalAmount = calculateTotalAmount();
    }

    @Override
    public double calculateTotalAmount() {
        return tickets.stream().mapToDouble(Ticket::getPrice).sum();
    }

    // Triển khai generateBillDetails() để sửa lỗi trong MainApplication
    @Override
    public String generateBillDetails() {
        return String.format("HOA DON THANH TOAN MA: %s | TONG: %.0f VND", paymentId, totalAmount);
    }

    // Cần có các Getters...
    public String getPaymentId() { return paymentId; }
    public LocalDateTime getPaymentTime() { return paymentTime; }
    public double getTotalAmount() { return totalAmount; }
}