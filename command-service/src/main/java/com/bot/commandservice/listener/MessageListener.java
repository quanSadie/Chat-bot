package com.bot.commandservice.listener;

import com.bot.commandservice.config.FeignClientAI;
import com.bot.commandservice.config.FeignProxy;
import discord4j.core.object.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

public abstract class MessageListener {
    @Autowired
    private FeignProxy feignProxy;

    @Autowired
    private FeignClientAI feignClientAI;

    public Mono<Void> processCommand(Message eventMessage) {
        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map((user -> !user.isBot())).orElse(false))
                .flatMap(message -> {
                    String content = message.getContent();
                    String discord_id = String.valueOf(message.getAuthor().get().getId());
                    if (content.equals("!todo")) {
                        // TODO: Pass data to todo-service
                        List<String> rs = feignProxy.retrieveExchangeValue(discord_id);
                        // this return statement is just for testing purposes
                        StringBuilder rt = new StringBuilder();
                        rt.append("Todos list of > ").append(message.getAuthor().get().getUsername()).append(" <:\n");
                        for (String s : rs) {
                            rt.append("- ").append(s).append("\n");
                        }
                        return message.getChannel().flatMap(channel ->
                                channel.createMessage(String.valueOf(rt)));
                    } else if (content.startsWith("!todo_add")) {
                        String todo_add = content.substring(10); // extract
                        feignProxy.handleTodoAdd(discord_id,todo_add);
                        return message.getChannel().flatMap(channel ->
                                channel.createMessage(todo_add + " has been added into the todo list!"));
                    } else if (content.startsWith("!todo_done")) {
                        String todo_id  = content.substring(11);
                        feignProxy.handleRemove(discord_id,todo_id);
                        return message.getChannel().flatMap(channel ->
                                channel.createMessage(todo_id + " has been removed from the todo list!"));
                    } else if (content.startsWith("ai!ask")) {
                        String question  = content.substring(7);
                        String answer = "No idea!";
                        try {
                            answer = feignClientAI.getAnswer(question);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        String finalAnswer = answer;
                        return message.getChannel().flatMap(channel ->
                                channel.createMessage(finalAnswer));
                    } else {
                        return Mono.empty();
                    }
                })
                .then();
    }
}
