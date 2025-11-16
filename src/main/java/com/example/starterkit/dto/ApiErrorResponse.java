package com.example.starterkit.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiErrorResponse {

    private boolean success;
    private String error;
    private int status;
    private LocalDateTime timestamp;
}
