package com.job4j.cinema.services;

import com.job4j.cinema.dto.FilmDto;
import com.job4j.cinema.model.Film;
import com.job4j.cinema.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SimpleFilmService implements FilmService {

    private final FilmRepository sql2oFilmRepository;
    private final GenreService simpleGenreService;

    public SimpleFilmService(FilmRepository sql2oFilmRepository, GenreService simpleGenreService) {
        this.sql2oFilmRepository = sql2oFilmRepository;
        this.simpleGenreService = simpleGenreService;
    }

    @Override
    public Optional<FilmDto> findFilmById(int id) {
        return sql2oFilmRepository.findById(id).map(this::buildFilmDto);
    }

    @Override
    public Collection<FilmDto> findAll() {
        return sql2oFilmRepository.findAll().stream().map(this::buildFilmDto).collect(Collectors.toList());
    }

    private FilmDto buildFilmDto(Film film) {
        var filmDto = new FilmDto();
        filmDto.setFileId(film.getFileId());
        filmDto.setName(film.getName());
        filmDto.setDescription(film.getDescription());
        filmDto.setYear(film.getYear());
        filmDto.setMinimalAge(film.getMinimalAge());
        filmDto.setDurationInMinutes(film.getDurationInMinutes());
        filmDto.setGenre(simpleGenreService.getNameGenreById(film.getGenreId()));
        filmDto.setFileId(film.getFileId());
        return filmDto;
    }
}
