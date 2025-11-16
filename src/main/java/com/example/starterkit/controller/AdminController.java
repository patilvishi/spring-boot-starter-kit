package com.example.starterkit.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/dashboard")
	public ResponseEntity<ApiResponse<String>> dashboard() {

    ApiResponse<String> response = ApiResponse.<String>builder()
            .success(true)
            .message("Admin dashboard loaded")
            .data("Welcome Admin")
            .timestamp(LocalDateTime.now())
            .build();

    return ResponseEntity.ok(response);
	}
}
