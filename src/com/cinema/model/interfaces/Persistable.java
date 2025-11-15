package com.cinema.model.interfaces;

import java.util.List;

public interface Persistable<T> {

    List<T> load(String filePath);
    void save(List<T> data, String filePath);
}