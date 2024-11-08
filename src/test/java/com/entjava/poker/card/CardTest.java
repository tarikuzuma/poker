package com.entjava.poker.card;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CardTest {

	@Test
	public void testCardEquality_sameRankAndSuitAreConsideredEqual() {
		Card card1 = new Card(CardRank.ACE, CardSuit.DIAMONDS);
		Card card2 = new Card(CardRank.ACE, CardSuit.DIAMONDS);

		assertEquals(card1, card2);
	}

	@Test
	public void testCardEquality_differentRankAndSuitAreNotEqual() {
		Card card1 = new Card(CardRank.ACE, CardSuit.DIAMONDS);
		Card card2 = new Card(CardRank.KING, CardSuit.DIAMONDS);

		assertNotEquals(card1, card2);
	}

	@Test
	public void styleClass_whenDiamondsOrHearts_returnsRedClass() {
		Card card1 = new Card(CardRank.ACE, CardSuit.DIAMONDS);
		Card card2 = new Card(CardRank.ACE, CardSuit.HEARTS);

		assertEquals("card-red", card1.styleClass());
		assertEquals("card-red", card2.styleClass());
	}

	@Test
	public void styleClass_whenClubsOrSpades_returnsBlackClass() {
		Card card1 = new Card(CardRank.ACE, CardSuit.CLUBS);
		Card card2 = new Card(CardRank.ACE, CardSuit.SPADES);

		assertEquals("card-black", card1.styleClass());
		assertEquals("card-black", card2.styleClass());
	}


	@Test
	public void validateSorting() {
		Card card1 = new Card(CardRank.ACE, CardSuit.CLUBS);
		Card card2 = new Card(CardRank.TEN, CardSuit.SPADES);

		Card card3 = new Card(CardRank.FIVE, CardSuit.SPADES);
		Card card7 = new Card(CardRank.FIVE, CardSuit.DIAMONDS);

		Card card4 = new Card(CardRank.JACK, CardSuit.SPADES);

		List<Card> cards = new ArrayList<>();

		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		cards.add(card4);
		cards.add(card7);

		Collections.sort(cards,Collections.reverseOrder());

		cards.stream().forEach(c -> System.out.print(c.getRank() + " " + c.getSuit()+ " "));
	}

}