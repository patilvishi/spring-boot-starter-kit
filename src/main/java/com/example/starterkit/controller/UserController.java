package com.example.starterkit.controller;

import com.example.starterkit.entity.UserEntity;
import com.example.starterkit.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserEntity> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UserEntity one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public UserEntity create(@RequestBody UserEntity user) {
        return service.save(user);
    }

    @PutMapping("/{id}")
    public UserEntity update(@PathVariable Long id, @RequestBody UserEntity user) {
        return service.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
	// ✅ Paginated list of all users
    @GetMapping("/paged")
    public Page<User> getPagedUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    // ✅ Search users by name (with pagination)
    @GetMapping("/search")
    public Page<User> searchByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findByNameContaining(name, pageable);
    }
}
