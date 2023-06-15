package com.job4j.cinema.dto;

import java.util.Objects;

public class HallDto {
    private int rowCount;
    private int placeCount;

    public HallDto(int rowCount, int placeCount) {
        this.rowCount = rowCount;
        this.placeCount = placeCount;
    }

    public HallDto() {
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(int placeCount) {
        this.placeCount = placeCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HallDto hallDto = (HallDto) o;
        return rowCount == hallDto.rowCount && placeCount == hallDto.placeCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowCount, placeCount);
    }
}
