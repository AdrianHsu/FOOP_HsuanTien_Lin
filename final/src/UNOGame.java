import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Scanner;


public class UNOGame {
	
	// public static int PLAYER_NUM = 3;
	public static int HUMAN_PLAYER_NUM = 3;
	// public static int AI_PLAYER_NUM = 0;
	public static int DEALER_INDEX = -1;
	public static int CURRENT_INDEX = -1;
	public static int CURRENT_PLUS = 0;
	public static boolean IS_CLOCKWISE = true; //which is 0,1,2,3...
	public static boolean IS_SKIPPED = false;
	public static boolean IS_WILD_COLOR = false;
	public static Colors CURRENT_WILD_COLOR = null;



	public Deque<Card> deck = new LinkedList<Card>();
	public ArrayList<Player> players = new ArrayList<Player>();
	public Deque<Card> discardPile = new LinkedList<Card>();

	public void initDeck(Deque<Card> deck) {
		
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
	public void shuffle() {

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
	public int pickDealer(Deque<Card> deck, ArrayList<Player> players) {

		int dealerIndex = -1;
		int dealerScore = -1;

		for(int i = 0; i < players.size(); i++) {
			
			Card mCard = deck.getLast();
			if(mCard.score > dealerScore) {
				dealerIndex = i;
				dealerScore = mCard.score;
			}
		}
		System.out.println("DEALER IS: PLAYER " + (dealerIndex + 1));
		return dealerIndex;
	}
	public void dealCards(Deque<Card> deck, ArrayList<Player> players) {

		for(int i = 0; i < 7; i++) {

			for(int j = 0; j < players.size(); j++) {

				int _size = deck.size();
				players.get(j).hand.add(deck.removeLast());
			}
		}
	}
	public void flipTopCard(Deque<Card> deck, ArrayList<Player> players) {

		Card top = deck.getLast();

		if (top instanceof WildCard) {
			WildCard wTop = (WildCard)top;
			if(wTop.getWildType() == WildType.WILD_DRAW_FOUR) {

				// CASE1: Return card to deck, shuffle, flip top card to start discard pile
				while(true) {
					System.out.println("TOP CARD IS WILD_DRAW_FOUR: RETURN CARD TO DECK AND SHUFFLE AGAIN");
					shuffle();
					top = deck.getLast();
					if(top instanceof WildCard) {
						
						wTop = (WildCard)top;
						if(wTop.getWildType() == WildType.WILD_DRAW_FOUR)
							continue;
					}
					break;
				}
			}
		}
		if (top instanceof WildCard) {
			
				WildCard wTop = (WildCard)top;
				// CASE2: Player to dealer's left declares first color to be matched, 
				// then plays normally

				// do nothing in this function
		}
		
		if (top instanceof ActionCard) { //actionCard
			
			ActionCard mActionCard = (ActionCard) top;
			if(mActionCard.getAction() == Actions.REVERSE) {
				
				// Dealer plays first; play proceeds counterclockwise
				CURRENT_INDEX = DEALER_INDEX;
				IS_CLOCKWISE = false;
				return;
			} else if (mActionCard.getAction() == Actions.SKIP) {
				IS_SKIPPED = true;
			} else {
				IS_SKIPPED = true;
				CURRENT_PLUS += 2;
			}
		}
		if(DEALER_INDEX == HUMAN_PLAYER_NUM - 1)
			CURRENT_INDEX = 0;
		else
			CURRENT_INDEX = DEALER_INDEX + 1;
		IS_CLOCKWISE = true;
		return;
	}
	public void rebuildDeck() {

		for(int i = 0; i < deck.size(); i++)
			discardPile.add(deck.removeLast());
		for(int i = 0; i < discardPile.size(); i++)
			deck.add(discardPile.removeLast());
		shuffle();
	}
	public void drawCard(Player player, int numOfCards) {

		if(numOfCards >=  deck.size()) {
			rebuildDeck();
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
			System.out.println("3. CHOSEN COLOR: " + CURRENT_WILD_COLOR);
		} else if (top instanceof ActionCard) {

			ActionCard aTop = (ActionCard)top;
			System.out.println("2. ACTION CARD EFFECT: " + aTop.getAction());

		} else {

			NumberCard nTop = (NumberCard)top;
			System.out.println("2. CARD NUMBER: " + nTop.getNumber());
		}
	}

	public void topIsNumberCard(ArrayList<Card> pHand, Card top, Player player) {
		
		boolean[] canBeDiscardedIndex = new boolean[pHand.size()];
		for(int i = 0; i < pHand.size(); i++)
			canBeDiscardedIndex[i] = false;
		boolean drawOne = nPrintCardsCanBeDiscarded(pHand, top, player,
			canBeDiscardedIndex);
		if(drawOne) {
			// NO MATCHING CARD
			System.out.print("NONE: NO MATCHING CARD, DRAW 1 CARD AND PASSED");
			drawCard(player, 1);
			System.out.println("");
		}
		else {
			System.out.println("");
			System.out.println("*********************************************");
			pickCard(pHand, top, player, canBeDiscardedIndex);
		}
	}
	public void topIsActionCard(ArrayList<Card> pHand, Card top, Player player) {
		
		ActionCard aCard = (ActionCard)top;
		if(aCard.getAction() == Actions.SKIP) {

			if(IS_SKIPPED) {
				System.out.println("PLAYER ARE SKIPPED");
				System.out.println("*********************************************");
				IS_SKIPPED = false;
			} else {
				boolean[] canBeDiscardedIndex = new boolean[pHand.size()];
				for(int i = 0; i < pHand.size(); i++)
					canBeDiscardedIndex[i] = false;
				boolean drawOne = aPrintCardsCanBeDiscarded(pHand, top, player, canBeDiscardedIndex);

				if(drawOne) {
					// NO MATCHING CARD
					System.out.print("NONE: NO MATCHING CARD, DRAW 1 CARD AND PASSED");
					drawCard(player, 1);
					System.out.println("");
				}
				else {
					System.out.println("");
					System.out.println("*********************************************");
					pickCard(pHand, top, player, canBeDiscardedIndex);
				}
			}
		} else if(aCard.getAction() == Actions.DRAW_TWO) {
			
			boolean[] canBeDiscardedIndex = new boolean[pHand.size()];
			for(int i = 0; i < pHand.size(); i++)
				canBeDiscardedIndex[i] = false;
			if(IS_SKIPPED) {
				
				boolean skip = aDrawTwoCanBeDiscarded(pHand, top, player, canBeDiscardedIndex);
				if(skip) {

					System.out.println("PLAYER ARE SKIPPED, DREW " + CURRENT_PLUS + " CARDS");
					drawCard(player, CURRENT_PLUS);
					IS_SKIPPED = false;
					CURRENT_PLUS = 0;
					System.out.println("*********************************************");
				} else {
					pickCard(pHand, top, player, canBeDiscardedIndex);

				}
			} else {

				boolean drawOne = aPrintCardsCanBeDiscarded(pHand, top, player, canBeDiscardedIndex);

				if(drawOne) {
					// NO MATCHING CARD
					System.out.print("NONE: NO MATCHING CARD, DRAW 1 CARD AND PASSED");
					drawCard(player, 1);
					System.out.println("");
				}
				else {
					System.out.println("");
					System.out.println("*********************************************");
					pickCard(pHand, top, player, canBeDiscardedIndex);
				}
			}
		}
		else {
			boolean[] canBeDiscardedIndex = new boolean[pHand.size()];
			for(int i = 0; i < pHand.size(); i++)
				canBeDiscardedIndex[i] = false;
			boolean drawOne = aPrintCardsCanBeDiscarded(pHand, top, player, canBeDiscardedIndex);

			if(drawOne) {
				// NO MATCHING CARD
				System.out.print("NONE: NO MATCHING CARD, DRAW 1 CARD AND PASSED");
				drawCard(player, 1);
				System.out.println("");
			}
			else {
				System.out.println("");
				System.out.println("*********************************************");
				pickCard(pHand, top, player, canBeDiscardedIndex);
			}
		}
	}
	public void topIsWildCard(ArrayList<Card> pHand, Card top, Player player) {
		
		WildCard wTop = (WildCard)top;
		if(wTop.getWildType() == WildType.WILD) {
			
			boolean[] canBeDiscardedIndex = new boolean[pHand.size()];
			for(int i = 0; i < pHand.size(); i++)
				canBeDiscardedIndex[i] = false;
			boolean drawOne = wPrintCardsCanBeDiscarded(pHand, top, player,
				canBeDiscardedIndex);
			if(drawOne) {
				// NO MATCHING CARD
				System.out.print("NONE: NO MATCHING CARD, DRAW 1 CARD AND PASSED");
				drawCard(player, 1);
				System.out.println("");
			}
			else {
				System.out.println("");
				System.out.println("*********************************************");
				pickCard(pHand, top, player, canBeDiscardedIndex);
			}
		} else {
			boolean[] canBeDiscardedIndex = new boolean[pHand.size()];
			for(int i = 0; i < pHand.size(); i++)
				canBeDiscardedIndex[i] = false;
			if(IS_SKIPPED) {
				boolean skip = aDrawFourCanBeDiscarded(pHand, top, player, canBeDiscardedIndex);
				if(skip) {

					System.out.println("PLAYER ARE SKIPPED, DREW " + CURRENT_PLUS + " CARDS");
					drawCard(player, CURRENT_PLUS);
					IS_SKIPPED = false;
					CURRENT_PLUS = 0;
					System.out.println("*********************************************");
				} else {
					pickCard(pHand, top, player, canBeDiscardedIndex);
				}
			} else {
				boolean drawOne = wPrintCardsCanBeDiscarded(pHand, top, player, canBeDiscardedIndex);

				if(drawOne) {
					// NO MATCHING CARD
					System.out.print("NONE: NO MATCHING CARD, DRAW 1 CARD AND PASSED");
					drawCard(player, 1);
					System.out.println("");
				}
				else {
					System.out.println("");
					System.out.println("*********************************************");
					pickCard(pHand, top, player, canBeDiscardedIndex);
				}
			}

		}
	}
	public boolean aDrawFourCanBeDiscarded(ArrayList<Card> pHand, Card top, Player player, boolean[] canBeDiscardedIndex) {

		boolean skip = true;
		WildCard wTop = (WildCard)top;
		for(int i = 0; i < pHand.size(); i++) {
			
			Card mCard = pHand.get(i);
			if(mCard instanceof WildCard) {

				WildCard wCard = (WildCard)mCard;
				if(wCard.getWildType() == WildType.WILD_DRAW_FOUR)
					canBeDiscardedIndex[i] = true;
				else 
					canBeDiscardedIndex[i] = false;
			} else
				canBeDiscardedIndex[i] = false;
			
			if(canBeDiscardedIndex[i] == true) {
				skip = false;
			}
		}
		if(!skip) {
			System.out.println("YOUR CARDS OF WHICH CAN BE DISCARDED: ");
			for(int i = 0; i < pHand.size(); i++)
				if(canBeDiscardedIndex[i] == true)
					System.out.print(i + " ");

			System.out.println("");
		}
		return skip;
	}
	public boolean aDrawTwoCanBeDiscarded(ArrayList<Card> pHand, Card top, Player player, boolean[] canBeDiscardedIndex) {

		boolean skip = true;
		ActionCard aTop = (ActionCard)top;
		for(int i = 0; i < pHand.size(); i++) {
			
			Card mCard = pHand.get(i);
			if(mCard instanceof WildCard) {

				WildCard wCard = (WildCard)mCard;
				if(wCard.getWildType() == WildType.WILD) {
					// can be discarded exclude +2, +4
					canBeDiscardedIndex[i] = false;
				} else {
					canBeDiscardedIndex[i] = true;
				}
			} else if(mCard instanceof ActionCard) {
				ActionCard aCard = (ActionCard)mCard;

				if(aCard.getAction() == Actions.DRAW_TWO)
					canBeDiscardedIndex[i] = true;
				else 
					canBeDiscardedIndex[i] = false;

			} else {
					canBeDiscardedIndex[i] = false;
			}

			if(canBeDiscardedIndex[i] == true) {
				// System.out.print(i + " ");
				skip = false;
			}
		}
		if(!skip) {
			System.out.println("YOUR CARDS OF WHICH CAN BE DISCARDED: ");
			for(int i = 0; i < pHand.size(); i++)
				if(canBeDiscardedIndex[i] == true)
					System.out.print(i + " ");

			System.out.println("");
		}
		return skip;
	}

	public boolean aPrintCardsCanBeDiscarded(ArrayList<Card> pHand, Card top, Player player, boolean[] canBeDiscardedIndex) {
	
		boolean drawOne = true;

		System.out.println("YOUR CARDS OF WHICH CAN BE DISCARDED: ");

		ActionCard aTop = (ActionCard)top;

		for(int i = 0; i < pHand.size(); i++) {
			Card mCard = pHand.get(i);
			if(mCard instanceof WildCard) {
				//CASE: 2-1
				canBeDiscardedIndex[i] = true;
			} else if(mCard instanceof ActionCard) {
				//CASE: 2-2
				ActionCard aCard = (ActionCard)mCard;
				
				if(aCard.getAction() == aTop.getAction()) {

						canBeDiscardedIndex[i] = true;
				}
			} else {
				//CASE: 2-3
				NumberCard nCard = (NumberCard)mCard;

				if(nCard.getColor() == aTop.getColor()) {
						canBeDiscardedIndex[i] = true;			
				}
			}
			if(canBeDiscardedIndex[i] == true) {
				System.out.print(i + " ");
				drawOne = false;	
			}
		}
		return drawOne;
	}
	public boolean nPrintCardsCanBeDiscarded(ArrayList<Card> pHand, Card top, Player player, boolean[] canBeDiscardedIndex) {
		boolean drawOne = true;
		System.out.println("YOUR CARDS OF WHICH CAN BE DISCARDED: ");

		NumberCard nTop = (NumberCard) top;

		for(int i = 0; i < pHand.size(); i++) {

			Card mCard = pHand.get(i);
			if(mCard instanceof WildCard) {
				//CASE: 3-1
				canBeDiscardedIndex[i] = true;
			} else if(mCard instanceof ActionCard) {
				//CASE: 3-2
				ActionCard aCard = (ActionCard)mCard;
				if(aCard.getColor() == nTop.getColor()) {
					
					canBeDiscardedIndex[i] = true;
				}
			} else {
				//CASE: 3-3
				NumberCard nCard = (NumberCard)mCard;		
				if(nCard.getColor() == nTop.getColor() || nCard.getNumber() == nTop.getNumber())
					canBeDiscardedIndex[i] = true;	
			}
			if(canBeDiscardedIndex[i] == true) {
				System.out.print(i + " ");
				drawOne = false;
			}
		}

		return drawOne;
	}
	public boolean wPrintCardsCanBeDiscarded(ArrayList<Card> pHand, Card top, Player player, boolean[] canBeDiscardedIndex) {

		boolean drawOne = true;
		System.out.println("YOUR CARDS OF WHICH CAN BE DISCARDED: ");

		WildCard wTop = (WildCard) top;

		for(int i = 0; i < pHand.size(); i++) {
			
			Card mCard = pHand.get(i);
			if(mCard instanceof WildCard) {
				//CASE: 3-1

				canBeDiscardedIndex[i] = true;
			} else if(mCard instanceof ActionCard) {
				//CASE: 3-2
				if(CURRENT_WILD_COLOR == mCard.getColor())
					canBeDiscardedIndex[i] = true;
			} else {
				//CASE: 3-3
				if(CURRENT_WILD_COLOR == mCard.getColor())
					canBeDiscardedIndex[i] = true;
			}
			if(canBeDiscardedIndex[i] == true) {
				System.out.print(i + " ");
				drawOne = false;
			}	
		}
		return drawOne;
	}
	public void pickCard(ArrayList<Card> pHand, Card top, Player player, boolean[] canBeDiscardedIndex) {
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

			ActionCard aCard = (ActionCard)pHand.get(var);
			if(aCard.getAction() == Actions.REVERSE)
				IS_CLOCKWISE = !IS_CLOCKWISE;
			else if (aCard.getAction() == Actions.SKIP) {
				IS_SKIPPED = true;
			} else {
				IS_SKIPPED = true;
				CURRENT_PLUS += 2;
			}
		} else {
			System.out.println("PICK A COLOR: (0)RED, (1)BLUE, (2)YELLOW, (3)GREEN ");
			int color = -1;
			color = scanner.nextInt();
			while(true) {
				if(color > 3 || color < 0) {
					System.out.println("INVALID, PICK AGAIN.");
					color = scanner.nextInt();
					continue;
				}
				break;
			}

			CURRENT_WILD_COLOR = Colors.values()[color];
			WildCard wCard = (WildCard)pHand.get(var);
			if(wCard.getWildType() == WildType.WILD_DRAW_FOUR) {
				IS_SKIPPED = true;
				CURRENT_PLUS += 4;				
			}
		}	
		discardPile.add( pHand.remove(var) );
	}

	public void playGame() {

		// suppose there are 3 players joined
		for( int i = 0; i < HUMAN_PLAYER_NUM; i++ ) {
			players.add(new HumanPlayer());
		}

		initDeck(deck);
		DEALER_INDEX = pickDealer(deck, players);
		shuffle();
		dealCards(deck, players);
		flipTopCard(deck, players);
		
		int round = 1;
		Card top = deck.getLast();
		discardPile.add(top);

		boolean IS_NOT_OVER = true;
		while(IS_NOT_OVER) {

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
				topIsNumberCard(pHand, top, players.get(CURRENT_INDEX));
			} else if (top instanceof ActionCard) {				
				topIsActionCard(pHand, top, players.get(CURRENT_INDEX));
			} else {

				WildCard wTop = (WildCard) top;
				if(round == 1 && wTop.getWildType() == WildType.WILD) {
					System.out.println("FIRST ROUND ONLY: PICK A COLOR: (0)RED, (1)BLUE, (2)YELLOW, (3)GREEN ");
					int color = -1;
					
					Scanner scanner = new Scanner(System.in);
					color = scanner.nextInt();
					while(true) {
						if(color > 3 || color < 0) {
							System.out.println("INVALID, PICK AGAIN.");
							color = scanner.nextInt();
							continue;
						}
						break;
					}
					CURRENT_WILD_COLOR = Colors.values()[color];					
				} else
					topIsWildCard(pHand, top, players.get(CURRENT_INDEX));
			}

			top = discardPile.getLast();
			if(pHand.size() == 0)
				IS_NOT_OVER = false;			

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
		System.out.println("*********************************************");
		System.out.println("CONGRATUATIONS! PLAYER " + (CURRENT_INDEX + 1)+ " WINS!");
		// DEBUG:
		// Deque<Card> testDeck = new LinkedList<Card>();

		// testDeck.add(new ActionCard(Actions.values()[2], Colors.BLUE));
		// testDeck.add(new WildCard(WildType.values()[0], Colors.BLACK));
		// testDeck.add(new WildCard(WildType.values()[1], Colors.BLACK));
		// flipTopCard(testDeck, players);

	}
}