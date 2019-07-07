package com.todoReactSpring.todoReact.controllers;

import com.todoReactSpring.todoReact.TodoRepository;
import com.todoReactSpring.todoReact.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class TodoController {

    private TodoRepository todoRepository;
    private final Logger log = LoggerFactory.getLogger(Todo.class);

    @Autowired
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/todos")
    Collection<Todo> getTodos() {
       return (Collection<Todo>) todoRepository.findAll();
    }

    @GetMapping("/todo/{id}")
    ResponseEntity<?> getTodo(@PathVariable Long id) {
        Optional<Todo> result = todoRepository.findById(id);
        return result.map(data -> ResponseEntity.ok().body(data))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/todo")
    ResponseEntity<Todo> newTodo(@Valid @RequestBody Todo todo) throws URISyntaxException {
        log.info("Request to create todo: {}", todo);
        Todo savedTodo = todoRepository.save(todo);
        return ResponseEntity.created(new URI("/api/todo/" + todo.getId()))
                .body(savedTodo);
    }

    @PutMapping("/todo/{id}")
    ResponseEntity<Todo> updateTodo(@Valid @RequestBody Todo todo) {
       log.info("Request to update todo: {}", todo);
       Todo updatedTodo = todoRepository.save(todo);
       return ResponseEntity.ok().body(updatedTodo);
    }

    @DeleteMapping("/todo/{id}")
    ResponseEntity<?> deleteTodo(@PathVariable Long id) {
        log.info("Request to delete todo: {}", id);
        todoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
