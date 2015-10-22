import java.util.ArrayList;
import java.util.Collections;

public class POOCasino {

	static final String AUTHOR = "b03901023 Pin-Chun Hsu";
	static final int NUM_TOTAL_CARDS = 52;
	static final int DOLLARS_GIVEN = 1000;

	static final int [] payoffTable = {0, 1, 2, 3, 4, 6, 9, 25, 50, 250};
	static final String[] sHandType = { "others", "Jacks or better", "two pair", "three of a kind",
										"straight", "flush", "full house", "four of a kind", 
										"straight flush", "royal flush"};

	public static void main( String [] args ) {

		Computer mComputer = new Computer(AUTHOR);
		Player mPlayer = new Player(DOLLARS_GIVEN);
		int round = 1;

		// DEBUG: To ensure that shuffler works well in totalCards
		// for(int i = 0; i < NUM_TOTAL_CARDS; i++) {
		// 	System.out.println(mComputer.totalCards.get(i).getRank().ordinal());
		// 	System.out.println(mComputer.totalCards.get(i).getSuit().ordinal());
		// }

		while( true ) {
			
			// The computer virtually opens a new deck of 52 cards, and shuffles it.
			mComputer.shuffle();
			// The player can choose to bet 1 to 5 P-dollars for the round.
			mPlayer.enterBet(round);

			if(mPlayer.getBet() == 0) {
				mComputer.quitGame(mPlayer.getName(), mPlayer.getDollars(), round);
				break;
			}
			// The computer distributes the player 5 cards from the shuffled deck.
			mComputer.distributeCards(mPlayer);
			// The player can choose to keep none, 1, 2, · · · , to 5 cards out of those 5 on hand. 
			mPlayer.keepHand(mComputer);
			// The computer then determines the best hand to describe the final 5 cards. 
			HandTypes mHandType = mComputer.determineHand(mPlayer.hand);
			// The computer then pays the user payoff P-dollars
			mComputer.payoff(mPlayer, mHandType);

			round++;
		}
	}
}