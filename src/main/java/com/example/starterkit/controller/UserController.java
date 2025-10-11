package com.example.starterkit.controller;

import com.example.starterkit.dto.UserRequest;
import com.example.starterkit.dto.UserResponse;
import com.example.starterkit.entity.User;
import com.example.starterkit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create
	@Operation(summary = "Create new user", description = "Register a new user in the system")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest req) {
        User saved = userService.createUser(toEntity(req));
        return ResponseEntity.status(201).body(toResponse(saved));
    }

    // Read one
	@Operation(summary = "Get user by ID", description = "Fetch a specific user by their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getOne(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(u -> ResponseEntity.ok(toResponse(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Read paged
	@Operation(summary = "Get all users", description = "Fetch all registered users from the database")
    @ApiResponse(responseCode = "200", description = "List of users retrieved successfully")
    @GetMapping
    public ResponseEntity<Page<UserResponse>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pr = PageRequest.of(page, size);
        Page<User> users = userService.getAllUsers(pr);
        Page<UserResponse> resp = users.map(this::toResponse);
        return ResponseEntity.ok(resp);
    }

    // Update
	@Operation(summary = "Update user", description = "Update existing user details")
    @ApiResponse(responseCode = "200", description = "User updated successfully")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @Valid @RequestBody UserRequest req) {
        User updated = userService.updateUser(id, toEntity(req));
        return ResponseEntity.ok(toResponse(updated));
    }

    // Delete
	@Operation(summary = "Delete user", description = "Delete a user by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Search by name (paged)
    @GetMapping("/search")
    public ResponseEntity<Page<UserResponse>> search(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageRequest pr = PageRequest.of(page, size);
        Page<User> users = userService.searchByName(name, pr);
        Page<UserResponse> resp = users.map(this::toResponse);
        return ResponseEntity.ok(resp);
    }
	
	@GetMapping("/profile")
    public String userProfile() {
        return "Welcome, User 👋 This is your profile.";
    }

    // Converters
    private User toEntity(UserRequest req) {
        return User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .build();
    }

    private UserResponse toResponse(User u) {
        return UserResponse.builder()
                .id(u.getId())
                .name(u.getName())
                .email(u.getEmail())
                .build();
    }
}
