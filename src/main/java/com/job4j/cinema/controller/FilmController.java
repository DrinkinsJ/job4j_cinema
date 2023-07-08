package com.job4j.cinema.controller;

import com.job4j.cinema.services.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/library")
    public String getFilmsLibrary(Model model) {
        model.addAttribute("films", filmService.findAll());
        return "films/library";
    }
}
