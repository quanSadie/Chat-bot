package com.bot.commandservice.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@FeignClient(name = "todo-service")
public interface FeignProxy {
    @PostMapping("/todo/add/{discord_id}/{message}")
    public String handleTodoAdd(@PathVariable String discord_id, @PathVariable String message);
    @PostMapping("/todo/remove/{discord_id}/{message}")
    public String handleRemove(@PathVariable String discord_id, @PathVariable String message);

    @GetMapping("/todo/retrieve/{discord_id}")
    public List<String> retrieveExchangeValue(@PathVariable String discord_id);
}