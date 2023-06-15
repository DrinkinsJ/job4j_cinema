package com.job4j.cinema.services;

import com.job4j.cinema.dto.FileDto;

import java.util.Optional;

public interface FileService {
    Optional<FileDto> getFileById(int id);
}
