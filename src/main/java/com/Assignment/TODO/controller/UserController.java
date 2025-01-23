package com.Assignment.TODO.controller;

import com.Assignment.TODO.Dto.Response;
import com.Assignment.TODO.Dto.UserDTO;
import com.Assignment.TODO.entity.User;
import com.Assignment.TODO.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity<Response> getUserById(@PathVariable Long id) {
        try {
            UserDTO user = userService.getUserById(id);
            Response response = new Response(null, "User fetched successfully", null, 200, user, null,null,null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Response errorResponse = new Response(null, e.getMessage(), null, 404, null, null,null,null);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Response> getUserByUsername(@PathVariable String username) {
        try {
            UserDTO user = userService.getUserByUsername(username);
            Response response = new Response(null, "User fetched successfully", null, 200, user, null,null,null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Response errorResponse = new Response(null, e.getMessage(), null, 404, null, null,null,null);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/profile/{id}")
    public ResponseEntity<Response> getUserProfile(@PathVariable Long id) {
        try {
            UserDTO profile = userService.getUserProfile(id);
            Response response = new Response(null, "User profile fetched successfully", null, 200, profile, null,null,null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Response errorResponse = new Response(null, e.getMessage(), null, 404, null, null,null,null);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateUser(@RequestBody User user,@PathVariable Long id) {
        try {
            user.setUserid(id);
            UserDTO updatedUser = userService.updateUser(user);
            Response response = new Response(null, "User updated successfully", null, 200, updatedUser, null,null,null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Response errorResponse = new Response(null, e.getMessage(), null, 404, null, null,null,null);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }


}
