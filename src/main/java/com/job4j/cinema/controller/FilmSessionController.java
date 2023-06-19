package com.job4j.cinema.controller;

import com.job4j.cinema.services.FilmSessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sessions")
public class FilmSessionController {
    private final FilmSessionService filmSessionService;

    public FilmSessionController(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }

    @GetMapping("/schedule")
    public String getSchedule(Model model) {
        model.addAttribute("sessions" ,filmSessionService.findAll());
        return "sessions/schedule";
    }
}
