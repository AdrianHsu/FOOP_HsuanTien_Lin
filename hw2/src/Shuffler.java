import java.util.ArrayList;

public class Shuffler {
	
	Shuffler(int _N, ArrayList<Card> totalCards) {
		
		if(totalCards.size() > 0) {
			totalCards.clear();
		}

		mRI.setSize(_N);

		for(int i = 0; i < _N; i ++) {
			int index = mRI.getNext();
			Card _card = new Card( Ranks.values()[ index % 13 ],
								   Suits.values()[ index %  4 ] );
			totalCards.add( _card );
		}
		// DEBUG: non-shuffle totalCards, to ensure that 10 kinds of payoffs are corrent
		// for(int i = 0; i < 47; i++) {
		// 		Card _card = new Card( Ranks.values()[0] , Suits.values()[0] );
		// 		totalCards.add( _card );
		// }
		// Card card1 = new Card( Ranks.values()[9] , Suits.values()[3] );
		// totalCards.add( card1 );
		// Card card2 = new Card( Ranks.values()[9] , Suits.values()[2] );
		// totalCards.add( card2 );
		// Card card3 = new Card( Ranks.values()[11] , Suits.values()[3] );
		// totalCards.add( card3 );
		// Card card4 = new Card( Ranks.values()[9] , Suits.values()[1] );
		// totalCards.add( card4 );
		// Card card5 = new Card( Ranks.values()[3] , Suits.values()[3] );
		// totalCards.add( card5 );
		// for(int i = 0; i < 13; i++) {
		// 	for(int j = 0; j < 4; j++) {
		// 		Card _card = new Card( Ranks.values()[i] , Suits.values()[j] );
		// 		totalCards.add( _card );
		// 	}
		// }
	}

	private RandomIndex mRI = new RandomIndex();
}