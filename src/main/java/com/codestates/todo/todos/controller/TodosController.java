package com.codestates.todo.todos.controller;

import com.codestates.todo.todos.dto.TodosDto;
import com.codestates.todo.todos.entity.Todos;
import com.codestates.todo.todos.mapper.TodosMapper;
import com.codestates.todo.todos.service.TodosService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping
@Slf4j
public class TodosController {
    private final TodosService todosService;
    private final TodosMapper mapper;


    public TodosController(TodosService todosService, TodosMapper mapper) {
        this.todosService = todosService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTodos(@RequestBody TodosDto.Post todosPostDto) {
        Todos todos = mapper.todosPostDtoToTodos(todosPostDto);

        Todos response = todosService.createTodos(todos);

        return new ResponseEntity<>(mapper.todosToTodosResponseDto(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchTodos(@PathVariable("id") @Positive long id,
                                     @Valid @RequestBody TodosDto.Patch todosPatchDto) {
        todosPatchDto.setId(id);

        Todos response = todosService.updateTodos(mapper.todosPatchDtoToTodos(todosPatchDto));

        return new ResponseEntity<>(mapper.todosToTodosResponseDto(response), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity getTodo(@PathVariable("id") @Positive long id) {
        Todos response = todosService.findTodo(id);
        return new ResponseEntity<>(mapper.todosToTodosResponseDto(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTodos() {
        List<Todos> todos = todosService.findTodos();

        List<TodosDto.Response> response =
                todos.stream()
                        .map(todo -> mapper.todosToTodosResponseDto(todo))
                        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo(@PathVariable("id") @Positive long id) {
        System.out.println("# delete todo");

        todosService.deleteTodo(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteTodos() {
        System.out.println("# delete todos");

        todosService.deleteTodos();

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
