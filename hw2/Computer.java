import java.util.Scanner;

public class Computer {
	

	public Computer(){}

	public static void setAuthor(String _author) {
		author = _author;
	}

	public static void init(String playerName) {

		System.out.println("POOCasino Jacks or better, written by " + author);
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter your name: ");
		playerName = scanner.next();
		System.out.println("Welcome, " +  playerName + ".");
		System.out.println("You have 1000 P-dollars now.");
	}

	private	static String author;
}