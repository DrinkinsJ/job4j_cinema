package com.job4j.cinema.repository;

import com.job4j.cinema.configuration.DatasourceConfiguration;
import com.job4j.cinema.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class Sql2oUserRepositoryTest {

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


    @AfterEach
    public void clearUsers() {
        var users = sql2oUserRepository.findAll();
        for (var user : users) {
            sql2oUserRepository.deleteById(user.getId());
        }
    }

    @Test
    public void whenUserAddThenEquals() {
        User user = new User(0, "mail", "name", "password");
        assertThat(sql2oUserRepository.save(user)).isEqualTo(Optional.of(user));
    }

    @Test
    public void whenUserAddWithSameEmailsThenEmpty() {
        User user1 = new User(0, "name", "email", "password");
        User user2 = new User(0, "name1", "email", "password1");
        sql2oUserRepository.save(user1);
        assertThat(sql2oUserRepository.save(user2)).isEqualTo(Optional.empty());
    }

    @Test
    public void whenSaveUsersThenGetAll() {
        var user1 = sql2oUserRepository.save(new User(0, "mail", "name", "password"));
        var user2 = sql2oUserRepository.save(new User(1, "mail2", "name1", "password1"));
        var users = sql2oUserRepository.findAll();
        for (var u : users) {
            System.out.println(
                    u.getId() + " " + u.getName() + " " + u.getEmail() + " " + u.getPassword()
            );

        }        assertThat(users).isEqualTo(List.of(user1.get(), user2.get()));
    }

    @Test
    public void whenUserFindByPasswordAndMailThenGet() {
        User user = new User(1, "name", "email12", "pass");
        sql2oUserRepository.save(user);
        assertThat(sql2oUserRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).isEqualTo(Optional.of(user));
    }

    @Test
    public void whenUserFindByPasswordAndMailThenEmpty() {
        var user1 = sql2oUserRepository.save(new User(0, "mail", "name", "password"));
        assertThat(sql2oUserRepository.findByEmailAndPassword("mail2", user1.get().getPassword())).isEqualTo(Optional.empty());
        assertThat(sql2oUserRepository.findByEmailAndPassword(user1.get().getEmail(), "password2")).isEqualTo(Optional.empty());
    }
}