package com.job4j.cinema.controller;

import com.job4j.cinema.model.Ticket;
import com.job4j.cinema.services.FilmSessionService;
import com.job4j.cinema.services.HallService;
import com.job4j.cinema.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.http.Cookie;
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
            model.addAttribute("message", "Сеанс с указанным идентификатором не найден");
            return "error";
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
    public String buyTicket(@ModelAttribute Ticket ticket, Model model) {
        var ticketOptional = ticketService.save(ticket);
        System.out.println(ticket.getId());
        System.out.println(ticket.getPlaceNumber());
        System.out.println(ticket.getUserId());

        if (ticketOptional.isEmpty()) {
            model.addAttribute("error", "Не удаётся купить билет на указанное место. Попробуйте "
                    + "другой вариант.");
            return "errors/404";
        }
        String successBuy = String.format("Вы купили билет на %s ряд %s место",
                ticketOptional.get().getRowNumber(),
                ticketOptional.get().getPlaceNumber());
        model.addAttribute("message", successBuy);
        return "redirect:tickets/successBuy";
    }
}
