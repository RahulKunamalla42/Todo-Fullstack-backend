package com.Assignment.TODO.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Response(
        String token,
        String message,
        String role,
        Integer status,
        UserDTO userDTO,
        List<UserDTO> userDtos,
        List<TodoDTO> todoDTOS,
        Long idofuser
) {
}
