import java.util.ArrayList;
import java.util.Collections;


public class Player {

	public Player(int i) {
		hand = new ArrayList<BaseCard>();
		pIndex = i;
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

				if(!(hand.get(i).getRank() == Ranks.JOKER)) { //are not both jokers
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
	public int getIndex() {
		return pIndex;
	}
	private ArrayList<BaseCard> hand;
	public ArrayList<BaseCard> getHand() { return hand; };

	private int pIndex;
}