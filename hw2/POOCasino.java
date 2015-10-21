import java.util.ArrayList;
import java.util.Collections;

public class POOCasino {

	static final int NUM_TOTAL_CARDS = 52;
	static final int [] payoffTable = {0, 1, 2, 3, 4, 6, 9, 25, 50, 250};
	static final String[] sHandType = { "others", "Jacks or better", "two pair", "three of a kind",
										"straight", "flush", "full house", "four of a kind", 
										"straight flush", "royal flush"};

	public static HandTypes determineHand(ArrayList<Card> _hand) {

		Collections.sort(_hand, Card.CardComparator);

		int [] rank = {0, 0, 0, 0, 0}; // init with arbitrary values
		int [] suit = {0, 0, 0, 0, 0};
		for(int i = 0; i < 5; i++) {
			rank[ i ] = _hand.get(i).getRank().ordinal();
			suit[ i ] = _hand.get(i).getSuit().ordinal();
		}
		// CASE1: ROYAL_FLUSH
		Boolean result = true;
		int rankTotal = 0;
		int _suit = 0;

		for(int i = 0; i < 5; i ++) {
			rankTotal += rank[ i ];
			if(i == 0) {
				_suit = suit[ i ];
			}
			else {
				// 5 same unit
				if(_suit != suit[ i ]) {
					result = false;
					break;
				}
			}
		}
		// 12 + 11 + 10 + 9 + 8 = 50
		if(rankTotal == 50 && result) {
			return HandTypes.ROYAL_FLUSH;
		}

		// CASE2: STRAIGHT_FLUSH
		result = true;
		rankTotal = 0;
		_suit = 0;

		for(int i = 1; i < 5; i ++) {
			if( rank[ i ] - 1 != rank[ i - 1 ]) {
				result = false;
				break;
			}
			if(i == 1) {
				_suit = suit[ i ];
			}
			else {
				// 5 same unit
				if(_suit != suit[ i ]) {
					result = false;
					break;
				}
			}
		}
		if(result && _suit == suit[0] ) {
			return HandTypes.STRAIGHT_FLUSH;
		}
		return HandTypes.OTHERS;
	}


	public static void main( String [] args ) {

		final String author = "b03901023 Pin-Chun Hsu";
		Computer mComputer = new Computer(author);

		// DEBUG: To ensure that shuffler works well in totalCards
		// for(int i = 0; i < NUM_TOTAL_CARDS; i++) {
		// 	System.out.println(mComputer.totalCards.get(i).getRank().ordinal());
		// 	System.out.println(mComputer.totalCards.get(i).getSuit().ordinal());
		// }

		Player mPlayer = new Player();
		mComputer.init(mPlayer);

		int round = 1;

		while( true ) {

			Shuffler mShuffler = new Shuffler( NUM_TOTAL_CARDS, mComputer.totalCards);
			mPlayer.setBet( mComputer.enterBet(round) );
			if(mPlayer.getBet() == 0) {
				mComputer.quitGame(mPlayer.getName(), mPlayer.getDollars(), round);
				break;
			}

			mComputer.initHand(mPlayer.hand);
			mComputer.keepHand(mPlayer.hand);
			mComputer.payoffHand(mPlayer);

			round++;
		}
	}
}