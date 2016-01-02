import foop.*;
import java.util.ArrayList;

public class POOCasino {
	
	private static int N_ROUND; //final
	private static int N_PLAYERS; //final
	private static int N_CHIP; //final
	public static final String SYSTEM_MESSAGE = "|SYSTEM_MESSAGE| ";
	public static final String DEALER_MESSAGE = "|DEALER_MESSAGE| ";

	private static final int MAX_N_PLAYERS = 4;

	private static int CURRENT_N_ROUND = 1;
	private static int CURRENT_N_PLAYERS = 0;


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
		ArrayList<Hand> current_table = new ArrayList<Hand>();

		DeckOfCards deckOfCards = new DeckOfCards();

		for(int i = 2; i < args.length; i++) {

			players.add(new PlayerB03901023(N_CHIP));
			playerStatusArray.add(new PlayerStatus(args[i]));
			current_table.add(new Hand(new ArrayList<Card>()));
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
				int bet = players.get(i).make_bet(current_table, CURRENT_N_PLAYERS, i);
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

			if(Dealer.faceupCardIsAce()) {
				System.out.println(DEALER_MESSAGE + "face-up card is ACE...");
				System.out.println(DEALER_MESSAGE + "ask each player whether to buy an insurance of Bi/2 or not");

				//ask each player whether to buy an insurance of Bi/2 or not.
				for(int i = 0; i < CURRENT_N_PLAYERS; i++) {
					System.out.println(DEALER_MESSAGE + playerStatusArray.get(i).getName() + "...");

					Card my_open = playerStatusArray.get(i).getHand().get(1);
					Card dealer_open = Dealer.getHand().get(1);
					boolean buy = players.get(i).buy_insurance(my_open, dealer_open, current_table);
					playerStatusArray.get(i).setInsurance(buy);
				}
			} else {
				System.out.println(DEALER_MESSAGE + "face-up card is not ACE... continue");
			}

		}

	}
}