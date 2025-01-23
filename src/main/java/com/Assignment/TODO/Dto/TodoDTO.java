package com.Assignment.TODO.Dto;

import java.time.LocalDateTime;

public record TodoDTO(
        Long todoid,
        String todotitle,
        String tododescription,
        boolean isCompleted,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
