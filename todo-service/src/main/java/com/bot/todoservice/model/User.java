package com.bot.todoservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "discord_user")
public class User {
    @Id
    private String discord_id;

    public User(String discord_id) {
        this.discord_id = discord_id;
    }

    @OneToMany(mappedBy = "discord_id", fetch = FetchType.LAZY)
    private List<Todo> todos = new ArrayList<>();
}