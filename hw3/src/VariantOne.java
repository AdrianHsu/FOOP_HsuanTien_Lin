import java.util.ArrayList;
import java.lang.Math;

// A variant in East Asia is called baba-nuki (ババ抜き, "old maid") in Japan 
// and dodukjapki (도둑잡기, "catching the thief") in Korea. 
// It is played exactly as old maid, but instead of removing a queen or any other card, 
// a joker is added, and player who is left with it loses.

public class VariantOne extends OldMaid {

	public static void dealCards(ArrayList<Player> _players, DeckOfCards _deckOfCards) {
		for(int j = 0; j < 54 ; j++) {
			if( !(_deckOfCards.totalCards.get(j).getSuit() == Suits.BLACK) )
				_players.get(j % 4).getHand().add( _deckOfCards.totalCards.get(j) );
		}
	}

	public static void baba_nuki(ArrayList<Player> players, int i) {
		
		int to = i % NUM_OF_PLAYERS;
		int from = (i + 1) % NUM_OF_PLAYERS;
		int toIndex = players.get(to).getIndex();
		int fromIndex = players.get(from).getIndex();

		int cardIndex = (1 + (int)(Math.random() * 100)) % players.get(from).getHand().size();

		System.out.println("Player" + toIndex + " draws a card from Player" + fromIndex
		+ " " + players.get(from).getHand().get(cardIndex).cardToString());

		drawing(players.get(to), players.get(from), cardIndex);
		drawCardsOutput(players, to, from);

		if(players.get(to).getHand().size() == 0 && players.get(from).getHand().size() == 0) {
			if(toIndex > fromIndex) {
				int tmp = toIndex;
				toIndex = fromIndex;
				fromIndex = toIndex;
			}
			System.out.println("[CONGRATULATIONS!]Player" + toIndex + " and Player" + fromIndex + " win");
			NUM_OF_PLAYERS -= 2;
			//seems that removal will call trim automatically, hence from -> to
			players.remove(from);
			players.remove(to);
			players.trimToSize();
		} else {
			if(players.get(from).getHand().size() == 0) {
				System.out.println("[CONGRATULATIONS!]Player" + fromIndex + " wins");
				NUM_OF_PLAYERS--;
				players.remove(from);
				players.trimToSize();
			} else if(players.get(to).getHand().size() == 0) {
				System.out.println("[CONGRATULATIONS!]Player" + toIndex + " wins");
				NUM_OF_PLAYERS--;
				players.remove(to);
				players.trimToSize();
			}
		}
	}
	public static void playGame() {

		DeckOfCards deckOfCards = new DeckOfCards();
		deckOfCards.shuffle();

		// init players
		ArrayList<Player> players = new ArrayList<Player>();
		for(int i = 0; i < NUM_OF_PLAYERS; i++) {
			players.add( new Player(i) );
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
		System.out.println("*****************************************");
		System.out.println("OldMaid VariantOne [baba-nuki] Game start");
		System.out.println("Red Joker was included.");
		System.out.println("*****************************************");


		int i = 0;
		Boolean IS_OVER = false;

		while(!IS_OVER) {

			baba_nuki(players, i);
			if(NUM_OF_PLAYERS == 1) {
				IS_OVER = true;
			}
			i++;
		}
		System.out.println("OldMaid VariantOne [baba-nuki] Game Over");

	}
}