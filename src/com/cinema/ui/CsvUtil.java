package com.cinema.ui;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {
    public static List<String[]> readAll(String path) throws IOException {
        List<String[]> rows = new ArrayList<>();
        Path p = Paths.get(path);
        if (!Files.exists(p)) return rows;
        try (BufferedReader br = Files.newBufferedReader(p)) {
            String line;
            while ((line = br.readLine()) != null) {
                // simple CSV split (no quoted fields)
                String[] cols = line.split(",", -1);
                rows.add(cols);
            }
        }
        return rows;
    }

    public static void writeAll(String path, List<String[]> rows) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(path))) {
            for (String[] row : rows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        }
    }
}
