import java.util.ArrayList;

public class Shuffler {
	
	Shuffler(int _size, ArrayList<Card> totalCards) {
		
		if(totalCards.size() > 0) {
			totalCards.clear();
		}

		mRI.setSize(_size);

		// for(int i = 0; i < _size; i ++) {
		// 	int index = mRI.getNext();
		// 	Card _card = new Card( Ranks.values()[ index % 13 ],
		// 						   Suits.values()[ index %  4 ] );
		// 	totalCards.add( _card );
		// }
		// DEBUG: non-shuffle totalCards, to ensure that 10 kinds of payoffs are corrent
		for(int i = 0; i < 13; i++) {
			for(int j = 0; j < 4; j++) {
				Card _card = new Card( Ranks.values()[i] , Suits.values()[j] );
				totalCards.add( _card );
			}
		}
	}

	private RandomIndex mRI = new RandomIndex();
}