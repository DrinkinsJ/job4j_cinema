package com.job4j.cinema.services;

import com.job4j.cinema.model.Ticket;
import com.job4j.cinema.repository.TicketRepository;

import java.util.Optional;

public class SimpleTicketService implements TicketService {

     private final TicketRepository sql2oTicketRepository;

    public SimpleTicketService(TicketRepository sql2oTicketRepository) {
        this.sql2oTicketRepository = sql2oTicketRepository;
    }

    @Override
    public Optional<Ticket> save(Ticket ticket) {
        return sql2oTicketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> findById(int id) {
        return sql2oTicketRepository.findById(id);
    }
}
