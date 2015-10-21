import java.util.ArrayList;

public class Shuffler {
	
	Shuffler(int _size, ArrayList<Card> totalCards) {
		
		mRI.setSize(_size);
		for(int i = 0; i < _size; i ++) {
			int index = mRI.getNext();
			Card _card = new Card( Ranks.values()[ index % 13 ],
								   Suits.values()[ index %  4 ] );
			totalCards.add( _card );
		}
	}

	private RandomIndex mRI = new RandomIndex();
}