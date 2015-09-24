public class Card extends BaseCard {

	public Card(int _rank, int _suit) {
		this.rank = _rank;
		this.suit = _suit;
		this.isJoker = false;
		
	}
	private final int suit;
	private final int rank;


	public int getSuit() { return suit; }
	public int getRank() { return rank; }
}