package com.bot.commandservice.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@FeignClient(name = "AI-Service")
public interface FeignClientAI {
    @GetMapping("/answer/{question}")
    public String getAnswer(@PathVariable String question) throws IOException;
}
