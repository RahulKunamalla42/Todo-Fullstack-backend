package com.Assignment.TODO.repo;

import com.Assignment.TODO.Dto.Role;
import com.Assignment.TODO.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    List<User> findByUsernameAndRole(String username, Role role );

    Optional<User> findByUsername(String username);
}
