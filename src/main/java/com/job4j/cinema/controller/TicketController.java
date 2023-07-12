package com.job4j.cinema.controller;

import com.job4j.cinema.model.Ticket;
import com.job4j.cinema.model.User;
import com.job4j.cinema.services.FilmSessionService;
import com.job4j.cinema.services.HallService;
import com.job4j.cinema.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.IntStream;

@ThreadSafe
@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final FilmSessionService filmSessionService;

    private final HallService hallService;

    public TicketController(TicketService ticketService, FilmSessionService filmSessionService, HallService hallService) {
        this.ticketService = ticketService;
        this.filmSessionService = filmSessionService;
        this.hallService = hallService;
    }

    @GetMapping("/{id}")
    public String getFilmLibrary(@PathVariable int id, Model model,
                                 HttpServletResponse response) {
        var filmSessionOptional = filmSessionService.findById(id);
        if (filmSessionOptional.isEmpty()) {
            model.addAttribute("message", "Cant find film session");
            return "errors/404";
        }
        var hall = hallService.findById(filmSessionOptional.get().getHallId());
        model.addAttribute("film", filmSessionOptional.get())
                .addAttribute("maxRow", getListNumber(hall.get().getRowCount()))
                .addAttribute("maxPlace", getListNumber(hall.get().getPlaceCount()))
                .addAttribute("ticket", new Ticket());
        response.addCookie(new Cookie("session", Integer.toString(id)));
        return "tickets/buying";
    }

    private List<String> getListNumber(int number) {
        return IntStream.rangeClosed(1, number).mapToObj(String::valueOf).toList();
    }

    @PostMapping("/buying")
    public String buyTicket(@ModelAttribute Ticket ticket, Model model, @CookieValue(value = "session") String session, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        ticket.setUserId(user.getId());
        ticket.setSessionId(Integer.parseInt(session));
        var ticketOptional = ticketService.save(ticket);
        if (ticketOptional.isEmpty()) {
            String failedBuy = String.format("You can't buy ticket for %s row %s place, try another seat place",
                    ticket.getRowNumber(),
                    ticket.getPlaceNumber());
            model.addAttribute("message", failedBuy);
            return "errors/404";
        }
        String successBuy = String.format("You buy ticket for %s row %s place",
                ticketOptional.get().getRowNumber(),
                ticketOptional.get().getPlaceNumber());
        model.addAttribute("message", successBuy);
        return "tickets/successBuy";
    }

    @GetMapping("/successBuy")
    public String success() {
        return "tickets/successBuy";
    }
}
