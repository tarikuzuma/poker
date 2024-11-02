package com.entjava.poker.game;

import com.entjava.poker.card.BlankCard;
import com.entjava.poker.card.Card;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class GameController {

	private Game game;

	public GameController(Game game) {
		this.game = game;
	}

	//	[
	//		{ "name": "Chance" },
	//		{ "name": "AliceGuo" },
	//		{ "name": "Lakas Tama" },
	//		{ "name": "Akira Chancellor" }
	//	]
	@PostMapping("/start_game/{numberOfPlayers}")
	public ResponseEntity<?> startGame(
			@PathVariable int numberOfPlayers,
			@RequestBody List<Player> players,
			RedirectAttributes redirectAttributes) {
		int playerCount = players.size();

		// Validate that the number of players is between 2 and 6
		if (playerCount < 2 || playerCount > 6) {
			// Return a 400 Bad Request with an error message in JSON format
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid number of players: must be between 2 and 6.");
		}

		// If valid, set up a redirect response
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/"));  // Redirect to the main page or desired location
		return new ResponseEntity<>(headers, HttpStatus.FOUND); // 302 Found for redirect
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

	//	postmapping endpoint with number of players
	//	payload contains the players and their names
	//	/start_game/{4}

	//	log message when players are not registered

	//	save the winners and losers in the database

	//	getmapping endpoint with id of event
	//	the event is returned along with the players, hands, and the winner name
}
