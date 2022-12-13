package com.codestates.todo.todos.repository;

import com.codestates.todo.todos.entity.Todos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodosRepository extends JpaRepository<Todos, Long> {
}
