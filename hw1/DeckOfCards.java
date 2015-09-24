import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {

	enum Suits {
		CLUBS, DIAMONDS, HEARTS, SPADES
	}
	enum Rank {
		TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,
		TEN, JACK, QUEEN, KING, ACE
	}
	enum Joker {
		RED, BLACK
	}

	public DeckOfCards() {
		for(int i = 0; i < 13; i++) {
			for(int j = 0; j < 4; j++) {
				Card _card = new Card(i, j);
				totalCards.add( _card );
			}
		}
		JokerCard _redJoker = new JokerCard(0);
		JokerCard _blackJoker = new JokerCard(1);

		totalCards.add( _redJoker );
		totalCards.add( _blackJoker );
	}
	public static void shuffle() {
		Collections.shuffle(totalCards);
	}

	public static ArrayList<BaseCard> totalCards = new ArrayList<BaseCard>();

}