package com.codestates.todo.todos.service;

import com.codestates.todo.todos.entity.Todos;
import com.codestates.todo.todos.repository.TodosRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.util.List;
import java.util.ListResourceBundle;
import java.util.Optional;

@Service
public class TodosService {
    private final TodosRepository todosRepository;

    public TodosService(TodosRepository todosRepository) {
        this.todosRepository = todosRepository;
    }

    public Todos createTodos(Todos todos) {
        return todosRepository.save(todos);
    }

    public Todos updateTodos(Todos todos) {
        Todos findTodo = findVerifyTodos(todos.getId());

        Optional.ofNullable(todos.getTitle())
                .ifPresent(title -> findTodo.setTitle(title));
        Optional.ofNullable(todos.isCompleted())
                .ifPresent(complete -> findTodo.setCompleted(complete));

        return todosRepository.save(findTodo);

    }

    public Todos findTodo(long id) {
        return findVerifyTodos(id);
    }

    public List<Todos> findTodos() {
        return todosRepository.findAll(Sort.by("id").descending());
    }

    public void deleteTodo(long id) {
        Todos findTodos = findVerifyTodos(id);

        todosRepository.delete(findTodos);
    }

    public void deleteTodos() {
    todosRepository.deleteAll();
    }

    public Todos findVerifyTodos(long id) {
        Optional<Todos> optionalTodos =
                todosRepository.findById(id);
        Todos findTodos =
                optionalTodos.orElseThrow(() -> new RuntimeException("Not found Todos"));

        return findTodos;
    }
}
