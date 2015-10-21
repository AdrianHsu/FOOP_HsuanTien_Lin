import java.util.Scanner;

public class POOCasino {

	public static void main( String [] args ) {
		System.out.println("POOCasino");
		scan();
	}
	public static void scan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input your name: ");
        System.out.printf("\nHello! %d!\n", scanner.nextInt());
	}
}