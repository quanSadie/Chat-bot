package com.bot.todoservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "todo_name")
    private String todo;
    @Column(name = "discord_id")
    private String discord_id;

    public Todo(String discord_id, String todo) {
        this.todo = todo;
        this.discord_id = discord_id;
    }
}
