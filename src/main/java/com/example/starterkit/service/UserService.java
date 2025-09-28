package com.example.starterkit.service;

import com.example.starterkit.entity.UserEntity;
import com.example.starterkit.exception.ResourceNotFoundException;
import com.example.starterkit.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<UserEntity> findAll() {
        return repo.findAll();
    }

    public UserEntity findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
    }

    public UserEntity save(UserEntity user) {
        return repo.save(user);
    }

    public UserEntity update(Long id, UserEntity user) {
        UserEntity existing = findById(id);
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        return repo.save(existing);
    }

    public void delete(Long id) {
        UserEntity existing = findById(id);
        repo.delete(existing);
    }
}
