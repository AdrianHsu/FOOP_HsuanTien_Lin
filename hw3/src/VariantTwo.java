import java.util.ArrayList;
import java.lang.Math;

// A variant is called jackass, played with jacks instead of queens 
// as the odd amount of cards. It is known in Dutch as 
// zwartepieten ("playing Black Pete") or pijkezotjagen ("Chasing the jack of spades"), 
// in Polish as Piotruś ("Peter"), in Icelandic as Svarti Pétur ("black Peter") 
// and in Swedish as Svarte Petter ("Black Peter").

public class VariantTwo extends OldMaid {

	public static void dealCards(ArrayList<Player> _players, DeckOfCards _deckOfCards) {
		for(int j = 0; j < 54 ; j++) {
			if( !(_deckOfCards.totalCards.get(j).getSuit() == Suits.BLACK || 
					  _deckOfCards.totalCards.get(j).getSuit() == Suits.RED ||
					 (_deckOfCards.totalCards.get(j).getSuit() == Suits.SPADES && 
					 	_deckOfCards.totalCards.get(j).getRank() == Ranks.JACK)) )
				_players.get(j % 4).getHand().add( _deckOfCards.totalCards.get(j) );
		}
	}

	public static void game(ArrayList<Player> players, int i) {
		
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
	public static void main(String [] args) {

		DeckOfCards deckOfCards = new DeckOfCards();
		deckOfCards.shuffle();

		System.out.println("|==============================================================|");
		System.out.println("|   @ OldMaid VariantTwo Game - [Jackass]                      |");
		System.out.println("|   @ Course: [CSIE1214] Fundamental OOP hw3                   |");
		System.out.println("|   @ https://www.csie.ntu.edu.tw/~htlin/course/foop15fall/    |");
		System.out.println("|   @ Instructor: Hsuan-Tien Lin                               |");
		System.out.println("|   @ Student ID / Name: B03901023 / Pin-Chun Hsu              |");
		System.out.println("|==============================================================|");

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
		System.out.println("OldMaid VariantTwo [Jackass] Game start");
		System.out.println("Spades Jack (SJ) was dropped.");
		System.out.println("*****************************************");

		int i = 0;
		Boolean IS_OVER = false;

		while(!IS_OVER) {

			game(players, i);
			if(NUM_OF_PLAYERS == 1) {
				IS_OVER = true;
			}
			i++;
		}
		System.out.println("OldMaid VariantTwo [Jackass] Game Over");

	}
}