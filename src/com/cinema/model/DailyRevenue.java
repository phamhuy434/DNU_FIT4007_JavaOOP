package com.cinema.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Lớp đại diện cho một bản ghi doanh thu tổng hợp trong một ngày.
 * Dữ liệu này được sử dụng bởi RevenueRepository.
 */
public class DailyRevenue implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate date;
    private double totalRevenue;
    private int ticketsSold;

    // Constructor
    public DailyRevenue(LocalDate date, double totalRevenue, int ticketsSold) {
        this.date = date;
        this.totalRevenue = totalRevenue;
        this.ticketsSold = ticketsSold;
    }

    // --- Getters ---
    public LocalDate getDate() {
        return date;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    // --- Setters (Thêm cho mục đích cập nhật thống kê) ---

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    @Override
    public String toString() {
        return String.format("Ngày: %s | Doanh thu: %.0f VNĐ | Vé bán: %d",
                date.toString(), totalRevenue, ticketsSold);
    }
}