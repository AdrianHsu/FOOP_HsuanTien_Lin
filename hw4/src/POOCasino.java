import foop.*;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.reflect.Constructor;

public class POOCasino {
	
	private static int N_ROUND; //final
	private static int N_PLAYERS; //final
	private static int N_CHIP; //final
	public static final String SYSTEM_MESSAGE = "|SYSTEM_MESSAGE| ";
	public static final String DEALER_MESSAGE = "|DEALER_MESSAGE| ";
	private static final String [] suit = {"SPADE", "HEART", "DIAMOND", "CLUB"};
	private static final String [] rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}; 

	private static final int MAX_N_PLAYERS = 4;

	private static int CURRENT_N_ROUND = 1;
	private static int CURRENT_N_PLAYERS = 0;

	public static ArrayList<Hand> getTable(ArrayList<PlayerStatus> playerStatusArray) {

		ArrayList<Hand> current_table = new ArrayList<Hand>();
		for(int i = 0; i < CURRENT_N_PLAYERS; i++) {

			if(playerStatusArray.get(i).getSplit()) {
				Hand hand0 = new Hand(playerStatusArray.get(i).getSplitHand(0));
				Hand hand1 = new Hand(playerStatusArray.get(i).getSplitHand(1));
				current_table.add(hand0);
				current_table.add(hand1);

			} else {
				Hand hand = new Hand(playerStatusArray.get(i).getHand());
				current_table.add(hand);
			}
		}
		return current_table;
	}

	public static boolean checkBusted(ArrayList<Card> hand) {

		int total = 0;
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
		int numOfAce = 0;
		int size = hand.size();

		for(int i = 0 ; i < size; i++) {
			if(hand.get(i).getValue() == 1) {
				// move ACE to front
				numOfAce++;
				Card ace = hand.get(i);
				hand.remove(i);
				hand.trimToSize();
				hand.add(0, ace);
			}
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

			// yours as Player1 and Player3 and his/hers as Player2 and Player4
			// DEBUG: 
			// players.add(new PlayerHuman(N_CHIP));

			try {

				Constructor c = Class.forName(args[i]).getConstructor(Integer.TYPE);
				Player foo = (Player) c.newInstance(N_CHIP);
				players.add(foo);
			} catch (Exception e) {
				e.printStackTrace();
			}
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


		while(CURRENT_N_ROUND <= N_ROUND) {

			System.out.println("|==============================================================|");
			System.out.println(SYSTEM_MESSAGE + "ROUND: " + CURRENT_N_ROUND + "/" + N_ROUND);
			System.out.println(SYSTEM_MESSAGE + "NUMBER OF PLAYERS: " + CURRENT_N_PLAYERS + "/" + N_PLAYERS);
			System.out.println("|==============================================================|");

			for(int i = 0; i < CURRENT_N_PLAYERS; i++)
				System.out.println(players.get(i).toString());

			System.out.println(DEALER_MESSAGE + "retrive last_table cards and shuffle...");

			for(int i = 0; i < N_PLAYERS; i++) {
				int bet = players.get(i).make_bet(getTable(playerStatusArray), CURRENT_N_PLAYERS, i);
				playerStatusArray.get(i).setBet(bet);
				System.out.println(DEALER_MESSAGE + playerStatusArray.get(i).getName() + " bet for " + bet + " $...");
			}

			for(int i = 0; i < CURRENT_N_PLAYERS; i++) {
				if(playerStatusArray.get(i).getSplit()){

					for(int j = 0; j < 2; j++) {
						deckOfCards.insert(playerStatusArray.get(i).getSplitHand(j));
					}
				} else {
					ArrayList<Card> cards;
					cards = playerStatusArray.get(i).getHand();
					deckOfCards.insert(cards);
				}
				playerStatusArray.get(i).reset();
			}

			deckOfCards.insert(Dealer.getHand());
			Dealer.reset();

			deckOfCards.shuffle();
			// DEBUG: 
			// System.out.println("DECK OF CARDS SIZE:" + deckOfCards.get().size());

			System.out.println(DEALER_MESSAGE + "assign face-down card to players and dealer...");
			
			// face down card
			for(int i = 0; i < CURRENT_N_PLAYERS; i++) {
				ArrayList<Card> cards = playerStatusArray.get(i).getHand();
				cards.add(deckOfCards.removeTop());
			}
			Dealer.getHand().add(deckOfCards.removeTop());

			System.out.println(DEALER_MESSAGE + "assign face-up card to players...");

			// face up card
			for(int i = 0; i < CURRENT_N_PLAYERS; i++) {

				ArrayList<Card> cards = playerStatusArray.get(i).getHand();
				cards.add(deckOfCards.removeTop());
			}
			System.out.println(DEALER_MESSAGE + "assign face-up card to dealer...");
			Dealer.getHand().add(deckOfCards.removeTop());
			// Dealer.getHand().add(new Card((byte)1, (byte)1));

			System.out.println(DEALER_MESSAGE + "flip up the face-up card... ");
			System.out.println(DEALER_MESSAGE + "Suit: " + suit[Dealer.getHand().get(1).getSuit() - 1] + "; Value: " + rank[Dealer.getHand().get(1).getValue() - 1]);

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
				}
			}
			int splitNum = 0;
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
					System.out.println(DEALER_MESSAGE + mPlayerStatus.getName() + " face-up card is... ");
					System.out.println(DEALER_MESSAGE + "Suit: " + suit[faceup.getSuit() - 1] + "; Value: " + rank[faceup.getValue() - 1]);

					System.out.println(DEALER_MESSAGE + mPlayerStatus.getName() + " flip up the face-down card... ");
					System.out.println(DEALER_MESSAGE + "Suit: " + suit[facedown.getSuit() - 1] + "; Value: " + rank[facedown.getValue() - 1]);

					
					// If the two cards happen to be of equal face value,
					// decide whether to split. 
					boolean split = false;
					if(facedown.getValue() == faceup.getValue()) {
				
						System.out.println(DEALER_MESSAGE + "The two cards happen to be of equal face value");
						
						// Hitting split aces is usually not allowed. 
						if(facedown.getValue() == 1) {
							System.out.println(DEALER_MESSAGE + "However, hitting split aces is not allowed");
						}
						else {
							System.out.println("Decide whether to split...");

							ArrayList<Card> my_open = mPlayerStatus.getHand();
							Card dealer_open = Dealer.getHand().get(1);
							
							split = mPlayer.do_split(my_open, dealer_open, getTable(playerStatusArray));
							// If splitting, the player goes with two separate hands and continues
							// the game with the decisions below.
							mPlayerStatus.setSplit(split);
							if(split)
								splitNum++;

							// Re-splitting is not allowed.
						}
					}

					boolean blackjack = false;
					if(!split) {
						if(countHandSoftTotal(mPlayerStatus.getHand()) == 21) {
							blackjack = true;
							mPlayerStatus.setBlackjack(blackjack);
							System.out.println("You got a BLACKJACK!, set blackjack = true");
							// mPlayerStatus.setStand(true);
						}
					}
					// Decide whether to double down.
					if( !split && !blackjack ) {
						System.out.println("Decide whether to double down...");
						
						Hand my_open = new Hand( mPlayerStatus.getHand() );
						Card dealer_open = Dealer.getHand().get(1);

						boolean doubledown = mPlayer.do_double(my_open, dealer_open, getTable(playerStatusArray));
						mPlayerStatus.setDoubledown(doubledown);
						if(doubledown)
							mPlayerStatus.setBet(mPlayerStatus.getBet() * 2);
					} else {
   						System.out.println("Do split( or get Blackjack ) and then double down is not allowed"); 
					}
				}			
			}
			// Decide whether to hit, until a standing decision or busted, of course.
			while(true) {
				
				int count = 0; // count for player not hit(split included, * by 2)
				for(int i = 0; i < CURRENT_N_PLAYERS; i++) {

					Player mPlayer = players.get(i);
					PlayerStatus mPlayerStatus = playerStatusArray.get(i);
					System.out.println("|==============================================================|");
					System.out.println(DEALER_MESSAGE + playerStatusArray.get(i).getName() + " turns: ");

					// split cases
					if(mPlayerStatus.getSplit()) {
						System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
							": has already splited");
						for(int j = 0; j < 2; j++) {
							System.out.println(DEALER_MESSAGE + "CURRENT HAND INDEX: " + j);
							ArrayList<Card> hand = mPlayerStatus.getSplitHand(j);

							if(mPlayerStatus.blackjackSplit[j]) {
								System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
									": (HAND"+ j + ")has already BLACKJACK, total = " + countHandSoftTotal(hand));
								count++;
							}
							else if(mPlayerStatus.standSplit[j]) {
								System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
									": (HAND"+ j + ")has already stand, total = " + checkBustedValue(hand));
								count++;
							}
							else if(mPlayerStatus.bustedSplit[j]) {
								System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
									": has already busted, total = " + checkBustedValue(hand));
								count++;
							} else {
								
								Hand my_open = new Hand( hand );
								Card dealer_open = Dealer.getHand().get(1);

								boolean blackjack = false;
								if(countHandSoftTotal(hand) == 21 && hand.size() == 2) {
									// blackjack = true;
									mPlayerStatus.blackjackSplit[j] = true;
									System.out.println("Your (HAND " + j + ") got a BLACKJACK!, set blackjack = true");
									// mPlayerStatus.standSplit[j] = true;
								}

								System.out.println("decide whether to hit...");
		   						boolean conti = mPlayer.hit_me(my_open, dealer_open, getTable(playerStatusArray));
		   						if(!conti) {
		   							// mPlayerStatus.setStand(!conti);
		   							mPlayerStatus.standSplit[j] = !conti;
									System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
									": (HAND" + j +  ") stand...");
		   						} else {
		   							// ArrayList<Card> cards = mPlayerStatus.getHand();
		   							ArrayList<Card> cards = hand;
									cards.add(deckOfCards.removeTop());
									
									if(checkBusted(cards)) {
										// mPlayerStatus.setBusted(true);
										mPlayerStatus.bustedSplit[j] = true;
										System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
									": (HAND" + j + ") busted..., total = " + checkBustedValue(cards));

									} else {
										int total = countHandSoftTotal(cards);
										System.out.println(DEALER_MESSAGE + "your current total value(soft) of (HAND " + j + ") is " + total);
									}
		   						}
							}
						}
					}
					else {
						// non-split cases
						if(mPlayerStatus.getBlackjack()) {
							System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
								": has already BLACKJACK, total = " + countHandSoftTotal(mPlayerStatus.getHand()));
							count++;
						}	else if(mPlayerStatus.getSurrender()) {
							System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
								": has already surrendered");
							count++;
						}
						else if(mPlayerStatus.getStand()) {
							System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
								": has already stand, total = " + checkBustedValue(mPlayerStatus.getHand()));
							count++;
						}
						else if(mPlayerStatus.getBusted()) {
							System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
								": has already busted, total = " + checkBustedValue(mPlayerStatus.getHand()));
							count++;
						}
						else if(mPlayerStatus.getDoubledown() && !mPlayerStatus.getBusted()) {
							System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
								": has already stand for it's a doubledown, total = " + checkBustedValue(mPlayerStatus.getHand()));
							count++;
						}
						else {
							
							Hand my_open = new Hand( mPlayerStatus.getHand() );
							Card dealer_open = Dealer.getHand().get(1);
	   						
							if(mPlayerStatus.getDoubledown() && mPlayerStatus.getHand().size() == 2) {

		   						System.out.println("doubledown, must hit one card");

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
								continue;
							}
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

				if(count == CURRENT_N_PLAYERS + splitNum) {
					System.out.println("|==============================================================|");			
					System.out.println(DEALER_MESSAGE + "All players are done");
					break;
				}
			}
			// execute dealer actions:
			System.out.println("|==============================================================|");
			System.out.println(DEALER_MESSAGE + "dealer turns: ");

			// Flip up (open) the face-down card.
			Card facedown = Dealer.getHand().get(0);
			Card faceup = Dealer.getHand().get(1);
			System.out.println(DEALER_MESSAGE + "flip up the face-down card... ");
			System.out.println(DEALER_MESSAGE + "Suit: " + suit[facedown.getSuit() - 1] + "; Value: " + rank[facedown.getValue() - 1]);

			boolean blackjack = false;
			if(countHandSoftTotal(Dealer.getHand()) == 21) {
				blackjack = true;
				Dealer.setBlackjack(blackjack);
				System.out.println(DEALER_MESSAGE + " Got a BLACKJACK!, set blackjack = true");
				// Dealer.setStand(true);
			}
			// If the total card value is ≤ 16 or is a soft-17, hit.
			while(true) {
				ArrayList<Card> cards = Dealer.getHand();
				if(countHandSoftTotal(cards) <= 17) {
					System.out.println(DEALER_MESSAGE + "dealer's total value(soft) is " + countHandSoftTotal(cards) + " <= soft-17, hit...");
					cards.add(deckOfCards.removeTop());
					Card card = cards.get(cards.size() - 1);
					System.out.println(DEALER_MESSAGE + "Suit: " + suit[card.getSuit() - 1] + "; Value: " + rank[card.getValue() - 1]);

				} else {
					// Otherwise, stand.
					int total = countHandSoftTotal(cards);
					System.out.println(DEALER_MESSAGE + "dealer's total value(soft) is " + total + " > 17");
					System.out.println(DEALER_MESSAGE + "Dealer stop hitting, stand");
					break;
				}
				if(checkBusted(cards)) {
					Dealer.setBusted(true);
					System.out.println(DEALER_MESSAGE + "Dealer busted..., total = " + checkBustedValue(cards));
					System.out.println(DEALER_MESSAGE + "Dealer stop hitting, busted");
					break;
				} else {
					int total = countHandSoftTotal(cards);
					System.out.println(DEALER_MESSAGE + "dealer's current total value(soft) is " + total);
				}
			}

			// Compare the result of the dealer to the result of the player.
			for(int i = 0; i < CURRENT_N_PLAYERS; i ++) {
				Player mPlayer = players.get(i);
				PlayerStatus mPlayerStatus = playerStatusArray.get(i);

				// NegativeException Will not happened since bet >= 0 
				double bet = (double)mPlayerStatus.getBet();
				
				// Buy Insurance will decrease bet / 2 immediately
				if(mPlayerStatus.getInsurance())
					try {	
						mPlayer.decrease_chips(bet / 2);
					} catch(Exception e) {
						e.printStackTrace();
						System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
					}

				if(mPlayerStatus.getSplit()) {

					for(int j = 0; j < 2; j++) {

						ArrayList<Card> hand = mPlayerStatus.getSplitHand(j);
						// CASE1. If player i surrenders, 1Bi/2 goes to the casino.
						// DONT-CARE

						// CASE2. If player i gets busted, Bi goes to the casino.
						if(mPlayerStatus.bustedSplit[j]) {
							try {
								
								mPlayer.decrease_chips(bet);
							} catch(Exception e) {
								e.printStackTrace();
								System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
							}
						}
						// CASE3. If player i gets a Blackjack, the player gets 3Bi/2 more chips 
						// unless the dealer also gets a Blackjack.
						// In the latter case, it is a “push” and the player just get 0 more chips.
						else if(mPlayerStatus.blackjackSplit[j]) {
							if(Dealer.getBlackjack()) {

								try {
									if(mPlayerStatus.getInsurance()) {
										mPlayer.increase_chips(bet);
									} else {
										mPlayer.increase_chips(0);
									}
								} catch(Exception e) {
									e.printStackTrace();
									System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
								}
							} else {
								try {
									//HOWEVER, blackjacks after a split are counted as
									// non-blackjack 21 when comparing against the dealer's hand.
									
									mPlayer.increase_chips(bet);
									// mPlayer.increase_chips((3 * bet) / 2);
								} catch(Exception e) {
									e.printStackTrace();
									System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
								}
							}
						}
						// CASE4. If player i doesn’t get a Blackjack, and if the dealer gets busted, each player gets Bi more chips
						else if(!mPlayerStatus.blackjackSplit[j] && Dealer.getBusted()) {
							try {
								
								mPlayer.increase_chips(bet);
							} catch(Exception e) {
								e.printStackTrace();
								System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
							}
						}
						// CASE5. If player i doesn’t get a Blackjack, and if the dealer gets a Blackjack, 
						// the bet Bi goes to the casino. If the player bought an insurance, 
						// however, she/he gets Bi back from the insurance, making it even.
						else if(!mPlayerStatus.blackjackSplit[j] && Dealer.getBlackjack()) {
							if(mPlayerStatus.getInsurance())
								try {
									
									mPlayer.decrease_chips(0);
								} catch(Exception e) {
									e.printStackTrace();
									System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
								}
							else
								try {
									
									mPlayer.decrease_chips(bet);
								} catch(Exception e) {
									e.printStackTrace();
									System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
								}
						}
						// CASE6. Finally, if neither player i nor the dealer gets a Blackjack, 
						// and neither of them gets busted, the sum of face values on the 
						// dealer’s and on player i’s hands are compared. 
						// If the dealer gets more, the player loses and Bi goes to the casino. 
						// If the player gets more, the player wins Bi more chips. 
						// Otherwise it is a “push” and the player just get 0 more chips.
						else if( (!mPlayerStatus.blackjackSplit[j] && !Dealer.getBlackjack())
							&& (!mPlayerStatus.bustedSplit[j] && !Dealer.getBusted())) {
							int playerSum = countHandSoftTotal(hand);
							int dealerSum = countHandSoftTotal(Dealer.getHand());
							if(dealerSum > playerSum)
								try {
									
									mPlayer.decrease_chips(bet);
								} catch(Exception e) {
									e.printStackTrace();
									System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
								}
							else if(dealerSum < playerSum)
								try {
									
									mPlayer.increase_chips(bet);
								} catch(Exception e) {
									e.printStackTrace();
									System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
								}
							else
								try {
									
									mPlayer.decrease_chips(0);
								} catch(Exception e) {
									e.printStackTrace();
									System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
								}
						}
					}
				} else {

					// CASE1. If player i surrenders, 1Bi/2 goes to the casino.
					if(mPlayerStatus.getSurrender()) {
						try {
							
							mPlayer.decrease_chips(bet / 2);
						} catch(Exception e) {
							e.printStackTrace();
							System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
						}
					}

					// CASE2. If player i gets busted, Bi goes to the casino.
					if(mPlayerStatus.getBusted()) {
						try {
							
							mPlayer.decrease_chips(bet);
						} catch(Exception e) {
							e.printStackTrace();
							System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
						}
					}
					// CASE3. If player i gets a Blackjack, the player gets 3Bi/2 more chips unless the dealer also gets a Blackjack.
					// In the latter case, it is a “push” and the player just get 0 more chips.
					else if(mPlayerStatus.getBlackjack()) {
						if(Dealer.getBlackjack()) {

							try {
								if(mPlayerStatus.getInsurance()) {
									mPlayer.increase_chips(bet);
								} else {
									mPlayer.increase_chips(0);
								}
							} catch(Exception e) {
								e.printStackTrace();
								System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
							}
						} else {
							try {
							
								mPlayer.increase_chips((3 * bet) / 2);
							} catch(Exception e) {
								e.printStackTrace();
								System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
							}
						}
					}
					// CASE4. If player i doesn’t get a Blackjack, and if the dealer gets busted, each player gets Bi more chips
					else if(!mPlayerStatus.getBlackjack() && Dealer.getBusted()) {
						try {
							
							mPlayer.increase_chips(bet);
						} catch(Exception e) {
							e.printStackTrace();
							System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
						}
					}
					// CASE5. If player i doesn’t get a Blackjack, and if the dealer gets a Blackjack, 
					// the bet Bi goes to the casino. If the player bought an insurance, 
					// however, she/he gets Bi back from the insurance, making it even.
					else if(!mPlayerStatus.getBlackjack() && Dealer.getBlackjack()) {
						if(mPlayerStatus.getInsurance())
							try {
								
								mPlayer.decrease_chips(0);
							} catch(Exception e) {
								e.printStackTrace();
								System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
							}
						else
							try {
								
								mPlayer.decrease_chips(bet);
							} catch(Exception e) {
								e.printStackTrace();
								System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
							}
					}
					// CASE6. Finally, if neither player i nor the dealer gets a Blackjack, 
					// and neither of them gets busted, the sum of face values on the 
					// dealer’s and on player i’s hands are compared. 
					// If the dealer gets more, the player loses and Bi goes to the casino. 
					// If the player gets more, the player wins Bi more chips. 
					// Otherwise it is a “push” and the player just get 0 more chips.
					else if((!mPlayerStatus.getBlackjack() && !Dealer.getBlackjack())
						&& (!mPlayerStatus.getBusted() && !Dealer.getBusted())) {

						ArrayList<Card> hand = mPlayerStatus.getHand();
						int playerSum = countHandSoftTotal(hand);
						int dealerSum = countHandSoftTotal(Dealer.getHand());

						if(dealerSum > playerSum)
							try {
								
								mPlayer.decrease_chips(bet);
							} catch(Exception e) {
								e.printStackTrace();
								System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
							}
						else if(dealerSum < playerSum)
							try {
								
								mPlayer.increase_chips(bet);
							} catch(Exception e) {
								e.printStackTrace();
								System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
							}
						else
							try {
								
								mPlayer.decrease_chips(0);
							} catch(Exception e) {
								e.printStackTrace();
								System.out.println(SYSTEM_MESSAGE + "EXCEPTION CATCHED...");
							}
					}
				}
			}
			CURRENT_N_ROUND++;
		}

		System.out.println("|==============================================================|");
		System.out.println(SYSTEM_MESSAGE + "POOCasino BlackJack TERMINATES SUCCESSFULLY");
		System.out.println(SYSTEM_MESSAGE + "NUMBER OF PLAYERS: " + CURRENT_N_PLAYERS + "/" + N_PLAYERS);
		System.out.println("|==============================================================|");

		for(int i = 0; i < CURRENT_N_PLAYERS; i++)
			System.out.println(players.get(i).toString());

	}
}