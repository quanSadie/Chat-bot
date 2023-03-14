package com.bot.commandservice.config;

import com.bot.commandservice.listener.EventListener;
import com.bot.commandservice.listener.MessageListener;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BotConfig {

    Logger logger = LoggerFactory.getLogger(BotConfig.class);
    @Value("${token}")
    private String token;

    @Bean
    public <T extends Event> GatewayDiscordClient gatewayDiscordClient(List<EventListener<T>> eventListeners) throws InterruptedException {
        GatewayDiscordClient client = DiscordClientBuilder.create(token)
                .build()
                .login()
                .block();
        System.out.println("Test cmd");
        for(EventListener<T> listener : eventListeners) {
            System.out.println("Test log");
            client.on(listener.getEventType())
                    .flatMap(listener::execute)
                    .onErrorResume(listener::handleError)
                    .subscribe();
        }
        return client;
    }
}
