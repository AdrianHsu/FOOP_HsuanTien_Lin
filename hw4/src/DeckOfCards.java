import foop.*;

import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {

	// SPADE = 1, HEART = 2, DIAMOND = 3, CLUB = 4
	private static final byte[] suit = {Card.SPADE, Card.HEART, Card.DIAMOND, Card.CLUB};


	// one-deck black-jack game
	public DeckOfCards() {
		for(int i = 0; i < 4; i++) {
			for(int j = 1; j <= 13; j++) { // 1 to 13

				Card _card = new Card( suit[i] , (byte)j );
				totalCards.add( _card );
			}
		}
		shuffle();
	}
	public void insert(ArrayList<Card> hand) {
		if(hand.size() > 0)
			totalCards.addAll(hand);
	}

	public Card removeTop() {

		int size = totalCards.size();
		return totalCards.remove(size - 1);
	}

	public void shuffle() {
		Collections.shuffle(totalCards);
	}
	public ArrayList<Card> get() {
		return totalCards;
	}

	private ArrayList<Card> totalCards = new ArrayList<Card>();
}
