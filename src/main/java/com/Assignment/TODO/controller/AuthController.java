package com.Assignment.TODO.controller;

import com.Assignment.TODO.Dto.LoginRequest;
import com.Assignment.TODO.Dto.Response;
import com.Assignment.TODO.Dto.UserDTO;
import com.Assignment.TODO.entity.User;
import com.Assignment.TODO.repo.UserRepo;
import com.Assignment.TODO.service.serviceImpl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody User user) {
        try {
            UserDTO registeredUser = authService.register(user);
            Response response = new Response(null, "User registered successfully", null, 201, registeredUser, null,null,null);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Response errorResponse = new Response(null, e.getMessage(), null, 400, null, null,null,null);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) {
        try {
            Response response = authService.login(loginRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Response errorResponse = new Response(null, e.getMessage(), null, 401, null, null,null,null);
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/home")
    public ResponseEntity<Response> home() {
        Response response = new Response(null, "Welcome to the public home page!", null, 200, null, null,null,null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
