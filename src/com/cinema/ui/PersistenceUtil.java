package com.cinema.ui;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceUtil {

    public static <T> void save(List<T> entities, String filename) {
        try {
            // ... (Logic tạo thư mục data nếu cần) ...
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(entities);
                System.out.println("Data saved to: " + filename);
            }
        } catch (IOException e) {
            System.err.println("Error saving data to " + filename + ": " + e.getMessage());
        }
    }

    // ⭐ Sửa lỗi: Đảm bảo sử dụng <T> generic và ép kiểu an toàn ⭐
    public static <T> List<T> load(String filename) {
        File file = new File(filename);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            @SuppressWarnings("unchecked")
            List<T> entities = (List<T>) ois.readObject(); // Ép kiểu an toàn (sửa lỗi Incompatible types)
            return entities;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data from " + filename + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}