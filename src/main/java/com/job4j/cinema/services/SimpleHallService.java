package com.job4j.cinema.services;

import com.job4j.cinema.dto.HallDto;
import com.job4j.cinema.model.Hall;
import com.job4j.cinema.repository.HallRepository;
import org.springframework.stereotype.Service;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@ThreadSafe
public class SimpleHallService implements HallService {

    private final HallRepository sql2oHallRepository;

    public SimpleHallService(HallRepository sql2oHallRepository) {
        this.sql2oHallRepository = sql2oHallRepository;
    }

    @Override
    public Optional<HallDto> findById(int id) {
        var hallOptional = sql2oHallRepository.findById(id);
        if (hallOptional.isEmpty()) {
            return Optional.empty();
        }
        var hall = hallOptional.get();
        return Optional.of(buildHallDto(hallOptional.get()));
    }

    @Override
    public Collection<HallDto> findAll() {
        return sql2oHallRepository.findAll().stream().map(this::buildHallDto).collect(Collectors.toList());
    }

    public HallDto buildHallDto(Hall hall) {
        var hallDto = new HallDto();
        hallDto.setPlaceCount(hall.getPlaceCount());
        hallDto.setRowCount(hall.getRowCount());
        return hallDto;
    }
}
