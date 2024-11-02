package com.entjava.poker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "is_winner", columnDefinition = "boolean default false")
    private boolean isWinner;

    @ManyToOne
    @JoinColumn(name = "game_event_id")
    private GameEvent gameEvent;

    // No-argument constructor
    public Player() {
        // Default constructor
    }

    public Player(String name) {
        this.name = name;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    @Override
    public String toString() {
        return name;
    }
}
