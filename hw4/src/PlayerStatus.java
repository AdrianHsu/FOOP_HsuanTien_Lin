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
		split = false;
		doubledown = false;
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
	public boolean getInsurence() {
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
	public String getName(){
		return name;
	}
	public ArrayList<Card> getHand() {
		if(split == false)
			return mHand;
		else 
			return null;
	}

	private boolean doubledown;
	private boolean split;
	private boolean stand;
	private boolean busted;
	private boolean surrender;
	private boolean insurance;
	private int bet;
	private String name;
	private ArrayList<Card> mHand = new ArrayList<>();
	private ArrayList<Card> firstHand = new ArrayList<>();
	private ArrayList<Card> secondHand = new ArrayList<>();

}