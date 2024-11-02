package com.entjava.poker.controller;

import com.entjava.poker.model.Player;
import com.entjava.poker.service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/player/{id}")
    public Player players(@PathVariable Integer id) {
        return playerService.getPlayerById(id).orElse(null);
    }

}
