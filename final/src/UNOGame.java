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
	private static boolean IS_CLOCKWISE = true; //which is 0,1,2,3...


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
		while(top instanceof WildCard) {
			
			// DEBUG:
			WildCard wTop = (WildCard)top;
			// System.out.println(a.getWildType());
			// System.out.println(deck.size());			

			if(wTop.getWildType() == WildType.WILD_DRAW_FOUR) {

				// CASE1: Return card to deck, shuffle, flip top card to start discard pile
				shuffle(deck);
				top = deck.getLast();
			} else {

				// CASE2: Player to dealer's left declares first color to be matched, 
				// then plays normally

				// do nothing in this function
			}
		}
		if(top instanceof ActionCard) { //actionCard
			
			ActionCard mActionCard = (ActionCard) top;
			if(mActionCard.getAction() == Actions.REVERSE) {
				
				// Dealer plays first; play proceeds counterclockwise
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
		flipTopCard(deck, players);
		
		int round = 1;

		while(true) {

			Deque<Card> discardPile = new LinkedList<Card>();

			Card top = deck.getLast();
			System.out.println("==================[ROUND " + round + "]==================");
			System.out.println("*********************************************");

			System.out.println("CURRENT PLAYER:  " + (CURRENT_INDEX + 1));
			System.out.println("CURRENT CARD INFO:");
			System.out.println("1. CARD COLOR: " + top.getColor());

			if(top instanceof WildCard) {

				WildCard wTop = (WildCard)top;
				System.out.println("2. WILD CARD EFFECT: " + wTop.getWildType());

			} else if (top instanceof ActionCard) {

				ActionCard aTop = (ActionCard)top;
				System.out.println("2. ACTION CARD EFFECT: " + aTop.getAction());

			} else {

				NumberCard nTop = (NumberCard)top;
				System.out.println("2. NUMBER CARD: " + nTop.getNumber());
			}
			System.out.println("*********************************************");
			System.out.println("YOUR CARDS IN HAND:  ");

			ArrayList<Card> pHand = players.get(CURRENT_INDEX).hand;
			for(int i = 0; i < pHand.size(); i++) {

				System.out.println("CARD INDEX: "+ i);
				System.out.println("1. CARD COLOR: " + pHand.get(i).getColor());
				if(pHand.get(i) instanceof WildCard) {

					WildCard wTop = (WildCard)pHand.get(i);
					System.out.println("2. WILD CARD EFFECT: " + wTop.getWildType());

				} else if (pHand.get(i) instanceof ActionCard) {

					ActionCard aTop = (ActionCard)pHand.get(i);
					System.out.println("2. ACTION CARD EFFECT: " + aTop.getAction());

				} else {

					NumberCard nTop = (NumberCard)pHand.get(i);
					System.out.println("2. NUMBER CARD: " + nTop.getNumber());
				}
			}
			System.out.println("*********************************************");


			if(IS_CLOCKWISE) {

				if(CURRENT_INDEX == HUMAN_PLAYER_NUM - 1)
					CURRENT_INDEX = 0;
				else
					CURRENT_INDEX++;
			} else {

				if(CURRENT_INDEX == 0)
					CURRENT_INDEX = HUMAN_PLAYER_NUM - 1;
				else
					CURRENT_INDEX--;
			}
			round++;
		}

		// DEBUG:
		// Deque<Card> testDeck = new LinkedList<Card>();

		// testDeck.add(new ActionCard(Actions.values()[2], Colors.BLUE));
		// testDeck.add(new WildCard(WildType.values()[0], Colors.BLACK));
		// testDeck.add(new WildCard(WildType.values()[1], Colors.BLACK));
		// flipTopCard(testDeck, players);

	}
}