package com.job4j.cinema.repository;

import com.job4j.cinema.configuration.DatasourceConfiguration;
import com.job4j.cinema.model.Film;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oFileRepositoryTest {

public static Sql2oFileRepository sql2oFileRepository;

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

        sql2oFileRepository = new Sql2oFileRepository(sql2o);
    }
    @Test
    public void test() {
        var film = sql2oFileRepository.findById(1);
        System.out.println(film.get().getPath());
    }

}