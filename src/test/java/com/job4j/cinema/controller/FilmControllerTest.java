package com.job4j.cinema.controller;


import com.job4j.cinema.dto.FilmDto;
import com.job4j.cinema.services.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class FilmControllerTest {

    private FilmController filmController;

    @BeforeEach
    public void initServices() {
        var filmService = mock(FilmService.class);
        filmController = new FilmController(filmService);
    }

    @Test
    public void whenGetFilmsLibraryThenReturnFilmsLibraryPage() {
        var films = List.of(new FilmDto());
        var model = new ConcurrentModel();
        var view = filmController.getFilmsLibrary(model);
        model.addAttribute("films", films);
        var actualFilms = model.getAttribute("films");
        assertThat(view).isEqualTo("films/library");
        assertThat(actualFilms).isEqualTo(films);
    }

}