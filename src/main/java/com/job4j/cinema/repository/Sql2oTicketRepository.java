package com.job4j.cinema.repository;

import com.job4j.cinema.model.Ticket;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import java.util.Optional;

@Repository
public class Sql2oTicketRepository implements TicketRepository {

    private final Sql2o sql2o;

    public Sql2oTicketRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Ticket> save(Ticket ticket) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("INSERT INTO tickets(session_id, row_number, place_number, user_id) "
                            + "values (:session_id, :row_number, :place_number, :user_id)", true)
                    .addParameter("session_id", ticket.getSessionId())
                    .addParameter("row_number", ticket.getRowNumber())
                    .addParameter("place_number", ticket.getPlaceNumber())
                    .addParameter("user_id", ticket.getUserId());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            ticket.setId(generatedId);
            return Optional.of(ticket);
        }
    }

    @Override
    public Optional<Ticket> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM tickets WHERE id = :id");
            var ticket = query.addParameter("id", id).executeAndFetchFirst(Ticket.class);
            return Optional.of(ticket);
        }
    }
}
