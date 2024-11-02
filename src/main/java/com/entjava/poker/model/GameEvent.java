package com.entjava.poker.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="game_event")
public class GameEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "event_timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime eventTimestamp;

    public GameEvent() {
        // Default Constructor
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public LocalDateTime getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(LocalDateTime eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    @Override
    public String toString() {
        return "GameEvent [id=" + id + ", eventTimestamp=" + eventTimestamp + "]";
    }
}
