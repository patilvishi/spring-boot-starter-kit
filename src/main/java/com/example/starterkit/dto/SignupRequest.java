package com.example.starterkit.dto;

import com.example.starterkit.entity.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private Role role; // 👈 Add role
}
