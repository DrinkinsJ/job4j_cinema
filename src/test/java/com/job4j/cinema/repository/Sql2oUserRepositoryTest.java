package com.job4j.cinema.repository;

import com.job4j.cinema.configuration.DatasourceConfiguration;
import com.job4j.cinema.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oUserRepositoryTest {

    private static Sql2oUserRepository sql2oUserRepository;

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

        sql2oUserRepository = new Sql2oUserRepository(sql2o);
    }

    @Test
    public void test() {
        User user = new User(1, "name1", "email3@mail.ru", "password");
        sql2oUserRepository.save(user);
    }

    @Test
    public void test2() {
        var user = sql2oUserRepository.findByEmailAndPassword("name1", "password");
        System.out.println(user.get().getFullName() + " " + user.get().getPassword());
    }

    @Test
    public void test3() {
        var users = sql2oUserRepository.findAll();
        for (var user : users) {
            System.out.println(user.getEmail() + " " + user.getPassword());
        }
    }
}