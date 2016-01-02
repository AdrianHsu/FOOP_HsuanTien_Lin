import foop.*;

import java.util.ArrayList;

public class Dealer {

	public Dealer(){};

	public static boolean faceupCardIsAce() {
		
		Card faceupCard = hand.getCards().get(hand.getCards().size() - 1);
		if(faceupCard.getValue() == 1)
			return true;
		return false;
	}

	public static Hand getHand(){return hand;}
	private static Hand hand = new Hand(new ArrayList<Card>());
}