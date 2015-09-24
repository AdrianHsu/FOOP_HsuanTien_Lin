import java.util.ArrayList;

public class PlayGame {

	public static void dealCards(ArrayList<Player> _players, DeckOfCards _deckOfCards) {
		for(int j = 0; j < 54 ; j++) {
			_players.get(j % 4).getHand().add( _deckOfCards.totalCards.get(j) );
		}
	}

	public static void drawing(Player p1, Player p2, Card card) {

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


	public static void main(String [] args) {

		DeckOfCards deckOfCards = new DeckOfCards();
		deckOfCards.shuffle();

		// init players
		ArrayList<Player> players = new ArrayList<Player>();
		for(int i = 0; i < 4; i++) {
			players.add( new Player() );
		}
		// deal cards
		dealCards(players, deckOfCards);
		System.out.println("Deal cards");

		// Cards Output
		cardsOutput(players);
	}
}