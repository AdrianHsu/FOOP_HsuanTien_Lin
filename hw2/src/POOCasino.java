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
		int _suit = -1;

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
		// rankTotal = 0;
		_suit = suit[0];

		for(int i = 1; i < 5; i ++) {
			if( rank[ i ] - 1 != rank[ i - 1 ]) {
				result = false;
				break;
			}
			if(_suit != suit[ i ]) {
				result = false;
				break;
			}
		}
		if(result) {
			return HandTypes.STRAIGHT_FLUSH;
		}

		// CASE3: FOUR_OF_A_KIND
		result = true;
		Boolean result2 = true;
		// rankTotal = 0;
		_suit = -1;

		for(int i = 1; i < 4; i++) {
			// CASE: 3,3,3,3,5
			if(rank[ i ] != rank[ i - 1 ]) {
				result = false;
			}
			// CASE: 2,3,3,3,3
			if(rank[ i + 1 ] != rank [ i ]) {
				result2 = false;
			}
		}
		if(result || result2) {
			return HandTypes.FOUR_OF_A_KIND;
		}
		// CASE4: FULL_HOUSE
		result = true;
		result2 = true;
		// rankTotal = 0;
		_suit = -1;

		if(rank[0] == rank[1] && rank[1] == rank[2] && rank[3] == rank[4]
		|| rank[0] == rank[1] && rank[2] == rank[3] && rank[3] == rank[4]) {
			return HandTypes.FOUR_OF_A_KIND;
		}

		// CASE5: FLUSH
		result = true;
		result2 = true;
		// rankTotal = 0;
		_suit = suit[0];

		for(int i = 0; i < 5; i++) {
			if(_suit != suit[ i ]) {
				result = false;
				break;
			}
		}
		if(result) {
			return HandTypes.FLUSH;
		}
		// CASE6: STRAIGHT
		result = true;
		result2 = true;
		// rankTotal = 0;
		_suit = -1;

		for(int i = 1; i < 5; i ++) {
			if( rank[ i ] - 1 != rank[ i - 1 ]) {
				result = false;
				break;
			}
		}
		if(result) {
			return HandTypes.STRAIGHT;
		}
		// CASE7: THREE_OF_A_KIND
		result = true;
		result2 = true;
		// rankTotal = 0;
		_suit = -1;

		if(rank[0] == rank[1] && rank[1] == rank[2]
		|| rank[1] == rank[2] && rank[2] == rank[3]
		|| rank[2] == rank[3] && rank[3] == rank[4]) {
			return HandTypes.THREE_OF_A_KIND;
		}
		// CASE8: TWO_PAIR
		result = true;
		result2 = true;
		// rankTotal = 0;
		_suit = -1;
		// CASE: 2,2,4,4,6
		// CASE: 2,2,4,6,6
		// CASE: 2,4,4,6,6
		if(rank[0] == rank[1] && rank[2] == rank[3]
		|| rank[0] == rank[1] && rank[3] == rank[4]
		|| rank[1] == rank[2] && rank[3] == rank[4]) {
			return HandTypes.TWO_PAIR;
		}
		// CASE9: JACKS_OR_BETTER
		result = true;
		result2 = true;
		// rankTotal = 0;
		_suit = -1;

		for(int i = 1; i < 5; i ++) {
			// 9 <= J, Q, K, A
			if(rank[i] == rank[i - 1] && 9 <= rank[i]) {
				return HandTypes.JACKS_OR_BETTER;
			}
		}
		// CASE10: OTHERS
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