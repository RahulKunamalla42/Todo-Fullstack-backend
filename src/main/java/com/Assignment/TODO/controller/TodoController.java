package com.Assignment.TODO.controller;

import com.Assignment.TODO.Dto.Response;
import com.Assignment.TODO.Dto.TodoDTO;
import com.Assignment.TODO.service.serviceImpl.TodoServiceImpl;
import com.Assignment.TODO.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/user")
public class TodoController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    TodoServiceImpl todoService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<TodoDTO> addTodoForUser(
            @PathVariable Long userId,
            @RequestBody TodoDTO todoDTO) {

        TodoDTO savedTodo = todoService.addTodoForUser(userId, todoDTO);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/todos")
    public ResponseEntity<Response> getUserTodos(@PathVariable Long id) {
        try {
            List<TodoDTO> todos = todoService.getUserTodos(id);
            Response response = new Response(null, "User's todos fetched successfully", null, 200, null, null,todos,null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Response errorResponse = new Response(null, e.getMessage(), null, 404, null, null,null,null);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}/{todoId}")
    public ResponseEntity<Response> deleteTodoForUser(
            @PathVariable Long userId,
            @PathVariable Long todoId) {
        todoService.deleteTodoForUser(userId, todoId);
        return ResponseEntity.ok(new Response(null,"todo is deleted",null,null,null,null,null,null));
    }
    @PutMapping("/{userId}/{todoId}/status")
    public ResponseEntity<TodoDTO> updateTodoStatus(
            @PathVariable Long userId,
            @PathVariable Long todoId,
            @RequestParam boolean isComplete) {
        TodoDTO updatedTodo = todoService.markTodoAsCompleteOrIncomplete(userId, todoId, isComplete);
        return ResponseEntity.ok(updatedTodo);
    }

    @PutMapping("/{userId}/{todoId}/edit")
    public ResponseEntity<TodoDTO> editTodo(
            @PathVariable Long userId,
            @PathVariable Long todoId,
            @RequestBody TodoDTO todoDTO) {
        return ResponseEntity.ok(todoService.editTodo(userId,todoId,todoDTO));
    }
}
