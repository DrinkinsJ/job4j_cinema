package com.job4j.cinema.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class FilmSessionDto {
    private int sessionId;
    private int filmId;
    private int hallId;
    private LocalDateTime startTime;
    private int durationInMinutes;
    private int price;

    public FilmSessionDto() {
    }

    public FilmSessionDto(int sessionId, int filmId, int hallId, LocalDateTime startTime, int durationInMinutes, int price) {
        this.sessionId = sessionId;
        this.filmId = filmId;
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

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FilmSessionDto that = (FilmSessionDto) o;
        return sessionId == that.sessionId && filmId == that.filmId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, filmId);
    }
}
