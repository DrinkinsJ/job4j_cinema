package com.job4j.cinema.controller;


import com.job4j.cinema.dto.FileDto;
import com.job4j.cinema.services.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileControllerTest {

    private FileService fileService;
    private FileController fileController;
    private MultipartFile multipartFile;

    @BeforeEach
    public void initServices() {
        fileService = mock(FileService.class);
        fileController = new FileController(fileService);
        multipartFile = new MockMultipartFile("file", new byte[]{1, 2, 3});
    }

    @Test
    public void whenFileControllerGetFileByIdSuccessThenResponseOk() throws IOException {
        var file = new FileDto(multipartFile.getOriginalFilename(), multipartFile.getBytes());
        when(fileService.getFileById(anyInt())).thenReturn(Optional.of(file));
        var view = fileController.getById(1);
        assertThat(view).isEqualTo(ResponseEntity.ok(file.getContent()));
    }

    @Test
    public void whenFileControllerGetFileByIdFailedThenResponseNotFound() {
        when(fileService.getFileById(anyInt())).thenReturn(Optional.empty());
        var view = fileController.getById(1);
        assertThat(view).isEqualTo(ResponseEntity.notFound().build());
    }

}