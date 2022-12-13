package com.codestates.todo.todos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class TodosDto {

    @Getter
    @Setter
    public static class Post {

        private String title;

        private long todo_order;

        private boolean completed;
    }

    @Getter
    @Setter
    public static class Patch{

        private long id;

        private String title;

        private long todo_order;

        private boolean completed;

    }

    @Getter
    @AllArgsConstructor
    public static class Response {

        private long id;

        private String title;

        private long todo_order;

        private boolean completed;
    }
}
