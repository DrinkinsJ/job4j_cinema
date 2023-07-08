package com.job4j.cinema.controller;

import com.job4j.cinema.dto.FilmSessionDto;
import com.job4j.cinema.services.FilmSessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class FilmSessionControllerTest {

    private FilmSessionController filmSessionController;

    @BeforeEach
    public void initServices() {
        var filmSessionService = mock(FilmSessionService.class);
        filmSessionController = new FilmSessionController(filmSessionService);
    }

    @Test
    public void whenGetScheduleThenReturnSchedulePage() {
        var filmSessions = List.of(new FilmSessionDto());
        var model = new ConcurrentModel();
        var view = filmSessionController.getSchedule(model);
        model.addAttribute("filmSessions", filmSessions);
        var actualFilmSessions = model.getAttribute("filmSessions");
        assertThat(view).isEqualTo("sessions/schedule");
        assertThat(actualFilmSessions).isEqualTo(filmSessions);
    }
}