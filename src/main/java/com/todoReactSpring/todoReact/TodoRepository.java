package com.todoReactSpring.todoReact;

import com.todoReactSpring.todoReact.models.Todo;
import org.springframework.data.repository.CrudRepository;


// What is the difference between JpaRepository and CrudRepository??

public interface TodoRepository extends CrudRepository<Todo, Long> {
}
