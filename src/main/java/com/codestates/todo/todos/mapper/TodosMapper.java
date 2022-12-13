package com.codestates.todo.todos.mapper;

import com.codestates.todo.todos.dto.TodosDto;
import com.codestates.todo.todos.entity.Todos;
import org.springframework.stereotype.Component;

@Component
public class TodosMapper {

    public Todos todosPostDtoToTodos(TodosDto.Post todosPostDto) {
        return new Todos(0L,
                todosPostDto.getTitle(),
                todosPostDto.getTodo_order(),
                todosPostDto.isCompleted());
    }

    public Todos todosPatchDtoToTodos(TodosDto.Patch todosPatchDto) {
        return new Todos(todosPatchDto.getId(),
                todosPatchDto.getTitle(),
                todosPatchDto.getTodo_order(),
                todosPatchDto.isCompleted());
    }
    public TodosDto.Response todosToTodosResponseDto(Todos todos) {
        return new TodosDto.Response(todos.getId(),
                todos.getTitle(),
                todos.getTodo_order(),
                todos.isCompleted());
    }
}
