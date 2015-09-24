import java.util.ArrayList;

public class Player {

	public Player() {
		hand = new ArrayList<BaseCard>();
	}

	private ArrayList<BaseCard> hand;
	public ArrayList<BaseCard> getHand() { return hand; };
}