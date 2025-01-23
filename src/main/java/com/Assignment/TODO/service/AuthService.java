package com.Assignment.TODO.service;

import com.Assignment.TODO.Dto.LoginRequest;
import com.Assignment.TODO.Dto.Response;
import com.Assignment.TODO.Dto.UserDTO;
import com.Assignment.TODO.entity.User;

public interface AuthService {
    UserDTO register(User user);
    Response login(LoginRequest loginRequest);
}
