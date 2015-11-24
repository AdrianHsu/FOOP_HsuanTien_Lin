import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Collections;

public class UNOGame {
	
	private static int PLAYER_NUM = 3;
	private static int HUMAN_PLAYER_NUM = 3;
	private static int AI_PLAYER_NUM = 0;
	private static int DEALER_INDEX = -1;
	private static int CURRENT_INDEX = -1; 
	private static boolean IS_CLOCKWISE = true; //0,1,2,3...


	private void initDeck(Deque<Card> deck) {
		
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

		shuffle(deck);
	}
	private void shuffle(Deque<Card> deck) {

		ArrayList<Card> listDeck = new ArrayList<Card>();
		int _size = deck.size();
		for(int i = 0; i < _size; i++) {
			listDeck.add(deck.removeLast());
		}
		
		Collections.shuffle(listDeck);
		
		if(!deck.isEmpty())
			System.out.println("ERROR");

		_size = listDeck.size();
		for(int i = 0; i < _size; i++) {
			deck.add(listDeck.remove(0));
			// System.out.println(deck.getLast().getScore() + "   " + i);
		}
	}
	private int pickDealer(Deque<Card> deck, ArrayList<Player> players) {

		int dealerIndex = -1;
		int dealerScore = -1;

		for(int i = 0; i < players.size(); i++) {
			
			Card mCard = deck.getLast();
			if(mCard.score > dealerScore) {
				dealerIndex = i;
				dealerScore = mCard.score;
			}
		}
		return dealerIndex;
	}
	private void dealCards(Deque<Card> deck, ArrayList<Player> players) {

		for(int i = 0; i < 7; i++) {

			for(int j = 0; j < players.size(); j++) {

				int _size = deck.size();
				players.get(j).hand.add(deck.removeLast());
			}
		}
	}
	private void flipTopCard(Deque<Card> deck, ArrayList<Player> players) {

		Card top = deck.getLast();
		while(top.getScore() == 50) { // must be wildcard
			deck.addFirst(top);
			System.out.println(deck.size());
			top = deck.removeLast();
			WildCard a = (WildCard)top;
			System.out.println(a.getWildType());
			System.out.println(deck.size());
		}
		if(top.getScore() == 20) { //actionCard
			
			ActionCard mActionCard = (ActionCard) top;
			if(mActionCard.getAction() == Actions.REVERSE) {
				
				CURRENT_INDEX = DEALER_INDEX;
				IS_CLOCKWISE = false;
				return;
			}
		}
		if(DEALER_INDEX == HUMAN_PLAYER_NUM - 1)
			CURRENT_INDEX = 0;
		else
			CURRENT_INDEX = DEALER_INDEX + 1;
		IS_CLOCKWISE = true;
		return;
	}

	public void playGame() {

		Deque<Card> deck = new LinkedList<Card>();
		ArrayList<Player> players = new ArrayList<Player>();

		// suppose there are 3 players joined
		for( int i = 0; i < HUMAN_PLAYER_NUM; i++ ) {
			players.add(new HumanPlayer());
		}

		initDeck(deck);
		DEALER_INDEX = pickDealer(deck, players);
		shuffle(deck);
		dealCards(deck, players);

		// flipTopCard(deck, players);
		

		// Deque<Card> testDeck = new LinkedList<Card>();
		// testDeck.add(new WildCard(WildType.values()[0], Colors.BLACK));
		// testDeck.add(new WildCard(WildType.values()[1], Colors.BLACK));
		// flipTopCard(testDeck, players);

	}
}