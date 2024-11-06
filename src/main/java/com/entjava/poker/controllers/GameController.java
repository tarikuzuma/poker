package com.entjava.poker.controllers;

import com.entjava.poker.card.BlankCard;
import com.entjava.poker.card.Card;
import com.entjava.poker.game.Game;
import com.entjava.poker.game.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class GameController {

	private Game game;

	public GameController(Game game) {
		this.game = game;
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("game", game);

		List<Player> players = game.getPlayers();
		for (int i = 0; i < players.size(); i++) {
			int playerNumber = i + 1;
			model.addAttribute("player" + playerNumber, players.get(i));
		}

		Iterator<Card> communityCardIterator = game.getCommunityCards().iterator();
		for (int communityCardNumber = 1; communityCardNumber <= 5; communityCardNumber++) {
			model.addAttribute("communityCard" + communityCardNumber, fetchNextCommunityCard(communityCardIterator));
		}

		return "index";
	}

	private Card fetchNextCommunityCard(Iterator<Card> communityCardIterator) {
		if (communityCardIterator.hasNext()) {
			return communityCardIterator.next();
		} else {
			return new BlankCard();
		}
	}

	@GetMapping("/nextAction")
	public String nextAction() {
		if (game.hasEnded()) {
			game.startNewGame();
		} else {
			game.nextAction();
		}

		return "redirect:/";
	}

	@GetMapping("/start_game/{id}")
	public ResponseEntity<Map<String, Object>> startGame(@PathVariable int id) {
		game.startNewGame();  // Assuming you have a method to initialize a new game
		List<Player> players = game.getPlayers();
		List<Map<String, String>> playerDetails = players.stream().map(player -> {
			Map<String, String> playerMap = new HashMap<>();
			playerMap.put("name", player.getName());
			return playerMap;
		}).collect(Collectors.toList());

		Map<String, Object> response = new HashMap<>();
		response.put("players", playerDetails);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/event/{id}")
	public ResponseEntity<Map<String, Object>> getEvent(@PathVariable int id) {
		List<Player> players = game.getPlayers();

		// Update here
		String winnerName = game.getWinningHand()
				.map(hand -> hand.getPlayer().getName())
				.orElse("No winner determined");

		List<Map<String, String>> playerDetails = players.stream().map(player -> {
			Map<String, String> playerMap = new HashMap<>();
			playerMap.put("name", player.getName());
			playerMap.put("hand", player.getHand() != null ? player.getHand().toString() : "No hand");
			return playerMap;
		}).collect(Collectors.toList());

		Map<String, Object> response = new HashMap<>();
		response.put("players", playerDetails);
		response.put("winner", winnerName);

		return ResponseEntity.ok(response);
	}

}
