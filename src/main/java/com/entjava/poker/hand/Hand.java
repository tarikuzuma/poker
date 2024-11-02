package com.entjava.poker.hand;

import com.entjava.poker.game.Player;
import com.entjava.poker.hand.types.Flush;
import com.entjava.poker.hand.types.FullHouse;
import com.entjava.poker.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * The base class of the different Hands such as {@link Flush},
 * {@link FullHouse}, etc.
 */
public abstract class Hand implements Comparable{

    public List<Card> getCurrentHand() {
        return currentHand;
    }

    public void setCurrentHand(List<Card> currentHand) {
        this.currentHand = currentHand;
    }

    private List<Card> currentHand = new ArrayList<>();

    /**
     * @return The {@link HandType}
     */
    public abstract HandType getHandType();

    @Override
    public int compareTo(Object o) {
        Hand hand = (Hand)o;

        int comparison = Integer.compare(this.getHandType().ordinal(), hand.getHandType().ordinal());

        // if same hand rank, check for all cards.
        int ctr =0;
        while(comparison ==0 && ctr < 5) {
            comparison = Integer.compare(this.getCurrentHand().get(ctr).getRank().ordinal(),hand.getCurrentHand().get(ctr).getRank().ordinal());
            ++ctr;
        }

        return comparison;
    }

    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
