package com.Assignment.TODO.Dto;

import java.time.LocalDateTime;
import java.util.List;

public record UserDTO(
        Long id,
        String username,
        String email,
        Role role,
        List<TodoDTO> usertodos,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
