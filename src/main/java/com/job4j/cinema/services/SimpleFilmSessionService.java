package com.job4j.cinema.services;

import com.job4j.cinema.dto.FilmSessionDto;
import com.job4j.cinema.model.FilmSession;
import com.job4j.cinema.repository.FilmRepository;
import com.job4j.cinema.repository.FilmSessionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ThreadSafe
public class SimpleFilmSessionService implements FilmSessionService {

    private final FilmSessionRepository filmSessionRepository;
    private final FilmRepository filmRepository;

    public SimpleFilmSessionService(FilmSessionRepository filmSessionRepository, FilmRepository filmRepository) {
        this.filmSessionRepository = filmSessionRepository;
        this.filmRepository = filmRepository;
    }


    @Override
    public Optional<FilmSessionDto> findById(int id) {
        return filmSessionRepository.findById(id).map(this::buildFilmSessionDto);
    }

    @Override
    public Collection<FilmSessionDto> findAll() {
        return filmSessionRepository.findAll().stream().map(this::buildFilmSessionDto).collect(Collectors.toList());
    }

    public FilmSessionDto buildFilmSessionDto(FilmSession filmSession) {
        FilmSessionDto filmSessionDto = new FilmSessionDto();
        filmSessionDto.setSessionId(filmSession.getFilmId());
        filmSessionDto.setFilmId(filmSession.getFilmId());
        filmSessionDto.setHallId(filmSession.getHallId());
        filmSessionDto.setStartTime(filmSession.getStartTime());
        filmSessionDto.setDurationInMinutes(filmRepository.findById(filmSession.getFilmId()).get().getDurationInMinutes());
        filmSessionDto.setPrice(filmSessionDto.getPrice());
        return filmSessionDto;
    }
}
