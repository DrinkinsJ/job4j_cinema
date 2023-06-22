package com.job4j.cinema.services;

import com.job4j.cinema.model.Genre;
import com.job4j.cinema.repository.GenreRepository;
import org.springframework.stereotype.Service;

import javax.annotation.concurrent.ThreadSafe;

@Service
@ThreadSafe
public class SimpleGenreService implements GenreService {

    private final GenreRepository genreRepository;

    public SimpleGenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public String getNameGenreById(int id) {
        var genreOptional = genreRepository.findById(id);
        return genreOptional.map(Genre::getName).orElse("");
    }
}
