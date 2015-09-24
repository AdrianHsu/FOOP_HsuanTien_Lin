import java.util.ArrayList;
import java.util.Collections;


public class Player {

	public Player() {
		hand = new ArrayList<BaseCard>();
	}

	public void sortHand() {
	   Collections.sort(hand, BaseCard.CardComparator);
	}
	private ArrayList<BaseCard> hand;
	public ArrayList<BaseCard> getHand() { return hand; };
}