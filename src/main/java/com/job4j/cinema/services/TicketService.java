package com.job4j.cinema.services;

import com.job4j.cinema.model.Ticket;

import java.util.Optional;

public interface TicketService {

    Optional<Ticket> save(Ticket ticket);

    Optional<Ticket> findById(int id);
}
