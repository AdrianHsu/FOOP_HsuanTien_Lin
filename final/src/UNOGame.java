import java.util.ArrayList;
import java.util.Collections;

public class UNOGame {
	
	private static int PLAYER_NUM = 3;
	private static int HUMAN_PLAYER_NUM = 3;
	private static int AI_PLAYER_NUM = 0;
	private static int DEALER_INDEX = -1;


	private void initDeck() {
		
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
	private void shuffle() {
		Collections.shuffle(deck);
	}
	private int pickDealer(ArrayList<Player> players) {

		int dealerIndex = -1;
		int dealerScore = -1;

		for(int i = 0; i < players.size(); i++) {
			
			Card mCard = deck.get(deck.size() - (i + 1));
			if(mCard.score > dealerScore) {
				dealerIndex = i;
				dealerScore = mCard.score;
			}
		}
		return dealerIndex;
	}
	private void dealCards(ArrayList<Player> players) {

		for(int i = 0; i < 7; i++) {

			for(int j = 0; j < players.size(); j++) {

				int _size = deck.size();
				players.get(j).hand.add(deck.remove(_size - 1));
			}
		}
	}

	private ArrayList<Card> deck = new ArrayList<Card>();
	private ArrayList<Player> players = new ArrayList<Player>();

	public void playGame() {

		initDeck();
		// suppose there are 3 players joined
		for( int i = 0; i < HUMAN_PLAYER_NUM; i++ ) {
			players.add(new HumanPlayer());
		}
		DEALER_INDEX = pickDealer(players);
		shuffle();
		System.out.println(DEALER_INDEX);
		dealCards(players);
		System.out.println(deck.size());

	}
}