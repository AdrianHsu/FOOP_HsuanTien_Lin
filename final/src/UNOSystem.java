import java.util.ArrayList;
import java.util.Collections;

public class UNOSystem {

	public UNOSystem() {
		initDeck();
	}

	public static void initDeck() {
		
		if(!deck.isEmpty()) {
			deck.clear();
		}
		for( int i = 0; i < 4; i++ ) {
			
			deck.add(new NumberCard(0, Colors.values()[i]) );
			deck.add(new WildCard(WildType.values()[0], Colors.BLACK));
			deck.add(new WildCard(WildType.values()[1], Colors.BLACK));
		}
		
		for( int i = 1; i <= 9; i++ ) {
			
			for( int j = 0; j < 4; j++ ) {
				
				// two same color number cards
				deck.add(new NumberCard(i, Colors.values()[j]) );
				deck.add(new NumberCard(i, Colors.values()[j]) );
			}
		}
		for( int i = 0; i < 3; i++ ) {

			for( int j = 0; j < 4; j++ ) {
				
				// two same color action cards
				deck.add(new ActionCard(Actions.values()[i], Colors.values()[j]) );
				deck.add(new ActionCard(Actions.values()[i], Colors.values()[j]) );
			}
		}

		shuffle();
	}
	public static void shuffle() {
		Collections.shuffle(deck);
	}
	public static void dealCards(ArrayList<Player> players) {

		for(int i = 0; i < 7; i++) {

			for(int j = 0; j < players.size(); j++) {

				int _size = deck.size();
				players[j].hand.add(deck.remove(_size - 1);
			}
		}
	}

	public static ArrayList<Card> deck = new ArrayList<Card>();
}
