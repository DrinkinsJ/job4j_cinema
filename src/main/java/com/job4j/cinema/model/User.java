package com.job4j.cinema.model;

import java.util.Map;
import java.util.Objects;

public class User {

    private int id;

    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "full_name", "full_name",
            "email", "email",
            "password", "password"
    );

    private String email;

    private String password;
    private String full_name;

    public User() {
    }

    public User(int id, String full_name, String email, String password) {
        this.id = id;
        this.full_name = full_name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
