package com.Assignment.TODO.service.serviceImpl;

import com.Assignment.TODO.Dto.Role;
import com.Assignment.TODO.Dto.TodoDTO;
import com.Assignment.TODO.entity.Todo;
import com.Assignment.TODO.repo.TodoRepo;
import com.Assignment.TODO.service.UserService;
import com.Assignment.TODO.entity.User;
import com.Assignment.TODO.Dto.UserDTO;
import com.Assignment.TODO.repo.UserRepo;
import com.Assignment.TODO.exception.OurException;
import com.Assignment.TODO.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    TodoRepo todoRepo;

    @Override
    public UserDTO getUserById(Long userid) {
        User user = userRepo.findById(userid)
                .orElseThrow(() -> new OurException("User not found", 404));
        return modelMapper.userToUserDto(user);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new OurException("User not found", 404));
        return modelMapper.userToUserDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return modelMapper.usersToUserDtos(users);
    }

    @Override
    public UserDTO updateUser(User user) {
        user.setRole(Role.USER);
        User existingUser = userRepo.findById(user.getUserid())
                .orElseThrow(() -> new OurException("User not found", 404));

        user.setUserTodos(existingUser.getUserTodos());

        User updatedUser = userRepo.save(user);
        return modelMapper.userToUserDto(updatedUser);
    }


    @Override
    public UserDTO deleteUser(Long userid) {
        User user = userRepo.findById(userid)
                .orElseThrow(() -> new OurException("User not found", 404));
        userRepo.delete(user);
        return modelMapper.userToUserDto(user);
    }



    @Override
    public UserDTO assignRole(Long userid, Role role) {
        User user = userRepo.findById(userid)
                .orElseThrow(() -> new OurException("User not found in assign role", 404));
        user.setRole(role);
        User updatedUser = userRepo.save(user);
        return modelMapper.userToUserDto(updatedUser);
    }


    @Override
    public boolean isUsernameTaken(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public boolean isEmailTaken(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public UserDTO getUserProfile(Long userid) {
        User user = userRepo.findById(userid)
                .orElseThrow(() -> new OurException("User not found in userprofile", 404));
        return modelMapper.userToUserDto(user);
    }

    @Override
    public List<UserDTO> searchUsers(String username, Role role) {
        List<User> users = userRepo.findByUsernameAndRole(username, role);
        if (users.isEmpty()) {
            throw new OurException("User not found in search users", 404);
        }

        return modelMapper.usersToUserDtos(users);
    }

}
