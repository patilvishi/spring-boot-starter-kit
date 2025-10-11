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
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
	if (userRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
		return ResponseEntity.badRequest().body("Username already exists!");
	}

	User user = User.builder()
			.username(signupRequest.getUsername())
			.email(signupRequest.getEmail())
			.password(passwordEncoder.encode(signupRequest.getPassword()))
			.role(signupRequest.getRole())  // 👈 Role from request
			.build();

	userRepository.save(user);
	return ResponseEntity.ok("User registered successfully!");
	}

}
