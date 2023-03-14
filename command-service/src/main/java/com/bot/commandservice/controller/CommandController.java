package com.bot.commandservice.controller;

import com.bot.commandservice.config.FeignProxy;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

//@RestController
//public class CommandController {
//
//
//    @PostMapping("/message")
//    public Mono<Void> handleMessage(@RequestBody Message message) {
//        String token = "MTA4MjQ4MzM2NjA1NTY1MzQ3OA.G3qlyi.jYyrx-2P8I63J3fNtQxJg810KG2CRcm-hrzbLY";
//        GatewayDiscordClient client = DiscordClientBuilder.create(token).build().login().block();
//        FeignProxy feignProxy = null;
//        client.getEventDispatcher().on(MessageCreateEvent.class)
//                .subscribe(event -> {
//                    String content = event.getMessage().getContent();
//                    if (content.startsWith("!todo")) {
//                        // Pass the message to the TodoService via Feign
//                        feignProxy.handleTodoMessage(message).subscribe();
//                    }
//                });
//
//        return Mono.empty();
//    }
//}


