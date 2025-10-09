package com.example.starterkit.controller;

import com.example.starterkit.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @Data
    static class LoginRequest {
        private String username;
        private String password;
    }

    @Data
    @AllArgsConstructor
    static class TokenResponse {
        private String token;
    }
}
