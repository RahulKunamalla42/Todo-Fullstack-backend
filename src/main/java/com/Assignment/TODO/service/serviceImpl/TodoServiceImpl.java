package com.Assignment.TODO.service.serviceImpl;

import com.Assignment.TODO.Dto.TodoDTO;
import com.Assignment.TODO.entity.Todo;
import com.Assignment.TODO.entity.User;
import com.Assignment.TODO.exception.OurException;
import com.Assignment.TODO.repo.TodoRepo;
import com.Assignment.TODO.repo.UserRepo;
import com.Assignment.TODO.service.TodoService;
import com.Assignment.TODO.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    TodoRepo todoRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public TodoDTO addTodoForUser(Long userId, TodoDTO todoDTO) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new OurException("User not found", 404));
        Todo todo = modelMapper.todoDtoToTodo(todoDTO);
        todo.setUser(user);
        Todo savedTodo = todoRepo.save(todo);
        return modelMapper.todoToTodoDTO(savedTodo);
    }

    @Override
    public List<TodoDTO> getUserTodos(Long userid) {
        User user = userRepo.findById(userid)
                .orElseThrow(() -> new OurException("User not found in getUserTodos", 404));
        return modelMapper.mapTodosToTodoDTOs(user.getUserTodos());
    }

    @Override
    public void deleteTodoForUser(Long userId, Long todoId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new OurException("User not found", 404));
        Todo todo = todoRepo.findById(todoId)
                .orElseThrow(() -> new OurException("Todo not found", 404));
        if (!todo.getUser().getUserid().equals(userId)) {
            throw new OurException("Todo does not belong to the user", 403);
        }
        todoRepo.delete(todo);
    }

    @Override
    public TodoDTO markTodoAsCompleteOrIncomplete(Long userId, Long todoId, boolean isComplete) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new OurException("User not found", 404));
        Todo todo = todoRepo.findById(todoId)
                .orElseThrow(() -> new OurException("Todo not found", 404));
        if (!todo.getUser().getUserid().equals(userId)) {
            throw new OurException("Todo does not belong to the user", 403);
        }
        todo.setComplete(isComplete);
        Todo updatedTodo = todoRepo.save(todo);
        return modelMapper.todoToTodoDTO(updatedTodo);
    }

    @Override
    public TodoDTO editTodo(Long userId, Long todoId, TodoDTO todoDTO) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new OurException("User not found", 404));

        Todo todo = todoRepo.findById(todoId)
                .orElseThrow(() -> new OurException("Todo not found", 404));

        if (!todo.getUser().getUserid().equals(userId)) {
            throw new OurException("Todo does not belong to the user", 403);
        }

        todo.setTodotitle(todoDTO.todotitle());
        todo.setTododescription(todoDTO.tododescription());
        todo.setComplete(todoDTO.isCompleted());
        todo.setUpdatedAt(LocalDateTime.now());
        Todo updatedTodo = todoRepo.save(todo);
       return modelMapper.todoToTodoDTO(updatedTodo);
    }

}
