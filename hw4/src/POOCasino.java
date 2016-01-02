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

			System.out.println(DEALER_MESSAGE + "retrive last_table cards and shuffle...");

			for(int i = 0; i < N_PLAYERS; i++) {
				int bet = players.get(i).make_bet(getTable(playerStatusArray), CURRENT_N_PLAYERS, i);
				playerStatusArray.get(i).setBet(bet);
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
			
			System.out.println(DEALER_MESSAGE + "Dealer face-up card value: " + Dealer.getHand().get(1).getValue());

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
					System.out.println(DEALER_MESSAGE + mPlayerStatus.getName() + " flip up the face-down card... ");
					System.out.println(DEALER_MESSAGE + "Suit: " + facedown.getSuit() + "; Value: " + facedown.getValue());

					
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
						System.out.println("|==============================================================|");
   						System.out.println(DEALER_MESSAGE + 
   							"do split( or get Blackjack ) and then double down is not allowed"); 
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
							}
							if(mPlayerStatus.standSplit[j]) {
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
						continue;
					}
					// non-split cases
					if(mPlayerStatus.getBlackjack()) {
						System.out.println(DEALER_MESSAGE + "player " + mPlayerStatus.getName() + 
							": has already BLACKJACK, total = " + countHandSoftTotal(mPlayerStatus.getHand()));
					}					
					if(mPlayerStatus.getSurrender()) {
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
				if(count == 4 + splitNum) {
					System.out.println(DEALER_MESSAGE + "All players are done");
					break;
				}
			}
			// execute dealer actions:
			System.out.println("|==============================================================|");
			System.out.println(DEALER_MESSAGE + " dealer turns: ");

			// Flip up (open) the face-down card.
			Card facedown = Dealer.getHand().get(0);
			Card faceup = Dealer.getHand().get(1);
			System.out.println(DEALER_MESSAGE + " flip up the face-down card... ");
			System.out.println(DEALER_MESSAGE + "Suit: " + facedown.getSuit() + "; Value: " + facedown.getValue());

			boolean blackjack = false;
			if(countHandSoftTotal(Dealer.getHand()) == 21) {
				blackjack = true;
				Dealer.setBlackjack(blackjack);
				System.out.println(DEALER_MESSAGE + " Got a BLACKJACK!, set stand = true");
				Dealer.setStand(true);
			}
			// If the total card value is ≤ 16 or is a soft-17, hit.
			while(true) {
				ArrayList<Card> cards = Dealer.getHand();
				if(countHandSoftTotal(cards) <= 17) {
					cards.add(deckOfCards.removeTop());
				} else {
					// Otherwise, stand.
					int total = countHandSoftTotal(cards);
					System.out.println(DEALER_MESSAGE + "dealer's total value(soft) is >= 17, which is" + total);
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
				double bet = (double)mPlayerStatus.getBet();
				
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
								System.out.println(e.toString());
							}
						}
						// CASE3. If player i gets a Blackjack, the player gets 3Bi/2 more chips 
						// unless the dealer also gets a Blackjack.
						// In the latter case, it is a “push” and the player just get 0 more chips.
						if(mPlayerStatus.blackjackSplit[j]) {
							if(Dealer.getBlackjack())
								try {
									mPlayer.decrease_chips(0);
								} catch(Exception e) {
								System.out.println(e.toString());
								}
							else
								try {
									mPlayer.increase_chips((3 * bet)/ 2);
								} catch(Exception e) {
								System.out.println(e.toString());
								}
						}
						
						// CASE4. If player i doesn’t get a Blackjack, and if the dealer gets busted, each player gets Bi more chips
						if(!mPlayerStatus.blackjackSplit[j] && Dealer.getBusted()) {
							try {
								mPlayer.increase_chips(bet);
							} catch(Exception e) {
								System.out.println(e.toString());
							}
						}
						// CASE5. If player i doesn’t get a Blackjack, and if the dealer gets a Blackjack, 
						// the bet Bi goes to the casino. If the player bought an insurance, 
						// however, she/he gets Bi back from the insurance, making it even.
						if(!mPlayerStatus.blackjackSplit[j] && Dealer.getBlackjack()) {
							if(mPlayerStatus.getInsurance())
								try {
									mPlayer.decrease_chips(0);
								} catch(Exception e) {
									System.out.println(e.toString());
								}
							else
								try {
									mPlayer.decrease_chips(bet);
								} catch(Exception e) {
									System.out.println(e.toString());
								}
						}
						// CASE6. Finally, if neither player i nor the dealer gets a Blackjack, 
						// and neither of them gets busted, the sum of face values on the 
						// dealer’s and on player i’s hands are compared. 
						// If the dealer gets more, the player loses and Bi goes to the casino. 
						// If the player gets more, the player wins Bi more chips. 
						// Otherwise it is a “push” and the player just get 0 more chips.
						if(!mPlayerStatus.blackjackSplit[j] && !Dealer.getBlackjack()
							&& !mPlayerStatus.bustedSplit[j] && !Dealer.getBusted()) {
							int playerSum = countHandSoftTotal(hand);
							int dealerSum = countHandSoftTotal(Dealer.getHand());
							if(dealerSum > playerSum)
								try {
									mPlayer.decrease_chips(bet);
								} catch(Exception e) {
									System.out.println(e.toString());
								}
							else if(dealerSum < playerSum)
								try {
									mPlayer.increase_chips(bet);
								} catch(Exception e) {
									System.out.println(e.toString());
								}
							else
								try {
									mPlayer.decrease_chips(0);
								} catch(Exception e) {
									System.out.println(e.toString());
								}
						}
					}
				} else {

					// CASE1. If player i surrenders, 1Bi/2 goes to the casino.
					if(mPlayerStatus.getSurrender()) {
						try {
							mPlayer.decrease_chips(bet / 2);
						} catch(Exception e) {
							System.out.println(e.toString());
						}
					}
					// CASE2. If player i gets busted, Bi goes to the casino.
					if(mPlayerStatus.getBusted()) {
						try {
							mPlayer.decrease_chips(bet);
						} catch(Exception e) {
							System.out.println(e.toString());
						}
					}
					// CASE3. If player i gets a Blackjack, the player gets 3Bi/2 more chips unless the dealer also gets a Blackjack.
					// In the latter case, it is a “push” and the player just get 0 more chips.
					if(mPlayerStatus.getBlackjack()) {
						if(Dealer.getBlackjack())
							try {
								mPlayer.decrease_chips(0);
							} catch(Exception e) {
								System.out.println(e.toString());
							}
						else
							try {
								mPlayer.increase_chips((3 * bet) / 2);
							} catch(Exception e) {
								System.out.println(e.toString());
							}
					}
					// CASE4. If player i doesn’t get a Blackjack, and if the dealer gets busted, each player gets Bi more chips
					if(!mPlayerStatus.getBlackjack() && Dealer.getBusted()) {
						try {
							mPlayer.increase_chips(bet);
						} catch(Exception e) {
							System.out.println(e.toString());
						}
					}
					// CASE5. If player i doesn’t get a Blackjack, and if the dealer gets a Blackjack, 
					// the bet Bi goes to the casino. If the player bought an insurance, 
					// however, she/he gets Bi back from the insurance, making it even.
					if(!mPlayerStatus.getBlackjack() && Dealer.getBlackjack()) {
						if(mPlayerStatus.getInsurance())
							try {
								mPlayer.decrease_chips(0);
							} catch(Exception e) {
								System.out.println(e.toString());
							}
						else
							try {
								mPlayer.decrease_chips(bet);
							} catch(Exception e) {
								System.out.println(e.toString());
							}
					}
					// CASE6. Finally, if neither player i nor the dealer gets a Blackjack, 
					// and neither of them gets busted, the sum of face values on the 
					// dealer’s and on player i’s hands are compared. 
					// If the dealer gets more, the player loses and Bi goes to the casino. 
					// If the player gets more, the player wins Bi more chips. 
					// Otherwise it is a “push” and the player just get 0 more chips.
					if(!mPlayerStatus.getBlackjack() && !Dealer.getBlackjack()
						&& !mPlayerStatus.getBusted() && !Dealer.getBusted()) {

						ArrayList<Card> hand = mPlayerStatus.getHand();
						int playerSum = countHandSoftTotal(hand);
						int dealerSum = countHandSoftTotal(Dealer.getHand());
						if(dealerSum > playerSum)
							try {
								mPlayer.decrease_chips(bet);
							} catch(Exception e) {
								System.out.println(e.toString());
							}
						else if(dealerSum < playerSum)
							try {
								mPlayer.increase_chips(bet);
							} catch(Exception e) {
								System.out.println(e.toString());
							}
						else
							try {
								mPlayer.decrease_chips(0);
							} catch(Exception e) {
								System.out.println(e.toString());
							}
					}

					CURRENT_N_ROUND++;
				}
			}

		}
	}
}