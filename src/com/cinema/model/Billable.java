package com.cinema.model; // <<-- ĐÃ FIX PACKAGE

public interface Billable {

    /**
     * Tinh toan tong gia tri thanh toan
     * @return Tong so tien can thanh toan
     */
    double calculateTotalAmount();

    /**
     * Tao va tra ve mot chuoi dinh dang hoa don
     * @return Chuoi thong tin hoa don
     */
    String generateBillDetails();
}