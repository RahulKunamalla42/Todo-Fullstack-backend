package com.Assignment.TODO.service;

import com.Assignment.TODO.Dto.TodoDTO;

import java.util.List;

public interface TodoService {

    TodoDTO addTodoForUser(Long userId, TodoDTO todoDTO);
    List<TodoDTO> getUserTodos(Long userid);
    void deleteTodoForUser(Long userId, Long todoId);
    TodoDTO markTodoAsCompleteOrIncomplete(Long userId, Long todoId, boolean isComplete);
    TodoDTO editTodo(Long userId, Long todoId, TodoDTO todoDTO);

}
