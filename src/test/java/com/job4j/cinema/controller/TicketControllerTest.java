package com.job4j.cinema.controller;

import com.job4j.cinema.dto.FilmSessionDto;
import com.job4j.cinema.dto.HallDto;
import com.job4j.cinema.model.Ticket;
import com.job4j.cinema.model.User;
import com.job4j.cinema.services.FilmSessionService;
import com.job4j.cinema.services.HallService;
import com.job4j.cinema.services.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ConcurrentModel;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    public void whenGetFilmLibraryThenRedirectToTicketBuyingPage() {
        var time = LocalDateTime.now();
        var filmSessionDto = new FilmSessionDto(1, "nameFilm", "nameHall", 1, time, 120, 500);
        var hallDto = new HallDto(2, 2);
        when(hallService.findById(anyInt())).thenReturn(Optional.of(hallDto));
        when(filmSessionService.findById(1)).thenReturn(Optional.of(filmSessionDto));
        var response = new MockHttpServletResponse();
        var model = new ConcurrentModel();
        var view = ticketController.getFilmLibrary(1, model, response);
        var actualFilm = model.getAttribute("film");
        var actualRow = model.getAttribute("maxRow");
        var actualPlace = model.getAttribute("maxPlace");
        var actualTicket = model.getAttribute("ticket");
        var actualCookie = response.getCookie("session");
        assertThat(view).isEqualTo("tickets/buying");
        assertThat(actualFilm).isEqualTo(filmSessionDto);
        assertThat(actualRow).isEqualTo(List.of("1", "2"));
        assertThat(actualPlace).isEqualTo(List.of("1", "2"));
        assertThat(actualTicket).isEqualTo(new Ticket());
        assertThat(Objects.requireNonNull(actualCookie).getValue()).isEqualTo("1");
    }

    @Test
    public void whenGetFilmLibraryThenErrorAndRedirectToErrorPage() {
        var msg = "Cant find film session";
        when(filmSessionService.findById(anyInt())).thenReturn(Optional.empty());
        var model = new ConcurrentModel();
        var response = mock(HttpServletResponse.class);
        var view = ticketController.getFilmLibrary(1, model, response);
        var actualMsg = model.getAttribute("message");
        assertThat(actualMsg).isEqualTo(msg);
        assertThat(view).isEqualTo("errors/404");
    }

    @Test
    public void whenBuyTicketSuccessThenThenRedirectToSuccessPage() {
        var ticket = new Ticket(1, 1, 1, 1, 1);
        var model = new ConcurrentModel();
        var sessionCookie = "1";
        when(ticketService.save(ticket)).thenReturn(Optional.of(ticket));
        var user = new User(1, "name", "email", "password");
        var request = new MockHttpServletRequest();
        var httpSession = new MockHttpSession();
        request.setSession(httpSession);
        httpSession.setAttribute("user", user);
        var view = ticketController.buyTicket(ticket, model, sessionCookie, request);
        var actualMsg = model.getAttribute("message");
        assertThat(view).isEqualTo("tickets/successBuy");
        assertThat(actualMsg).isEqualTo("You buy ticket for " + ticket.getRowNumber() + " row " + ticket.getPlaceNumber() + " place");
    }

    @Test
    public void whenBuyTicketFailedThenRedirectToErrorPage() {
        var ticket = new Ticket();
        var msg = "Can't buy ticket, try another seat places";
        when(ticketService.save(ticket)).thenReturn(Optional.empty());
        var user = new User(1, "name", "email", "password");
        var model = new ConcurrentModel();
        var sessionCookie = "1";
        var request = new MockHttpServletRequest();
        var httpSession = new MockHttpSession();
        request.setSession(httpSession);
        httpSession.setAttribute("user", user);
        var view = ticketController.buyTicket(ticket, model, sessionCookie, request);
        var actualMsg = model.getAttribute("error");
        assertThat(view).isEqualTo("errors/404");
        assertThat(actualMsg).isEqualTo(msg);
    }

    @Test
    public void whenSuccessBuyThenRedirect() {
        var view = ticketController.success();
        assertThat(view).isEqualTo("tickets/successBuy");
    }
}