import java.util.Comparator;

public class BaseCard {
	
	public BaseCard(Ranks _rank, Suits _suit) {
		this.rank = _rank;
		this.suit = _suit;	
	}

	public static Comparator<BaseCard> CardComparator = new Comparator<BaseCard>() {

		public int compare(BaseCard b1, BaseCard b2) {
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