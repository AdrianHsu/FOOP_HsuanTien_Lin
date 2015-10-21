import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Computer {
	
	public Computer(String _author) {
		author = _author;
	}

	public void init(Player _player) {

		System.out.println("POOCasino Jacks or better, written by " + author);
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter your name: ");
		_player.setName( scanner.next() );
		System.out.println("Welcome, " +  _player.getName() + ".");
		_player.setDollars(1000);
		System.out.println("You have 1000 P-dollars now.");
	}
	public int enterBet(int _round) {
		System.out.print("Please enter your P-dollar bet for round " + _round + " (1-5 or 0 for quitting the game): ");
		Scanner scanner = new Scanner(System.in);
		int _bet = scanner.nextInt();

		if(_bet > 5 || _bet < 0) {
			// THROW EXCEPTION
		} 
		return _bet;
	}
	public void quitGame(String _playerName, int _dollars, int _round) {
		System.out.println("Good bye, " + _playerName + ". You played for " + 
				_round + " round and have " + _dollars + " P-dollars now.");
	}
	public void initHand(ArrayList<Card> _hand) {
		
		if(_hand.isEmpty()) {
		
			System.out.print("Your cards are");
			for(int i = 0; i < 5; i++) {
				_hand.add(totalCards.remove(totalCards.size() - 1));
				totalCards.trimToSize();
			}

			// Collections.sort(_hand, Card.CardComparator);

			for(int i = 0; i < 5; i++) {
				System.out.print(" (" + (char)(i + 97) + ") " + _hand.get(i).cardToString());
			}
			System.out.println("");
		}
	}
	public void keepHand(ArrayList<Card> _hand) {
		Scanner scanner = new Scanner(System.in);
		String keep = "";
		System.out.print("Which cards do you want to keep? ");
		keep = scanner.next();
		Boolean [] discard = {false, false, false, false, false};
		for(int i = 0; i < keep.length(); i++) {
			discard[ (int)(keep.charAt(i)) - 97 ] = true;
		}

		System.out.print("Okay. I will discard ");
		for(int i = 0; i < 5; i++) {
			if(discard[i] == true) {
				System.out.print(" (" + (char)(i + 97) + ") " + _hand.get( i ).cardToString());
			}
		}
		System.out.println(".");
		System.out.print("Your new cards are");
		for(int i = 0; i < 5; i++) {
			if(discard[ i ] == true) {
				_hand.set(i, totalCards.remove(totalCards.size() - 1));
				totalCards.trimToSize();
			}
			System.out.print(" " + _hand.get( i ).cardToString());

		}
		System.out.println(".");
		// Collections.sort(_hand, Card.CardComparator);
	}
	public static ArrayList<Card> totalCards = new ArrayList<Card>();
	private	static String author;
}