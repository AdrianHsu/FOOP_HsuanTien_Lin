import foop.*;

import java.util.ArrayList;

public class Dealer {

	public Dealer(){};

	public static boolean faceupCardIsAce() {
		
		Card faceupCard = getHand().get(getHand().size() - 1);

		if(faceupCard.getValue() == 1)
			return true;
		return false;
	}

	public static ArrayList<Card> getHand() {
		return mHand;
	}

	private static ArrayList<Card> mHand = new ArrayList<Card>();
}