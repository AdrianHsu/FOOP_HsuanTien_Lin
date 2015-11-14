import java.util.Scanner;

public class Game {
	public static void main(String [] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter your variant number (1 or 2): ");
		int var =  scanner.nextInt();

		if(var == 1) {

			VariantOne mVariantOne = new VariantOne();

			System.out.println("|==============================================================|");
			System.out.println("|   @ OldMaid VariantOne Game - [baba-nuki]                    |");
			System.out.println("|   @ Course: [CSIE1214] Fundamental OOP hw3                   |");
			System.out.println("|   @ https://www.csie.ntu.edu.tw/~htlin/course/foop15fall/    |");
			System.out.println("|   @ Instructor: Hsuan-Tien Lin                               |");
			System.out.println("|   @ Student ID / Name: B03901023 / Pin-Chun Hsu              |");
			System.out.println("|==============================================================|");

			mVariantOne.playGame();
		}
		else if (var == 2) {
			VariantTwo mVaraintTwo = new VariantTwo();

			System.out.println("|==============================================================|");
			System.out.println("|   @ OldMaid VariantTwo Game - [Jackass]                      |");
			System.out.println("|   @ Course: [CSIE1214] Fundamental OOP hw3                   |");
			System.out.println("|   @ https://www.csie.ntu.edu.tw/~htlin/course/foop15fall/    |");
			System.out.println("|   @ Instructor: Hsuan-Tien Lin                               |");
			System.out.println("|   @ Student ID / Name: B03901023 / Pin-Chun Hsu              |");
			System.out.println("|==============================================================|");

			mVaraintTwo.playGame();
		}
		else {
			System.out.println("INVALID INPUT, THE GAME ENDS.");
		}
	}
}