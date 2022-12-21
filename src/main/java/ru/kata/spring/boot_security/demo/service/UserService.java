package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    UserDetails loadUserByUsername(String username);

    List<User> findAll();
    void saveUser(User user);
    void updateUser(String username, User updateUser);
    void deleteUser(Long id);
}