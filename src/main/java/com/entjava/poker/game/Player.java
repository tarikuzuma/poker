package com.entjava.poker.game;

import com.entjava.poker.hand.Hand;
import com.entjava.poker.card.Card;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * A player in the game.
 * Maps to the players table in the database.
 */
@Entity
@Table(name = "players")
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "hand")
	private String handDescription;

	@Column(name = "is_winner")
	private boolean isWinner;

	// Relationship with events
	@OneToMany(mappedBy = "player")
	private List<Event> events = new ArrayList<>();

	// Transient fields - not persisted to database
	@Transient
	private List<Card> hand = new ArrayList<>();

	@Transient
	private Hand playableHand;

	// Default constructor required by JPA
	public Player() {
	}

	public Player(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Card> getHand() {
		return hand;
	}

	void addToHand(Card card) {
		hand.add(card);
	}

	void clearHand() {
		hand.clear();
	}

	public Hand getPlayableHand() {
		return playableHand;
	}

	public void setPlayableHand(Hand playableHand) {
		this.playableHand = playableHand;
		if (playableHand != null) {
			this.handDescription = playableHand.toString();
		}
	}

	public String getHandDescription() {
		return handDescription;
	}

	public void setHandDescription(String handDescription) {
		this.handDescription = handDescription;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean winner) {
		isWinner = winner;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void addEvent(Event event) {
		events.add(event);
		event.setPlayer(this);
	}

	@Override
	public String toString() {
		return name;
	}
}