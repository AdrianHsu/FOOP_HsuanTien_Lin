public class PlayerStatus {

	public PlayerStatus(String _name){
		name = _name;
	}

	public void setBet(int _bet){
		bet = _bet;
	}
	public int getBet() {
		return bet;
	}
	private int bet;
	public String name; 
}