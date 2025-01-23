package com.Assignment.TODO.util;

import com.Assignment.TODO.Dto.TodoDTO;
import com.Assignment.TODO.Dto.UserDTO;
import com.Assignment.TODO.entity.User;
import com.Assignment.TODO.entity.Todo;
import com.Assignment.TODO.Dto.LoginRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapper {

    public UserDTO userToUserDto(User user) {
        return new UserDTO(
                user.getUserid(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                mapTodosToTodoDTOs(user.getUserTodos()),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public List<TodoDTO> mapTodosToTodoDTOs(List<Todo> todos) {
        return todos.stream()
                .map(todo -> new TodoDTO(
                        todo.getTodoid(),
                        todo.getTodotitle(),
                        todo.getTododescription(),
                        todo.isComplete(),
                        todo.getCreatedAt(),
                        todo.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    // Convert a list of User entities to a list of UserDTOs
    public List<UserDTO> usersToUserDtos(List<User> users) {
        return users.stream()
                .map(this::userToUserDto) // Use userToUserDto method to convert each User
                .collect(Collectors.toList());
    }

    public Todo todoDtoToTodo(TodoDTO todoDTO) {
        Todo todo = new Todo();
        todo.setTodoid(todoDTO.todoid());
        todo.setTodotitle(todoDTO.todotitle());
        todo.setTododescription(todoDTO.tododescription());
        todo.setComplete(todoDTO.isCompleted());
        todo.setCreatedAt(todoDTO.createdAt());
        todo.setUpdatedAt(todoDTO.updatedAt());
        return todo;
    }

    public User userDtoToUser(UserDTO userDTO) {
        User user = new User();
        user.setUserid(userDTO.id());
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setRole(userDTO.role());
        user.setCreatedAt(userDTO.createdAt());
        user.setUpdatedAt(userDTO.updatedAt());
        user.setUserTodos(mapTodoDTOsToTodos(userDTO.usertodos()));
        return user;
    }

    public List<Todo> mapTodoDTOsToTodos(List<TodoDTO> todoDTOs) {
        return todoDTOs.stream()
                .map(todoDTO -> new Todo(
                        todoDTO.todoid(),
                        todoDTO.todotitle(),
                        todoDTO.tododescription(),
                        todoDTO.isCompleted(),
                        null,
                        todoDTO.createdAt(),
                        todoDTO.updatedAt()
                ))
                .collect(Collectors.toList());
    }

    public User loginRequestToUser(LoginRequest loginRequest) {
        User user = new User();
        user.setUsername(loginRequest.username());
        user.setPassword(loginRequest.password());
        return user;
    }

    // This is the missing method you need
    public TodoDTO todoToTodoDTO(Todo todo) {
        return new TodoDTO(
                todo.getTodoid(),
                todo.getTodotitle(),
                todo.getTododescription(),
                todo.isComplete(),
                todo.getCreatedAt(),
                todo.getUpdatedAt()
        );
    }
}
