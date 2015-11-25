import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Scanner;


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
			
			// deck.add(new NumberCard(0, Colors.values()[i]) );
			// deck.add(new WildCard(WildType.values()[0], Colors.BLACK));
			// deck.add(new WildCard(WildType.values()[1], Colors.BLACK));
			deck.add(new NumberCard(0, Colors.values()[0]));
		}
		
		for( int i = 1; i <= 9; i++ ) {
			
			for( int j = 0; j < 4; j++ ) {
				
				// two same color number cards
				// deck.add(new NumberCard(i, Colors.values()[j]) );
				// deck.add(new NumberCard(i, Colors.values()[j]) );
				deck.add(new NumberCard(i, Colors.values()[0]) );
				deck.add(new NumberCard(i, Colors.values()[0]) );
			}
		}
		for( int i = 0; i < 3; i++ ) {

			for( int j = 0; j < 4; j++ ) {
				
				// two same color action cards
				// deck.add(new ActionCard(Actions.values()[i], Colors.values()[j]) );
				// deck.add(new ActionCard(Actions.values()[i], Colors.values()[j]) );
				deck.add(new ActionCard(Actions.values()[2], Colors.values()[0]) );

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
	public void rebuildDeck(Deque<Card> deck, Deque<Card> discardPile) {

		for(int i = 0; i < deck.size(); i++)
			discardPile.add(deck.removeLast());
		for(int i = 0; i < discardPile.size(); i++)
			deck.add(discardPile.removeLast());
		shuffle(deck);
	}
	public void drawCard(Player player, int numOfCards, 
											 Deque<Card> deck, Deque<Card> discardPile) {

		if(numOfCards >=  deck.size()) {
			rebuildDeck(deck, discardPile);
		}

		for(int i = 0; i < numOfCards; i++)
			player.hand.add(deck.removeLast());
	}
	public void printHand(ArrayList<Card> pHand) {
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
				System.out.println("2. CARD NUMBER: " + nTop.getNumber());
			}
		}
	}
	public void printCard(Card top) {

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
			System.out.println("2. CARD NUMBER: " + nTop.getNumber());
		}
	}
	public void topIsNumberCard(ArrayList<Card> pHand, Card top, 
																 Deque<Card> deck, Deque<Card> discardPile, Player player) {

		boolean[] canBeDiscardedIndex = new boolean[pHand.size()];
		boolean drawOne = true;
		NumberCard nTop = (NumberCard)top;

		System.out.println("YOUR CARDS OF WHICH CAN BE DISCARDED: ");
		for(int i = 0; i < pHand.size(); i++) {

			if(pHand.get(i) instanceof WildCard) {

			} else if (pHand.get(i) instanceof ActionCard) {

				ActionCard aHand = (ActionCard)pHand.get(i);
				if(aHand.getColor() == top.getColor()) {

					if(aHand.getAction() == Actions.REVERSE) {
						canBeDiscardedIndex[i] = true;
					}
				}
			} else {

				NumberCard nHand = (NumberCard)pHand.get(i);
				if(nHand.getColor() == nTop.getColor() ||
				   nHand.getNumber() == nTop.getNumber())
						canBeDiscardedIndex[i] = true;
				else
					canBeDiscardedIndex[i] = false;
			}

			if(canBeDiscardedIndex[i] == true) {
				System.out.print(i + " ");
				drawOne = false;
			}	
		}

		if(drawOne) {
			// NO MATCHING CARD
			System.out.print("NONE: NO MATCHING CARD, DRAW 1 CARD AND PASSED");
			drawCard(player, 1, deck, discardPile);
			drawOne = true;
			System.out.println("");
		}
		else {
			System.out.println("");
			System.out.println("*********************************************");
			System.out.println("PICK A NUMBER: ");
			Scanner scanner = new Scanner(System.in);
			int var =  scanner.nextInt();
			
			while(true) {
				if(var >= pHand.size() || var < 0) {
					System.out.println("INVALID, PICK AGAIN.");
					var = scanner.nextInt();
					continue;
				}
				if(canBeDiscardedIndex[var] == false) {
					System.out.println("INVALID, PICK AGAIN.");
					var = scanner.nextInt();
					continue;
				}
				break;
			}
			System.out.println("YOU PICKED: " + var);
			if(pHand.get(var) instanceof NumberCard) {


			} else if (pHand.get(var) instanceof ActionCard) {

				ActionCard aHand = (ActionCard)pHand.get(var);
				if(aHand.getAction() == Actions.REVERSE)
					IS_CLOCKWISE = !IS_CLOCKWISE;
			} else {

			}
			discardPile.add( pHand.remove(var) );

		}
	}

	
	public void topIsActionCard(ArrayList<Card> pHand, Card top, 
																 Deque<Card> deck, Deque<Card> discardPile, Player player) {
		boolean[] canBeDiscardedIndex = new boolean[pHand.size()];
		boolean drawOne = true;
		//diff
		ActionCard aTop = (ActionCard)top;

		System.out.println("YOUR CARDS OF WHICH CAN BE DISCARDED: ");
		for(int i = 0; i < pHand.size(); i++) {

			if(pHand.get(i) instanceof WildCard) {

			} else if (pHand.get(i) instanceof ActionCard) {

				ActionCard aHand = (ActionCard)pHand.get(i);
				if(aHand.getColor() == top.getColor()) {

					if(aHand.getAction() == Actions.REVERSE) {
						canBeDiscardedIndex[i] = true;
					}
				}
			} else {

				NumberCard nHand = (NumberCard)pHand.get(i);
				//diff
				if(nHand.getColor() == aTop.getColor())
						canBeDiscardedIndex[i] = true;
				else
					canBeDiscardedIndex[i] = false;
			}

			if(canBeDiscardedIndex[i] == true) {
				System.out.print(i + " ");
				drawOne = false;
			}	
		}

		if(drawOne) {
			// NO MATCHING CARD
			System.out.print("NONE: NO MATCHING CARD, DRAW 1 CARD AND PASSED");
			drawCard(player, 1, deck, discardPile);
			drawOne = true;
			System.out.println("");
		}
		else {
			System.out.println("");
			System.out.println("*********************************************");
			System.out.println("PICK A NUMBER: ");
			Scanner scanner = new Scanner(System.in);
			int var =  scanner.nextInt();
			
			while(true) {
				if(var >= pHand.size() || var < 0) {
					System.out.println("INVALID, PICK AGAIN.");
					var = scanner.nextInt();
					continue;
				}
				if(canBeDiscardedIndex[var] == false) {
					System.out.println("INVALID, PICK AGAIN.");
					var = scanner.nextInt();
					continue;
				}
				break;
			}
			System.out.println("YOU PICKED: " + var);
			if(pHand.get(var) instanceof NumberCard) {


			} else if (pHand.get(var) instanceof ActionCard) {

				ActionCard aHand = (ActionCard)pHand.get(var);
				if(aHand.getAction() == Actions.REVERSE)
					IS_CLOCKWISE = !IS_CLOCKWISE;
			} else {

			}
			discardPile.add( pHand.remove(var) );
		}
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
		Card top = deck.getLast();
		boolean IS_NOT_OVER = true;
		while(IS_NOT_OVER) {

			Deque<Card> discardPile = new LinkedList<Card>();

			System.out.println("==================[ROUND " + round + "]==================");
			System.out.println("*********************************************");

			System.out.println("CURRENT PLAYER:  " + (CURRENT_INDEX + 1));
			printCard(top);
			
			System.out.println("*********************************************");
			System.out.println("YOUR CARDS IN HAND:  ");

			ArrayList<Card> pHand = players.get(CURRENT_INDEX).hand;
			printHand(pHand);
			System.out.println("*********************************************");
			
			if(top instanceof NumberCard) {

				topIsNumberCard(pHand, top, deck, discardPile, players.get(CURRENT_INDEX));
				top = discardPile.getLast();
				if(pHand.size() == 0)
					IS_NOT_OVER = false;
			} else if (top instanceof ActionCard) {
				
				topIsActionCard(pHand, top, deck, discardPile, players.get(CURRENT_INDEX));
				top = discardPile.getLast();
				if(pHand.size() == 0)
					IS_NOT_OVER = false;

			} else {

			}
			if(IS_NOT_OVER) {
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
		}

		System.out.println("!!!!!!!!!PLAYER " + CURRENT_INDEX + " WINS!!!!!!!!!");
		// DEBUG:
		// Deque<Card> testDeck = new LinkedList<Card>();

		// testDeck.add(new ActionCard(Actions.values()[2], Colors.BLUE));
		// testDeck.add(new WildCard(WildType.values()[0], Colors.BLACK));
		// testDeck.add(new WildCard(WildType.values()[1], Colors.BLACK));
		// flipTopCard(testDeck, players);

	}
}