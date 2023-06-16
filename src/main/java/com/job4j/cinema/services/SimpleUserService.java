package com.job4j.cinema.services;

import com.job4j.cinema.model.User;
import com.job4j.cinema.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Optional;

@ThreadSafe
@Service
public class SimpleUserService implements UserService {
    private UserRepository userRepository;

    public SimpleUserService(UserRepository sql2oUserRepository) {
        this.userRepository = sql2oUserRepository;
    }

    @Override
    public Optional<User> save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
