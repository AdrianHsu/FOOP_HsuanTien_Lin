import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Player {

	public Player() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter your name: ");
		setName( scanner.next() );
		System.out.println("Welcome, " +  getName() + ".");
		addDollars(1000);
		System.out.println("You have 1000 P-dollars now.");
		bet = -1; //default
	}

	public void enterBet(int _round) {
		System.out.print("Please enter your P-dollar bet for round " + _round + " (1-5 or 0 for quitting the game): ");
		Scanner scanner = new Scanner(System.in);
		int _bet = scanner.nextInt();
		// DEBUG:
		// int _bet = 5;

		if(_bet > 5 || _bet < 0) {
			// THROW EXCEPTION
		} 
		bet = _bet;
	}
	public void keepHand(Computer mComputer) {
		Scanner scanner = new Scanner(System.in);
		String keep = "";
		System.out.print("Which cards do you want to keep? ");
		keep = scanner.next();
		// DEBUG:
		// keep = "edcba";

		Boolean [] discard = {true, true, true, true, true};

		if (keep.contentEquals("none")) {
			// for(int i = 0; i < 5; i++) {
			// 	discard[ i ] = true;
			// }			
		} else {
			if(keep.length() == 5) {
				System.out.println("Okay. I will discard nothing");
				return;
			} else {
				for(int i = 0; i < keep.length(); i++) {
					discard[ (int)(keep.charAt(i)) - 97 ] = false;
				}
			}
		}

		System.out.print("Okay. I will discard ");
		for(int i = 0; i < 5; i++) {
			if(discard[i] == true) {
				System.out.print(" (" + (char)(i + 97) + ") " + hand.get( i ).cardToString());
			}
		}
		System.out.println(".");
		System.out.print("Your new cards are");
		for(int i = 0; i < 5; i++) {
			if(discard[ i ] == true) {
				hand.set(i, mComputer.totalCards.remove(mComputer.totalCards.size() - 1));
				mComputer.totalCards.trimToSize();
			}
			System.out.print(" " + hand.get( i ).cardToString());

		}
		System.out.println(".");
		// Collections.sort(hand, Card.CardComparator);
	}

	public String getName() { return name; }
	public void setName(String _name) { name = _name; }
	public int getDollars() {return dollars; }
	public void addDollars(int _dollars) { dollars += _dollars; }
	public int getBet() {return bet; }
	public void setBet(int _bet) { bet = _bet; }

	public static ArrayList<Card> hand = new ArrayList<Card>();
	private String name;
	private int dollars;
	private int bet;
}