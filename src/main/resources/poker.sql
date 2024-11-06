CREATE DATABASE Poker;
USE Poker;

CREATE TABLE game_event (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            event_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE player (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        is_winner BOOLEAN DEFAULT FALSE,
                        game_event_id BIGINT,
                        FOREIGN KEY (game_event_id) REFERENCES game_event(id)
);

CREATE TABLE hand (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      player_id BIGINT,
                      hand_type VARCHAR(255) NOT NULL,
                      cards VARCHAR(255) NOT NULL,
                      high_card VARCHAR(255),
                      FOREIGN KEY (player_id) REFERENCES player(id)
);

-- Insert a game event record first
INSERT INTO game_event (event_timestamp) VALUES (CURRENT_TIMESTAMP);

-- Now insert player records, referencing the existing game event
INSERT INTO player (name, is_winner, game_event_id) VALUES
                                                        ('Alice', TRUE, 1),
                                                        ('Bob', FALSE, 1),
                                                        ('Charlie', FALSE, NULL);