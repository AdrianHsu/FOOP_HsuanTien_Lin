import java.util.ArrayList;
import java.util.Collections;


public class Player {

	public Player() {
		hand = new ArrayList<BaseCard>();
	}
	public void sortHand() {
		Collections.sort(hand, BaseCard.CardComparator);
	}
	public void dropPairs() {
		if(hand.size() == 0) {
			return;
		}
		for(int i = 1; i < hand.size(); i++) {

			if(hand.get(i).getRank() == hand.get(i - 1).getRank()) {

				if(!(hand.get(i).getRank().ordinal() == 13)) { //is not both are jokers
					hand.remove(i);
					hand.remove(i - 1);
					hand.trimToSize();
					i--;
				}
			}
		}
	}
	public void removeCard(int index) {
		hand.remove(index);
		hand.trimToSize();
	}
	public void addCard(BaseCard _baseCard) {
		hand.add(_baseCard);
	}
	private ArrayList<BaseCard> hand;
	public ArrayList<BaseCard> getHand() { return hand; };
}