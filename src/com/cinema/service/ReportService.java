package com.cinema.service;

import com.cinema.repository.TicketRepository;

import java.util.*;
import java.util.stream.Collectors;

public class ReportService {
    private TicketRepository ticketRepo = new TicketRepository();

    public Map<String, Double> revenueByMovie() {
        Map<String, Double> map = new HashMap<>();
        ticketRepo.findAll().forEach(t -> {
            map.put(t.getMovieId(), map.getOrDefault(t.getMovieId(), 0.0) + t.getPrice());
        });
        return map;
    }

    public List<String> top3Movies() {
        return revenueByMovie().entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
