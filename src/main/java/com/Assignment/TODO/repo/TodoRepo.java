package com.Assignment.TODO.repo;

import com.Assignment.TODO.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<Todo,Long> {
}
