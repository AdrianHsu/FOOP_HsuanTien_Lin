import foop.*;

import java.util.ArrayList;

public class Dealer {

	public Dealer() {}
	public static boolean faceupCardIsAce() {
		
		Card faceupCard = getHand().get(getHand().size() - 1);

		if(faceupCard.getValue() == 1)
			return true;
		return false;
	}

	public static ArrayList<Card> getHand() {
		return mHand;
	}

	public static void setBlackjack(boolean _blackjack) {
		stand = _blackjack;
	}
	public static boolean getBlackjack() {
		return stand;
	}	
	public static void setBusted(boolean _busted) {
		busted = _busted;
	}
	public static boolean getBusted() {
		return busted;
	}
	public static void setStand(boolean _stand) {
		stand = _stand;
	}
	public static boolean getStand() {
		return stand;
	}

	private static boolean blackjack = false;
	private static boolean stand = false;
	private static boolean busted = false;
	private static ArrayList<Card> mHand = new ArrayList<Card>();
}