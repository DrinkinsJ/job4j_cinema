package com.job4j.cinema.controller;

import com.job4j.cinema.model.User;
import com.job4j.cinema.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserControllerTest {

    private UserService userService;

    private UserController userController;

    @BeforeEach
    public void initServices() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    public void whenGetRegistrationPage() {
        var view = userController.getRegistrationPage();
        assertThat(view).isEqualTo("users/register");
    }

    @Test
    public void whenGetLoginPage() {
        var view = userController.getLoginPage();
        assertThat(view).isEqualTo("users/login");
    }

    @Test
    public void whenSuccessRegisterThenRedirectToIndexPage() {
        var model = new ConcurrentModel();
        var user = new User(1, "name", "email", "password");
        when(userService.save(user)).thenReturn(Optional.of(user));
        var view = userController.register(model, user);
        assertThat(view).isEqualTo("redirect:/index");
    }

    @Test
    public void whenFailedRegisterThenRedirectToErrorPage() {
        var model = new ConcurrentModel();
        var user = new User();
        when(userService.save(user)).thenReturn(Optional.empty());
        var view = userController.register(model, user);
        assertThat(view).isEqualTo("errors/404");
    }

    @Test
    public void whenLoginSuccessThenRedirectToIndexPage() {
        var model = new ConcurrentModel();
        var user = new User(1, "name", "email", "password");
        when(userService.findByEmailAndPassword(anyString(), anyString())).thenReturn(Optional.of(user));
        var httpSession = mock(HttpSession.class);
        var request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(httpSession);
        var view = userController.loginUser(user, model, request);
        assertThat(view).isEqualTo("redirect:/index");
    }

    @Test
    public void whenLoginFailedThenRedirectToLoginPageAndGetErrorMsg() {
        String msg = "Incorrect login or password";
        var model = new ConcurrentModel();
        var user = new User(1, "name", "email", "password");
        when(userService.findByEmailAndPassword(anyString(), anyString())).thenReturn(Optional.empty());
        var httpSession = mock(HttpSession.class);
        var request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(httpSession);
        var view = userController.loginUser(user, model, request);
        var actualMsg = model.getAttribute("error");
        assertThat(view).isEqualTo("users/login");
        assertThat(actualMsg).isEqualTo(msg);
    }

    @Test
    public void whenUserLogoutThenRedirectToLoginPage() {
        var httpSession = mock(HttpSession.class);
        var view = userController.logout(httpSession);
        assertThat(view).isEqualTo("redirect:/users/login");
    }
}