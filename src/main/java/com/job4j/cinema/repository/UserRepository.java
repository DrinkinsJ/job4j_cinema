package com.job4j.cinema.repository;

import com.job4j.cinema.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    Optional<User> save(User user);

    Optional<User> findByEmailAndPassword(String email, String password);

    Collection<User> findAll();
}
