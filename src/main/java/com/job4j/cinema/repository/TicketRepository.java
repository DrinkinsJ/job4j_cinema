package com.job4j.cinema.repository;

import com.job4j.cinema.model.Ticket;

import java.util.Optional;

public interface TicketRepository {
    Optional<Ticket> save(Ticket ticket);

    Optional<Ticket> findById(int id);
}
