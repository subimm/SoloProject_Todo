package com.codestates.todo.todos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Todos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동생성
    private long id;

    @Column
    private String title;

    @Column(nullable = false, unique = true)
    private long todo_order;

    @Column(nullable = false)
    private boolean completed;

    public Todos(String title, long todo_order, boolean completed) {
        this.title = title;
        this.todo_order = todo_order;
        this.completed = completed;
    }
}
