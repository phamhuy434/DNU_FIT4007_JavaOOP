package com.cinema.model.repository;

import com.cinema.model.Payment;
import com.cinema.ui.PersistenceUtil;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentRepository {

    private final List<Payment> payments;
    private static final String FILE_PATH = "data/payments.dat";

    public PaymentRepository() {
        this.payments = PersistenceUtil.load(FILE_PATH);
    }

    private void saveAll() {
        PersistenceUtil.save(payments, FILE_PATH);
    }

    public void save(Payment payment) {
        payments.add(payment);
        saveAll();
    }

    public List<Payment> findAll() {
        return payments;
    }

    // Tùy chọn: Hàm hỗ trợ tính toán doanh thu (DailyRevenue)
    public List<Payment> findByDate(LocalDate date) {
        return payments.stream()
                .filter(p -> p.getPaymentTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }
}