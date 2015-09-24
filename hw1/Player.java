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
		for(int i = 1; i < hand.size(); i++) {

			if(hand.get(i).getRank() == hand.get(i - 1).getRank()) {

				hand.remove(i - 1);
				i--;
				hand.trimToSize();
			}
		}
	}
	private ArrayList<BaseCard> hand;
	public ArrayList<BaseCard> getHand() { return hand; };
}