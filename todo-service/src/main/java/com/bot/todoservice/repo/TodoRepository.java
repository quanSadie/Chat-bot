package com.bot.todoservice.repo;

import com.bot.todoservice.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("SELECT t FROM Todo t WHERE t.discord_id = :discordId")
    List<Todo> findTodosByDiscordId(@Param("discordId") String discordId);
}
