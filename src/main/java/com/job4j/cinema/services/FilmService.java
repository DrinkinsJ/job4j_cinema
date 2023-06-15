package com.job4j.cinema.services;

import com.job4j.cinema.dto.FilmDto;

import java.util.Collection;
import java.util.Optional;

public interface FilmService {
    Optional<FilmDto> findFilmById(int id);

    Collection<FilmDto> findAll();
}
