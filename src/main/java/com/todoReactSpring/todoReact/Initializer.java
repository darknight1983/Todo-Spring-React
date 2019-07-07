package com.todoReactSpring.todoReact;

import com.todoReactSpring.todoReact.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;


@Component
public class Initializer implements CommandLineRunner {

    private final TodoRepository todoRepository;

    @Autowired
    public Initializer(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void run(String... strings) {

        Stream.of("Learn the Spring Framework", "Learn Java...and More Java", "Keep up with Javascript")
                .forEach(task -> todoRepository.save(new Todo(task)));

        todoRepository.findAll().forEach(System.out::println);

    }
}
