package com.bot.todoservice.controller;

import com.bot.todoservice.model.Todo;
import com.bot.todoservice.model.User;
import com.bot.todoservice.repo.TodoRepository;
import com.bot.todoservice.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class Controller {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TodoRepository todoRepository;

    @GetMapping("/retrieve/{discord_id}")
    public List<String> retrieveExchangeValue(@PathVariable String discord_id) {
        List<Todo> todos = todoRepository.findTodosByDiscordId(discord_id);
        if (todos == null || todos.size() == 0 ) {
            return Collections.singletonList("None");
        }
        return todos.stream().map(Todo::getTodo).toList();
    }

    @PostMapping("/add/{discord_id}/{message}")
    public String handleTodoAdd(@PathVariable String discord_id, @PathVariable String message) {
        userRepository.save(new User(discord_id));
        todoRepository.save(new Todo(discord_id, message));
        return "Added";
    }

    @PostMapping("/remove/{discord_id}/{message}")
    public String handleRemove(@PathVariable String discord_id, @PathVariable String message) {
        List<Todo> todos = todoRepository.findTodosByDiscordId(discord_id);
        for (Todo todo: todos) {
            if (todo.getTodo().equals(message) && todo.getDiscord_id().equals(discord_id)) {
                todoRepository.delete(todo);
            }
        }
        return "Removed";
    }
}