import foop.*;

import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {

	// SPADE, HEART, DIAMOND, CLUB
	private static final byte[] suit = {Card.SPADE, Card.HEART, Card.DIAMOND, Card.CLUB};


	// one-deck black-jack game
	public DeckOfCards() {
		for(int i = 0; i < 4; i++) {
			for(int j = 1; j <= 13; j++) {

				Card _card = new Card( suit[i] , (byte)j );
				totalCards.add( _card );
			}
		}
		shuffle();
	}
	public void insert(ArrayList<Card> hand) {
		totalCards.addAll(hand);
	}

	public Card removeTop() {

		int size = totalCards.size();
		return totalCards.remove(size - 1);
	}

	public void shuffle() {
		Collections.shuffle(totalCards);
	}

	public ArrayList<Card> totalCards = new ArrayList<Card>();
}
