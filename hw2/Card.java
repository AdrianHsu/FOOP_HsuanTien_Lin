import java.util.Comparator;

public class Card {
	
	// Constructor
	public Card(Ranks _rank, Suits _suit) {
		this.rank = _rank;
		this.suit = _suit;	
	}
	// Copy Constructor
	public Card(Card _card) {
		this.rank = _card.getRank();
		this.suit = _card.getSuit();
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

	public static final String[] sSuits = {"C", "D", "H", "S"};
	public static final String[] sRanks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

	protected final Suits suit;
	protected final Ranks rank;
}