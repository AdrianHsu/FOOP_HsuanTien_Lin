import java.util.Scanner;
import java.util.ArrayList;

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
	public static ArrayList<Card> totalCards = new ArrayList<Card>();
	private	static String author;
}