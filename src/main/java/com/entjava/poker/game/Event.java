package com.entjava.poker.game;

import javax.persistence.*;

/**
 * Represents a game event in the poker game.
 * Maps to the events table in the database.
 */
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    // Default constructor required by JPA
    public Event() {}

    public Event(Player player) {
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", player=" + (player != null ? player.getName() : "none") +
                '}';
    }
}