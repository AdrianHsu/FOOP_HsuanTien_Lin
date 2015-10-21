import java.util.ArrayList;
import java.util.Collections;

public class POOCasino {

	static final int NUM_TOTAL_CARDS = 52;
	static final int [] payoffTable = {0, 1, 2, 3, 4, 6, 9, 25, 50, 250};
	static final String[] sHandType = { "others", "Jacks or better", "two pair", "three of a kind",
										"straight", "flush", "full house", "four of a kind", 
										"straight flush", "royal flush"};

	public static void main( String [] args ) {

		final String author = "b03901023 Pin-Chun Hsu";
		Computer mComputer = new Computer(author);
		Player mPlayer = new Player();
		int round = 1;

		// DEBUG: To ensure that shuffler works well in totalCards
		// for(int i = 0; i < NUM_TOTAL_CARDS; i++) {
		// 	System.out.println(mComputer.totalCards.get(i).getRank().ordinal());
		// 	System.out.println(mComputer.totalCards.get(i).getSuit().ordinal());
		// }
		while( true ) {

			mComputer.shuffle();
			mPlayer.enterBet(round);
			if(mPlayer.getBet() == 0) {
				mComputer.quitGame(mPlayer.getName(), mPlayer.getDollars(), round);
				break;
			}

			mComputer.distributeCards(mPlayer);
			mPlayer.keepHand(mComputer);
			mComputer.payoff(mPlayer);

			round++;
		}
	}
}