package com.entjava.poker.game;

import com.entjava.poker.card.BlankCard;
import com.entjava.poker.card.Card;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Iterator;
import java.util.List;

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

	//	postmapping endpoint with number of players
	//	payload contains the players and their names
	//	/start_game/{4}

	//	log message when players are not registered

	//	save the winners and losers in the database

	//	getmapping endpoint with id of event
	//	the event is returned along with the players, hands, and the winner name
}
