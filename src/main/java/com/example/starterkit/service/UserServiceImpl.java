package com.example.starterkit.service.impl;

import com.example.starterkit.entity.User;
import com.example.starterkit.exception.ResourceNotFoundException;
import com.example.starterkit.repository.UserRepository;
import com.example.starterkit.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.access.prepost.PostAuthorize;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User createUser(User user) {
        user.setId(null);
        return repo.save(user);
    }

    @Override
	@PostAuthorize("returnObject.username == authentication.name or hasRole('ADMIN')")
    public Optional<User> getUserById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public User updateUser(Long id, User user) {
        User existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        return repo.save(existing);
    }

    @Override
    public void deleteUser(Long id) {
        User existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        repo.delete(existing);
    }

    @Override
    public Page<User> searchByName(String name, Pageable pageable) {
        return repo.findByNameContaining(name, pageable);
    }
	
	@Override
    public Optional<User> findByUsername(String username) {
        return repo.findByUsername(username);
    }
}
