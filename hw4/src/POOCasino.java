import foop.*;
import java.util.ArrayList;

public class POOCasino {
	
	private static int N_ROUND; //final
	private static int N_PLAYERS; //final
	private static int N_CHIP; //final
	private static final String SYSTEM_MESSAGE = "|SYSTEM_MESSAGE| ";
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

			for(int i = 0; i < CURRENT_N_PLAYERS; i++)
				players.get(i).toString();

			for(int i = 0; i < N_PLAYERS; i++) {
				int bet = players.get(i).make_bet(current_table, CURRENT_N_PLAYERS, i);
				playerStatusArray.get(i).setBet(bet);

			}
			// face down card
			for(int i = 0; i < N_PLAYERS; i++) {

				ArrayList<Card> cards = current_table.get(i).getCards();
				deckOfCards.insert(cards);
				cards.clear();

				cards.add(deckOfCards.removeTop());
			}
			deckOfCards.insert(Dealer.getHand().getCards());
			Dealer.getHand().getCards().clear();

			Dealer.getHand().getCards().add(deckOfCards.removeTop());

			// face top card
			for(int i = 0; i < N_PLAYERS; i++) {

				ArrayList<Card> cards = current_table.get(i).getCards();
				cards.add(deckOfCards.removeTop());
			}
			Dealer.getHand().getCards().add(deckOfCards.removeTop());
			
			if(Dealer.faceupCardIsAce()) {

				//ask each player whether to buy an insurance of Bi/2 or not.

			}

		}

	}
}