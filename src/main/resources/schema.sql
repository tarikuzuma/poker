-- Players table: Stores information about registered players.
CREATE TABLE players (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    hand VARCHAR(255),  -- Stores the hand description, e.g., "One Pair (J) - A,9,6 High"
    is_winner BOOLEAN DEFAULT FALSE
);

-- Game Events table: Stores information about each game event.
CREATE TABLE events (
    id SERIAL PRIMARY KEY,
    player_id INT REFERENCES players(id)
);