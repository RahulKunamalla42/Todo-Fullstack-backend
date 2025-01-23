package com.Assignment.TODO.service;

import com.Assignment.TODO.Dto.Role;
import com.Assignment.TODO.Dto.TodoDTO;
import com.Assignment.TODO.Dto.UserDTO;
import com.Assignment.TODO.entity.Todo;
import com.Assignment.TODO.entity.User;
import java.util.List;

public interface UserService {
    UserDTO getUserById(Long userid);
    UserDTO getUserByUsername(String username);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(User user);
    UserDTO deleteUser(Long userid);
    UserDTO assignRole(Long userid, Role role);
    boolean isUsernameTaken(String username);
    boolean isEmailTaken(String email);
    UserDTO getUserProfile(Long userid);
    List<UserDTO> searchUsers(String username, Role role);
}

