package com.job4j.cinema.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class FilmSessionDto {
    private int sessionId;
    private String nameFilm;
    private String nameHall;

    private int hallId;
    private LocalDateTime startTime;
    private int durationInMinutes;
    private int price;

    public FilmSessionDto() {
    }

    public FilmSessionDto(int sessionId, String nameFilm, String nameHall, int hallId, LocalDateTime startTime, int durationInMinutes, int price) {
        this.sessionId = sessionId;
        this.nameFilm = nameFilm;
        this.nameHall = nameHall;
        this.hallId = hallId;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
        this.price = price;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getNameFilm() {
        return nameFilm;
    }

    public void setNameFilm(String nameFilm) {
        this.nameFilm = nameFilm;
    }

    public String getNameHall() {
        return nameHall;
    }

    public void setNameHall(String nameHall) {
        this.nameHall = nameHall;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmSessionDto that = (FilmSessionDto) o;
        return sessionId == that.sessionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId);
    }
}
