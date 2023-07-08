package com.job4j.cinema.controller;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexControllerTest {

    @Test
    public void whenGetIndexThenReturnIndexPage() {
        var indexController = new IndexController();
        var view = indexController.getIndex();
        assertThat(view).isEqualTo("index");
    }
}