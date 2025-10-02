package com.example.starterkit.service;

import com.example.starterkit.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Long id);
    Page<User> getAllUsers(Pageable pageable);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    Page<User> searchByName(String name, Pageable pageable);
}
