public class Player {

	public Player(String _name, int _dollars) {

		name = _name;
		dollars = _dollars;
	}
	public Player() {
		name = "";
		dollars = 0;
	}

	public String getName() { return name; }
	public void setName(String _name) { name = _name; }
	public int getDollars() {return dollars; }
	public void setDollars(int _dollars) { dollars = _dollars; }

	private String name;
	private int dollars;
	
}