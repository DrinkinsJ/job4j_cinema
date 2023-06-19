package com.job4j.cinema.repository;

import com.job4j.cinema.configuration.DatasourceConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Properties;

class Sql2oHallRepositoryTest {

    private static Sql2oHallRepository sql2oHallRepository;

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

        sql2oHallRepository = new Sql2oHallRepository(sql2o);
    }

    @Test
    public void test() {
        var halls = sql2oHallRepository.findAll();
        for (var hall : halls) {
            System.out.println(hall.getName());
        }
    }

    @Test
    public void test2() {
        var hall = sql2oHallRepository.findById(1);
        System.out.println(hall);
    }

}