package com.cinema.repository;

import java.util.List;

public interface CrudRepository<T, ID> {
    // T: Kiểu dữ liệu (Movie, Showtime,...) | ID: Kiểu khóa chính (String)

    void save(T entity); // Thêm mới hoặc cập nhật đối tượng

    T findById(ID id); // Tìm đối tượng theo ID

    boolean delete(ID id); // Xóa đối tượng theo ID

    List<T> findAll(); // Lấy tất cả các đối tượng

    // Tải dữ liệu từ file khi khởi động (nếu bạn không dùng interface Persistable riêng)
    // List<T> loadFromFile();

    // Lưu dữ liệu vào file khi tắt
    // void saveToFile(List<T> entities);
}