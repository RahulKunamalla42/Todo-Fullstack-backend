package com.Assignment.TODO.service.serviceImpl;

import com.Assignment.TODO.Dto.LoginRequest;
import com.Assignment.TODO.Dto.Response;
import com.Assignment.TODO.Dto.Role;
import com.Assignment.TODO.Dto.UserDTO;
import com.Assignment.TODO.entity.User;
import com.Assignment.TODO.exception.OurException;
import com.Assignment.TODO.repo.UserRepo;
import com.Assignment.TODO.security.JwtService;
import com.Assignment.TODO.service.AuthService;
import com.Assignment.TODO.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserServiceImpl userService;

    @Override
    public UserDTO register(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (userService.isUsernameTaken(user.getUsername())) {
            throw new OurException("Username is already taken", 400);
        }
        if (userService.isEmailTaken(user.getEmail())) {
            throw new OurException("Email is already taken", 400);
        }

        // Set default role to USER
        user.setRole(Role.USER);

        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User save = userRepo.save(user);

        return modelMapper.userToUserDto(save);
    }

    @Override
    public Response login(LoginRequest loginRequest) {
        if (loginRequest == null) {
            throw new IllegalArgumentException("Login request cannot be null");
        }

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );

        if (authenticate.isAuthenticated()) {
            User principal = (User) authenticate.getPrincipal();
            String token = jwtService.GenarateToken(principal);
            return new Response(token, "Token created successfully", principal.getRole().name(), 200, null, null,null,principal.getUserid());
        }

        throw new OurException("Invalid username or password", 401);
    }
}
