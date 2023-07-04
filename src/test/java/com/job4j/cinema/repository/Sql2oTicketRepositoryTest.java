package com.job4j.cinema.repository;

import com.job4j.cinema.configuration.DatasourceConfiguration;
import com.job4j.cinema.model.Ticket;
import com.job4j.cinema.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

class Sql2oTicketRepositoryTest {

    private static Sql2oTicketRepository sql2oTicketRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oFilmRepositoryTest.class.getClassLoader()
                .getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        sql2oTicketRepository = new Sql2oTicketRepository(sql2o);

    }

    @Test
    public void whenSaveTicketThenEqual() {
      Ticket ticket = new Ticket(123, 1, 2, 3, 3);
        assertThat(sql2oTicketRepository.save(ticket)).isEqualTo(Optional.of(ticket));
    }

}