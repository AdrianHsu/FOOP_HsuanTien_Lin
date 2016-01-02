import foop.*;
import java.util.ArrayList;


public class PlayerStatus {

	public PlayerStatus(String _name){
		name = _name;
	}

	public void setBet(int _bet){
		bet = _bet;
	}
	public void setInsurance(boolean buy) {
		insurance = buy;
	}
	public int getBet() {
		return bet;
	}
	public String getName(){
		return name;
	}

	public ArrayList<Card> getHand() {
		return mHand;
	}
	private boolean insurance;
	private int bet;
	private String name;
	private ArrayList<Card> mHand = new ArrayList<>();
}