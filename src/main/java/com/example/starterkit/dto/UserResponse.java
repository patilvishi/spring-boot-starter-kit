package com.example.starterkit.dto;

import com.example.starterkit.entity.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
	 private Set<Role> roles;
}
