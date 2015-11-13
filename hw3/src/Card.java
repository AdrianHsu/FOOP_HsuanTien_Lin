import java.util.Comparator;

public class Card {

	// Constructor
	public Card(Ranks _rank, Suits _suit) {
		this.rank = _rank;
		this.suit = _suit;	
	}
	// Copy Constructor
	public Card(Card _Card) {
		this.rank = _Card.getRank();
		this.suit = _Card.getSuit();
	}

	public static Comparator<Card> CardComparator = new Comparator<Card>() {

		public int compare(Card b1, Card b2) {
			if( b1.rank != b2.rank )
				return b1.rank.ordinal() - b2.rank.ordinal();
			else
				return b1.suit.ordinal() - b2.suit.ordinal();
		}
	};

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