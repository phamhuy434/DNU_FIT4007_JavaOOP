package com.cinema.model.interfaces; // ⭐ Đảm bảo package này khớp với thư mục interfaces ⭐

/**
 * Interface Billable (Tính giá và tạo hóa đơn)
 */
public interface Billable {

    /**
     * Tính tổng toàn bộ giá trị tiền cần thanh toán
     * @return Tổng số tiền cần thanh toán
     */
    double calculateTotalAmount();

    /**
     * Tạo và trả về một chuỗi định dạng hóa đơn
     * @return Chuỗi thông tin hóa đơn
     */
    String generateBillDetails();
}