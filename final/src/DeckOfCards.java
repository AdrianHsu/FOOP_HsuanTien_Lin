import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {

	public DeckOfCards() {

		init();
		shuffle();
	}

	public static void init() {
		
		if(!totalCards.isEmpty()) {
			totalCards.clear();
		}
		for( int i = 0; i < 4; i++ ) {
			
			totalCards.add(new NumberCard(0, Colors.values()[i]) );
			totalCards.add(new WildCard(WildType.values()[0], Colors.BLACK));
			totalCards.add(new WildCard(WildType.values()[1], Colors.BLACK));
		}
		
		for( int i = 1; i <= 9; i++ ) {
			
			for( int j = 0; j < 4; j++ ) {
				
				// two same color number cards
				totalCards.add(new NumberCard(i, Colors.values()[j]) );
				totalCards.add(new NumberCard(i, Colors.values()[j]) );
			}
		}
		for( int i = 0; i < 3; i++ ) {

			for( int j = 0; j < 4; j++ ) {
				
				// two same color action cards
				totalCards.add(new ActionCard(Actions.values()[i], Colors.values()[j]) );
				totalCards.add(new ActionCard(Actions.values()[i], Colors.values()[j]) );
			}
		}
	}
	public static void shuffle() {
		Collections.shuffle(totalCards);
	}

	public static ArrayList<Card> totalCards = new ArrayList<Card>();
}
