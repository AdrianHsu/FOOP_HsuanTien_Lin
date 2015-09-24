import java.util.ArrayList;

public class PlayGame {

	public static void dealCards(ArrayList<Player> _players, DeckOfCards _deckOfCards) {
		for(int j = 0; j < 54 ; j++) {
			_players.get(j % 4).getHand().add( _deckOfCards.totalCards.get(j) );
		}
	}

	public static void drawing(Player p1, Player p2, Card card) {

	}


	public static void main(String [] args) {

		DeckOfCards deckOfCards = new DeckOfCards();
		deckOfCards.shuffle();

		if(!deckOfCards.totalCards.get(0).isJoker()) {

			Card card;
			card = (Card)deckOfCards.totalCards.get(0);
			System.out.println( card.getRank() );
		}

		// // init players
		// ArrayList<Player> players = new ArrayList<Player>();
		// for(int i = 0; i < 4; i++) {
		// 	players.add( new Player() );
		// }
		// // deal cards
		// dealCards(players, deckOfCards);

	}
}