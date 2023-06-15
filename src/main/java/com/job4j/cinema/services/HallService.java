package com.job4j.cinema.services;

import com.job4j.cinema.dto.HallDto;

import java.util.Collection;
import java.util.Optional;

public interface HallService {
    Optional<HallDto> findById(int id);

    Collection<HallDto> findALl();
}
