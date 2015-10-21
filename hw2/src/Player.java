import java.util.ArrayList;

public class Player {

	public Player(String _name, int _dollars, int _bet) {

		name = _name;
		dollars = _dollars;
		bet = _bet;
	}
	public Player() {
		name = "";
		dollars = 0;
		bet = 0;
	}

	public String getName() { return name; }
	public void setName(String _name) { name = _name; }
	public int getDollars() {return dollars; }
	public void addDollars(int _dollars) { dollars += _dollars; }
	public int getBet() {return bet; }
	public void setBet(int _bet) { bet = _bet; }

	public static ArrayList<Card> hand = new ArrayList<Card>();
	private String name;
	private int dollars;
	private int bet;
}