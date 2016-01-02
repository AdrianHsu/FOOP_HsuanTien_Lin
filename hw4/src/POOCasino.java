import foop.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class POOCasino {
	
	private static int N_ROUND; //final
	private static int N_PLAYERS; //final
	private static int N_CHIP; //final
	public static final String SYSTEM_MESSAGE = "|SYSTEM_MESSAGE| ";
	public static final String DEALER_MESSAGE = "|DEALER_MESSAGE| ";

	private static final int MAX_N_PLAYERS = 4;

	private static int CURRENT_N_ROUND = 1;
	private static int CURRENT_N_PLAYERS = 0;

	public static ArrayList<Hand> getTable(ArrayList<PlayerStatus> playerStatusArray) {

		ArrayList<Hand> current_table = new ArrayList<Hand>();
		for(int i = 0; i < CURRENT_N_PLAYERS; i++) {

			Hand hand = new Hand(playerStatusArray.get(i).getHand());
			current_table.add(hand);
		}
		return current_table;
	}

	public static Comparator<Card> CardComparator = new Comparator<Card>() {

		public int compare(Card b1, Card b2) {
			if( b1.getValue() != b2.getValue() )
				return b1.getValue() - b2.getValue();
			else
				return b1.getSuit() - b2.getSuit();
		}
	};

	public static boolean checkBusted(ArrayList<Card> hand) {

		int total = 0;
		Collections.sort(hand, CardComparator);
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i).getValue() > 10) // 11, 12, 13 for J,Q,K
				total += 10;
			else
				total += hand.get(i).getValue(); // ACE for 1, check busted only
		}
		if(total > 21)
			return true;
		else
			return false;
	}
	public static int checkBustedValue(ArrayList<Card> hand) {

		int total = 0;
		Collections.sort(hand, CardComparator);
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i).getValue() > 10) // 11, 12, 13 for J,Q,K
				total += 10;
			else
				total += hand.get(i).getValue(); // ACE for 1, check busted only
		}
		return total;
	}
	public static int countHandSoftTotal(ArrayList<Card> hand) {

		int total = 0;
		Collections.sort(hand, CardComparator);
		int numOfAce = 0;

		for(int i = 0 ; i < hand.size(); i++) {
			if(hand.get(i).getValue() == 1)
				numOfAce++;
		}
		if(numOfAce == 0) {
			
			for(int i = 0; i < hand.size(); i++) {
				if(hand.get(i).getValue() > 10) // 11, 12, 13 for J,Q,K
					total += 10;
				else
					total += hand.get(i).getValue();
			}
		} else if (numOfAce == 1) {
			
			for(int i = 1; i < hand.size(); i++) { // start from i = 1 since sorted
				if(hand.get(i).getValue() > 10) // 11, 12, 13 for J,Q,K
					total += 10;
				else
					total += hand.get(i).getValue();
			}
			if(total + 11 > 21)
				total += 1;
			else
				total += 11;
		} else if (numOfAce > 1) { //numOfAce == 2, 3, 4
			
			for(int i = numOfAce; i < hand.size(); i++) { // start from i = 1 since sorted
				if(hand.get(i).getValue() > 10) // 11, 12, 13 for J,Q,K
					total += 10;
				else
					total += hand.get(i).getValue();
			}
			total += numOfAce - 1;
			if(total + 11 > 21)
				total += 1;
			else
				total += 11;
		}
		return total;
	}

	public static void main(String [] args) {

		//RUN COMMAND:
		//java POOCasino nRound nChip Player1 Player2 Player3 Player4

		//BOUND CHECKING
		if(args.length <= 2) 
			throw new IndexOutOfBoundsException(SYSTEM_MESSAGE + "0 PLAYER ATTENDED");
		else if(args.length > 2 + MAX_N_PLAYERS)
			throw new IndexOutOfBoundsException(SYSTEM_MESSAGE + "MORE THAN 4 PLAYERS ATTENDED");

		// INIT

		System.out.println(SYSTEM_MESSAGE + "Initializing...");
		N_ROUND = Integer.valueOf(args[0]);
		N_CHIP = Integer.valueOf(args[1]);
		ArrayList<Player> players = new ArrayList<>();
		ArrayList<PlayerStatus> playerStatusArray = new ArrayList<>();

		DeckOfCards deckOfCards = new DeckOfCards();

		for(int i = 2; i < args.length; i++) {

			players.add(new PlayerB03901023(N_CHIP));
			playerStatusArray.add(new PlayerStatus(args[i]));
			N_PLAYERS++;
		}
		CURRENT_N_PLAYERS = N_PLAYERS;

		System.out.println("|==============================================================|");
		System.out.println("|   @ POOCasino: BlackJack CardGame                             ");
		System.out.println("|   @ Course: [CSIE1214] Fundamental OOP hw4                    ");
		System.out.println("|   @ https://www.csie.ntu.edu.tw/~htlin/course/foop15fall/     ");
		System.out.println("|   @ Instructor: Hsuan-Tien Lin                                ");
		System.out.println("|   @ Student ID / Name: B03901023 / Pin-Chun Hsu               ");
		System.out.println("|==============================================================|");
		System.out.println(SYSTEM_MESSAGE + "NUMBER OF CHIPS INIT: " + N_CHIP);
		System.out.println(SYSTEM_MESSAGE + "NUMBER OF ROUNDS INIT: " + N_ROUND);
		System.out.println(SYSTEM_MESSAGE + "NUMBER OF PLAYERS INIT: " + N_PLAYERS);
		System.out.println("|==============================================================|");


		while(CURRENT_N_ROUND <= N_ROUND) {


			System.out.println(SYSTEM_MESSAGE + "ROUND: " + CURRENT_N_ROUND + "/" + N_ROUND);
			System.out.println(SYSTEM_MESSAGE + "NUMBER OF PLAYERS: " + CURRENT_N_PLAYERS + "/" + N_PLAYERS);
			System.out.println("|==============================================================|");

			for(int i = 0; i < CURRENT_N_PLAYERS; i++)
				players.get(i).toString();

			for(int i = 0; i < N_PLAYERS; i++) {
				int bet = players.get(i).make_bet(getTable(playerStatusArray), CURRENT_N_PLAYERS, i);
				playerStatusArray.get(i).setBet(bet);
			}

			System.out.println(DEALER_MESSAGE + "retrive last_table cards and shuffle...");

			for(int i = 0; i < CURRENT_N_PLAYERS; i++) {
				ArrayList<Card> cards = playerStatusArray.get(i).getHand();
				deckOfCards.insert(cards);
				cards.clear();
			}
			deckOfCards.insert(Dealer.getHand());
			Dealer.getHand().clear();

			deckOfCards.shuffle();

			System.out.println(DEALER_MESSAGE + "assign face-down card to players and dealer...");
			
			// face down card
			for(int i = 0; i < N_PLAYERS; i++) {
				ArrayList<Card> cards = playerStatusArray.get(i).getHand();
				cards.add(deckOfCards.removeTop());
			}
			Dealer.getHand().add(deckOfCards.removeTop());

			System.out.println(DEALER_MESSAGE + "assign face-up card to players...");

			// face up card
			for(int i = 0; i < N_PLAYERS; i++) {

				ArrayList<Card> cards = playerStatusArray.get(i).getHand();
				cards.add(deckOfCards.removeTop());
			}
			System.out.println(DEALER_MESSAGE + "assign face-up card to dealer...");
			Dealer.getHand().add(deckOfCards.removeTop());
			
			System.out.println(DEALER_MESSAGE + "face-up card value: " + Dealer.getHand().get(1).getValue());

			// check insurance
			if(Dealer.faceupCardIsAce()) {

				System.out.println(DEALER_MESSAGE + "face-up card is ACE...");
				System.out.println(DEALER_MESSAGE + "ask each player whether to buy an insurance of Bi/2 or not");

				//ask each player whether to buy an insurance of Bi/2 or not.
				for(int i = 0; i < CURRENT_N_PLAYERS; i++) {
					System.out.println(DEALER_MESSAGE + playerStatusArray.get(i).getName() + " decides...");

					Card my_open = playerStatusArray.get(i).getHand().get(1);
					Card dealer_open = Dealer.getHand().get(1);
					boolean buy = players.get(i).buy_insurance(my_open, dealer_open, getTable(playerStatusArray));
					playerStatusArray.get(i).setInsurance(buy);
					playerStatusArray.get(i).setSurrender(false);
				}
			} else {
				System.out.println(DEALER_MESSAGE + "face-up card is not ACE... continue");
				System.out.println(DEALER_MESSAGE + "ask each player whether to surrender or not");
				for(int i = 0; i < CURRENT_N_PLAYERS; i++) {
					System.out.println(DEALER_MESSAGE + playerStatusArray.get(i).getName() + " decides...");

					Card my_open = playerStatusArray.get(i).getHand().get(1);
					Card dealer_open = Dealer.getHand().get(1);
					
					boolean sur = players.get(i).do_surrender(my_open, dealer_open, getTable(playerStatusArray));
					playerStatusArray.get(i).setSurrender(sur);
					playerStatusArray.get(i).setInsurance(false);
				}
			}
			// first deal
			for(int i = 0; i < CURRENT_N_PLAYERS; i++) {

				Player mPlayer = players.get(i);
				PlayerStatus mPlayerStatus = playerStatusArray.get(i);
				System.out.println("|==============================================================|");
				System.out.println(DEALER_MESSAGE + playerStatusArray.get(i).getName() + " turns: ");

				if(mPlayerStatus.getSurrender())
					System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
						": has already surrendered");
				else {
					
					// Flip up (open) the face-down card.
					Card facedown = mPlayerStatus.getHand().get(0);
					Card faceup = mPlayerStatus.getHand().get(1);
					System.out.println(DEALER_MESSAGE + mPlayerStatus.getName() + " flip up the face-down card... ");
					System.out.println(DEALER_MESSAGE + "Suit: " + facedown.getSuit() + "; Value: " + facedown.getValue());

					
					// If the two cards happen to be of equal face value,
					// decide whether to split. 
					boolean split = false;
					if(facedown.getValue() == faceup.getValue()) {
				
						System.out.println(DEALER_MESSAGE + "The two cards happen to be of equal face value");
						System.out.println("Decide whether to split...");

						ArrayList<Card> my_open = mPlayerStatus.getHand();
						Card dealer_open = Dealer.getHand().get(1);
						
						split = mPlayer.do_split(my_open, dealer_open, getTable(playerStatusArray));
						// If splitting, the player goes with two separate hands and continues
						// the game with the decisions below.
						mPlayerStatus.setSplit(split);
						// Re-splitting is not allowed.
					}
					// Decide whether to double down.
					if(!split) {
						System.out.println("Decide whether to double down...");
						
						Hand my_open = new Hand( mPlayerStatus.getHand() );
						Card dealer_open = Dealer.getHand().get(1);

						boolean doubledown = mPlayer.do_double(my_open, dealer_open, getTable(playerStatusArray));
						mPlayerStatus.setDoubledown(doubledown);
						if(doubledown)
							mPlayerStatus.setBet(mPlayerStatus.getBet() * 2);
					} else {
   						System.out.println(DEALER_MESSAGE + "do split and then double down is not allowed..."); 
					}
				}			
			}
			// Decide whether to hit, until a standing decision or busted, of course.
			while(true) {
				
				for(int i = 0; i < CURRENT_N_PLAYERS; i++) {

					Player mPlayer = players.get(i);
					PlayerStatus mPlayerStatus = playerStatusArray.get(i);
					System.out.println("|==============================================================|");
					System.out.println(DEALER_MESSAGE + playerStatusArray.get(i).getName() + " turns: ");

					if(mPlayerStatus.getSurrender())
						System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
							": has already surrendered");
					else if(mPlayerStatus.getStand())
						System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
							": has already stand");
					else if(mPlayerStatus.getBusted())
						System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
							": has already busted, total = " + checkBustedValue(mPlayerStatus.getHand()));
					else {
						
						Hand my_open = new Hand( mPlayerStatus.getHand() );
						Card dealer_open = Dealer.getHand().get(1);
   						System.out.println("decide whether to hit...");
   						boolean conti = mPlayer.hit_me(my_open, dealer_open, getTable(playerStatusArray));
   						if(!conti) {
   							mPlayerStatus.setStand(!conti);
							System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
							" stand...");
   						} else {
   							ArrayList<Card> cards = mPlayerStatus.getHand();
							cards.add(deckOfCards.removeTop());
							
							if(checkBusted(cards)) {
								mPlayerStatus.setBusted(true);
								System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
							" busted..., total = " + checkBustedValue(cards));

							} else {
								int total = countHandSoftTotal(cards);
								System.out.println(DEALER_MESSAGE + "your current total value(soft) is " + total);
							}
   						}
					}
				}
			}
		}

	}
}