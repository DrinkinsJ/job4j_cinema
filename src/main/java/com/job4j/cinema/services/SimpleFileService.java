package com.job4j.cinema.services;

import com.job4j.cinema.dto.FileDto;
import com.job4j.cinema.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class SimpleFileService implements FileService {

    private final FileRepository fileRepository;

    public SimpleFileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public Optional<FileDto> getFileById(int id) {
        var fileOptional = fileRepository.findById(id);
        if (fileOptional.isEmpty()) {
            return Optional.empty();
        }
        try {
            var path = Path.of(fileOptional.get().getPath());
            var content = Files.readAllBytes(path);
            return Optional.of(new FileDto(fileOptional.get().getName(), content));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
