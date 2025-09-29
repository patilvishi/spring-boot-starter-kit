package com.example.starterkit.repository;

import com.example.starterkit.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    // Built-in pagination & sorting
    Page<User> findAll(Pageable pageable);

    // Custom name search with pagination
    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<User> findByNameContaining(@Param("name") String name, Pageable pageable);
}
