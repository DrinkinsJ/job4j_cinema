package com.job4j.cinema.controller;

import com.job4j.cinema.model.Ticket;
import com.job4j.cinema.model.User;
import com.job4j.cinema.services.FilmSessionService;
import com.job4j.cinema.services.HallService;
import com.job4j.cinema.services.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.ConcurrentModel;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TicketControllerTest {
    private TicketService ticketService;
    private FilmSessionService filmSessionService;
    private HallService hallService;
    private TicketController ticketController;

    @BeforeEach
    public void initServices() {
        ticketService = mock(TicketService.class);
        filmSessionService = mock(FilmSessionService.class);
        hallService = mock(HallService.class);
        ticketController = new TicketController(ticketService, filmSessionService, hallService);
    }

}