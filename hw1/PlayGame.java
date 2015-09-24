import java.util.ArrayList;

public class PlayGame {

	public static void dealCards(ArrayList<Player> _players, DeckOfCards _deckOfCards) {
		for(int j = 0; j < 54 ; j++) {
			_players.get(j % 4).getHand().add( _deckOfCards.totalCards.get(j) );
		}
	}
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
		
		//to
		System.out.print("Player" + to + ": ");
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
		System.out.print("Player" + from + ": ");
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

		BaseCard drawCard = new BaseCard( pFrom.getHand().get(index) );
		pFrom.removeCard(index);
		pTo.addCard(drawCard);
		pFrom.sortHand();
		pTo.sortHand();
		pFrom.dropPairs();
		pTo.dropPairs();
	}

	public static void main(String [] args) {

		DeckOfCards deckOfCards = new DeckOfCards();
		deckOfCards.shuffle();

		// init players
		int NUM_OF_PLAYERS = 4;
		ArrayList<Player> players = new ArrayList<Player>();
		for(int i = 0; i < NUM_OF_PLAYERS; i++) {
			players.add( new Player() );
		}
		// deal cards
		System.out.println("Deal cards");
		dealCards(players, deckOfCards);

		// sort all players' cards
		sort(players);

		// Cards Output
		cardsOutput(players);
		
		// drop cards, first time for all players
		System.out.println("Drop cards");
		for(int i = 0; i < NUM_OF_PLAYERS; i++) {
			players.get(i).dropPairs();
		}

		// Cards Output
		cardsOutput(players);

		// Game start
		System.out.println("Game start");

		int i = 0;
		Boolean IS_OVER = false;
		while(!IS_OVER) {

			int to = i % 4;
			int from = (i + 1) % 4;
			int cardIndex = (int) Math.random() % players.get(from).getHand().size();

			System.out.println("Player" + to + " draws a card from Player" + from
			+ " " + players.get(from).getHand().get(cardIndex).cardToString());

			drawing(players.get(to), players.get(from), cardIndex);
			drawCardsOutput(players, to, from);

			if(players.get(to).getHand().size() == 0 && players.get(from).getHand().size() == 0) {
				if(to > from) {
					int tmp = to;
					to = from;
					from = to;
				}
				System.out.println("Player" + to + " and Player" + from + " win");
				IS_OVER = true;
			}
			if(players.get(from).getHand().size() == 0) {
				System.out.println("Player" + from + " wins");
				IS_OVER = true;
			} else if(players.get(to).getHand().size() == 0) {
				System.out.println("Player" + to + " wins");
				IS_OVER = true;
			}
			
			i++;
		}
		System.out.println("Basic game over");

	}
}