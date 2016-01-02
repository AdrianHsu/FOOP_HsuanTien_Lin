import foop.*;
import java.util.ArrayList;


public class PlayerStatus {

	public PlayerStatus(String _name){
		name = _name;
		bet = 0;
		insurance = false;
		surrender = false;
		busted = false;
		stand = false;
		blackjack = false;
		split = false;
		doubledown = false;
	}

	public void reset() {

		// bet = 0; // ALREADY RESET(OVERRIDE)
		insurance = false;
		surrender = false;
		busted = false;
		stand = false;
		blackjack = false;
		split = false;
		doubledown = false;
		mHand.clear();
		firstHand.clear();
		secondHand.clear();
		for(int i = 0; i < 2; i++) {
			blackjackSplit[i] = false;
			standSplit[i] = false;
			bustedSplit[i] = false;
		}	
	}
	public void setBet(int _bet){
		bet = _bet;
	}
	public int getBet() {
		return bet;
	}
	public void setInsurance(boolean buy) {
		insurance = buy;
	}
	public boolean getInsurance() {
		return insurance;
	}
	public void setSurrender(boolean _surrender) {
		surrender = _surrender;
	}
	public boolean getSurrender() {
		return surrender;
	}
	public void setBusted(boolean _busted) {
		busted = _busted;
	}
	public boolean getBusted() {
		return busted;
	}
	public void setStand(boolean _stand) {
		stand = _stand;
	}
	public boolean getStand() {
		return stand;
	}
	public void setSplit(boolean _split) {
		split = _split;

		if(split) {
			firstHand.add(mHand.get(0));
			secondHand.add(mHand.get(1));
			mHand.clear();
		} else {
			for(int i = 0; i < 2; i ++) {
				blackjackSplit[i] = false;
				standSplit[i] = false;
				bustedSplit[i] = false;
			}
		}
	}
	public boolean getSplit() {
		return split;
	}
	public void setDoubledown(boolean _doubledown) {
		doubledown = _doubledown;
	}
	public boolean getDoubledown() {
		return doubledown;
	}
	public void setBlackjack(boolean _blackjack) {
		stand = _blackjack;
	}
	public boolean getBlackjack() {
		return stand;
	}	
	public String getName(){
		return name;
	}
	public ArrayList<Card> getHand() {
		if(split == false)
			return mHand;
		else 
			return null;
	}
	public ArrayList<Card> getSplitHand(int i) {
		if(split == false)
			return null; //exception
		if(i == 0)
			return firstHand;
		else
			return secondHand;
	}
  	public String toString(ArrayList<Card> hand) {

  		String result = "";
  		result += "|========================CARDS IN HAND=========================|\n";
  		for(int i = 0; i < hand.size(); i++) {
  			Card card = hand.get(i);
			System.out.println("Card" + i + " Suit: " + card.getSuit() + "; Value: " + card.getValue());
  		}
		result += "|==============================================================|\n";

  		return result;
  	}
	private boolean blackjack;
	private boolean doubledown;
	private boolean split;
	private boolean stand;
	private boolean busted;
	private boolean surrender;
	private boolean insurance;
	private int bet;
	private String name;
	private ArrayList<Card> mHand = new ArrayList<>();



	public boolean[] blackjackSplit = {false, false};
	public boolean[] standSplit = {false, false};
	public boolean[] bustedSplit = {false, false};

	private ArrayList<Card> firstHand = new ArrayList<>();
	private ArrayList<Card> secondHand = new ArrayList<>();

}