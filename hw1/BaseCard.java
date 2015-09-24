public class BaseCard {
	
	public BaseCard(Ranks _rank, Suits _suit) {
		this.rank = _rank;
		this.suit = _suit;	
	}

	public String cardToString() {
		return sSuits[ suit.ordinal() ] + sRanks[ rank.ordinal() ] ;
	}

	public Suits getSuit() { return suit; }
	public Ranks getRank() { return rank; }

	public static final String[] sSuits = {"C", "D", "H", "S", "R", "B"};
	public static final String[] sRanks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "0"};

	protected final Suits suit;
	protected final Ranks rank;
}