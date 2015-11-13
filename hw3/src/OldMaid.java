import java.util.ArrayList;

public class OldMaid {

	public static int NUM_OF_PLAYERS = 4;	

	public static void dealCards(ArrayList<Player> _players, DeckOfCards _deckOfCards){};
	
	public static void sort(ArrayList<Player> _players) {
		for(int i = 0; i < _players.size(); i++) {
			_players.get(i).sortHand();
		}
	}
	public static void cardsOutput(ArrayList<Player> _players) {
		
		for(int i = 0; i < _players.size(); i++) {
			System.out.print("Player" + i + ": ");

			for(int j = 0; j < _players.get(i).getHand().size() - 1; j++) {
				System.out.print( _players.get(i).getHand().get(j).cardToString() + " ");
			}
			int  last = _players.get(i).getHand().size() - 1;
			System.out.println( _players.get(i).getHand().get(last).cardToString());
		}
	}
	public static void drawCardsOutput(ArrayList<Player> _players, int to, int from) {
		
		int toIndex = _players.get(to).getIndex();
		int fromIndex = _players.get(from).getIndex();
		//to
		System.out.print("Player" + toIndex + ": ");
		if(_players.get(to).getHand().size() != 0) {
				
			for(int j = 0; j < _players.get(to).getHand().size() - 1; j++) {
				System.out.print( _players.get(to).getHand().get(j).cardToString() + " ");
			}
			int  last = _players.get(to).getHand().size() - 1;
			System.out.println( _players.get(to).getHand().get(last).cardToString());
		} else {
			System.out.println("");
		}
		//from
		System.out.print("Player" + fromIndex + ": ");
		if(_players.get(from).getHand().size() != 0) {

			for(int j = 0; j < _players.get(from).getHand().size() - 1; j++) {
				System.out.print( _players.get(from).getHand().get(j).cardToString() + " ");
			}
			int last = _players.get(from).getHand().size() - 1;
			System.out.println( _players.get(from).getHand().get(last).cardToString());
		} else {
			System.out.println("");
		}
	}
	public static void drawing(Player pTo, Player pFrom, int index) {

		Card drawCard = new Card( pFrom.getHand().get(index) );
		pFrom.removeCard(index);
		pTo.addCard(drawCard);
		pFrom.sortHand();
		pTo.sortHand();
		pFrom.dropPairs();
		pTo.dropPairs();
	}

}