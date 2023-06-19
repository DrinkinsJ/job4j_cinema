package com.job4j.cinema.controller;

import com.job4j.cinema.services.FilmService;
import com.job4j.cinema.services.FilmSessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;
    private final FilmSessionService filmSessionService;

    public FilmController(FilmService filmService, FilmSessionService filmSessionService) {
        this.filmService = filmService;
        this.filmSessionService = filmSessionService;
    }

    @GetMapping("/library")
    public String getFilmsLibrary(Model model) {
        model.addAttribute("films", filmService.findAll());
        return "films/library";
    }
}
