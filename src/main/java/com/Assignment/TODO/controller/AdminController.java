package com.Assignment.TODO.controller;

import com.Assignment.TODO.Dto.Response;
import com.Assignment.TODO.Dto.Role;
import com.Assignment.TODO.Dto.UserDTO;
import com.Assignment.TODO.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/admin")
public class AdminController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/all")
    public ResponseEntity<Response> getAllUsers() {
        try {
            List<UserDTO> users = userService.getAllUsers();
            Response response = new Response(null, "Users fetched successfully", null, 200, null, users,null,null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Response errorResponse = new Response(null, e.getMessage(), null, 500, null, null,null,null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable Long id) {
        try {
            UserDTO deletedUser = userService.deleteUser(id);
            Response response = new Response(null, "User deleted successfully", null, 200, deletedUser, null,null,null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Response errorResponse = new Response(null, e.getMessage(), null, 404, null, null,null,null);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/assign-role/{id}")
    public ResponseEntity<Response> assignRole(@PathVariable Long id, @RequestBody Role role) {
        try {
            UserDTO userWithRole = userService.assignRole(id, role);
            Response response = new Response(null, "Role assigned successfully", null, 200, userWithRole, null,null,null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Response errorResponse = new Response(null, e.getMessage(), null, 404, null, null,null,null);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/search")
    public ResponseEntity<Response> searchUsers(@RequestParam String username, @RequestParam Role role) {
        try {
            List<UserDTO> users = userService.searchUsers(username, role);
            Response response = new Response(null, "Users fetched successfully", null, 200, null, users,null,null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Response errorResponse = new Response(null, e.getMessage(), null, 404, null, null,null,null);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

}
