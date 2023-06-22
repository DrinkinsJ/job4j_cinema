package com.job4j.cinema.repository;

import com.job4j.cinema.configuration.DatasourceConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Sql2oFilmRepositoryTest {

    private static Sql2oFilmRepository sql2oFilmRepository;


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

        sql2oFilmRepository = new Sql2oFilmRepository(sql2o);
    }

    @Test
    public void whenFindByIdThenReturnName() {
        assertThat(sql2oFilmRepository.findById(1).get().getName()).isEqualTo("ALIEN");
    }

    @Test
    public void whenFindAllThenReturnCountFilms() {
        assertThat(sql2oFilmRepository.findAll().size()).isEqualTo(9);
    }
}
