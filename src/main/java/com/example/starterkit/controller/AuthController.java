package com.example.starterkit.controller;

import com.example.starterkit.util.JwtUtil;
import com.example.starterkit.entity.Role;
import com.example.starterkit.entity.User;
import com.example.starterkit.repository.UserRepository;
import com.example.starterkit.repository.RefreshTokenRepository;
import com.example.starterkit.servivce.AuthService;
import com.example.starterkit.servivce.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;	
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
	private final RefreshTokenRepository refreshTokenRepository;
	private final AuthService authService;
	private final JwtUserDetailsService jwtService;

    @PostMapping("/signup")
    public Map<String, String> signup(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        if (userRepository.existsByUsername(username)) {
            return Map.of("error", "Username already exists!");
        }

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(Set.of(Role.ROLE_USER))
                .build();

        userRepository.save(user);
        return Map.of("message", "User registered successfully!");
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        String accessToken = jwtUtil.generateAccessToken(username);
        String refreshToken = jwtUtil.generateRefreshToken(username);

        return Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        );
    }

    @PostMapping("/refresh")
    public Map<String, String> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

          return refreshTokenRepository.findByToken(refreshToken)
            .map(authService::verifyExpiration)
            .map(RefreshToken::getUser)
            .map(user -> {
                String newToken = jwtService.generateTokenFromUsername(user.getUsername());
                return ResponseEntity.ok(Map.of(
                        "accessToken", newToken,
                        "refreshToken", refreshToken
                ));
            })
            .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
    }
}
