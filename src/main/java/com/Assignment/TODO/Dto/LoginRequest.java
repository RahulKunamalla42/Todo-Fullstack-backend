package com.Assignment.TODO.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "user name is required")
        String username,
        @NotBlank(message = "password is required")
        @Size(min = 8,message = "password must contain 8 characters")
        String password
) {
}
