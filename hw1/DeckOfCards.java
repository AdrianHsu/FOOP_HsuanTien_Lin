import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {

	public DeckOfCards() {
		for(int i = 0; i < 13; i++) {
			for(int j = 0; j < 4; j++) {
				Card _card = new Card( Ranks.values()[i] , Suits.values()[j] );
				totalCards.add( _card );
			}
		}
		JokerCard _redJoker = new JokerCard( Ranks.JOKER, Suits.RED );
		JokerCard _blackJoker = new JokerCard( Ranks.JOKER, Suits.BLACK );

		totalCards.add( _redJoker );
		totalCards.add( _blackJoker );
	}
	public static void shuffle() {
		Collections.shuffle(totalCards);
	}

	public static ArrayList<BaseCard> totalCards = new ArrayList<BaseCard>();
}
