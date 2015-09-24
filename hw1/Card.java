public class Card extends BaseCard {

	public Card(int _rank, int _suit) {
		this.rank = _rank;
		this.suit = _suit;
		
	}
	private final int suit;
	private final int rank;

	
	public boolean isJoker(){ return false; }
	public int getSuit() { return suit; }
	public int getRank() { return rank; }
}