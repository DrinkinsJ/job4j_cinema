package com.job4j.cinema.repository;

import com.job4j.cinema.model.Genre;

import java.util.Collection;
import java.util.Optional;

public interface GenreRepository {
    Optional<Genre> findById(int id);

    Collection<Genre> findAll();
}
