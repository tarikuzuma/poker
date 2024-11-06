package com.entjava.poker.repository;

// There's a problem in creating the repository...

import com.entjava.poker.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
        Optional<Player> findByName(String name);
}