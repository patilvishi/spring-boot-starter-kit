package com.example.starterkit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public List<String> getUsers() {
        // Simple mock data for Day 2
        return List.of("Alice", "Bob", "Charlie");
    }
}
