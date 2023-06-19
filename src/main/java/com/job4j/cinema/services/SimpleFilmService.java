package com.job4j.cinema.services;

import com.job4j.cinema.dto.FilmDto;
import com.job4j.cinema.model.Film;
import com.job4j.cinema.repository.FilmRepository;
import com.job4j.cinema.repository.GenreRepository;
import org.springframework.stereotype.Service;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ThreadSafe
public class SimpleFilmService implements FilmService {

    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;

    public SimpleFilmService(FilmRepository filmRepository, GenreRepository genreRepository) {
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<FilmDto> findFilmById(int id) {
        return filmRepository.findById(id).stream().map(film -> new FilmDto(film.getId(),
                film.getName(), film.getDescription(), film.getYear(),
                film.getMinimalAge(), film.getDurationInMinutes(), genreName(film), film.getFileId())).findAny();
    }

    @Override
    public Collection<FilmDto> findAll() {
        return filmRepository.findAll().stream().map(film -> new FilmDto(film.getId(),
                        film.getName(), film.getDescription(), film.getYear(),
                        film.getMinimalAge(), film.getDurationInMinutes(), genreName(film), film.getFileId()))
                .collect(Collectors.toList());
    }

    public String genreName(Film film) {
        String result = null;
        var genre = genreRepository.findById(film.getGenreId());
        if (genre.isPresent()) {
            result = genre.get().getName();
        }
        return result;
    }
}
