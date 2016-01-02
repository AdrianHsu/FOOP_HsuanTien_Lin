import foop.*;
import java.util.Scanner;
import java.util.ArrayList;

public class PlayerB03901023 extends Player {

	public PlayerB03901023(int _chips){
		super(_chips);
	}
	
	// protected final double get_chips(){
		
	// 	return -1;
	// }
	public int make_bet(ArrayList<Hand> last_table,
           int total_player,
           int my_position){

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your bet: ");
		int bet = scanner.nextInt();

		while( (bet * 2.5)  >= get_chips()) {
			
			scanner = new Scanner(System.in);
			System.out.println("Please enter your bet again");
			bet = scanner.nextInt();
		}

		return bet;
	}
	public boolean buy_insurance(Card my_open,
                    Card dealer_open,
                    ArrayList<Hand> current_table){
		
		//dealer_open must be ACE
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter 1 for YES or 0 for NO: ");
		int buy = scanner.nextInt();

		if(buy == 1)
			return true;
		else
			return false;
	}
	public boolean do_surrender(Card my_open,
                   Card dealer_open,
                   ArrayList<Hand> current_table){

		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter 1 for YES or 0 for NO: ");
		int sur = scanner.nextInt();

		if(sur == 1)
			return true;
		else
			return false;
	}

	public boolean do_split(ArrayList<Card> my_open,
               Card dealer_open,
               ArrayList<Hand> current_table){
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter 1 for YES or 0 for NO: ");
		int split = scanner.nextInt();

		if(split == 1)
			return true;
		else
			return false;
	}
	public boolean do_double(Hand my_open,
                Card dealer_open,
                ArrayList<Hand> current_table){
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter 1 for YES or 0 for NO: ");
		int dou = scanner.nextInt();

		if(dou == 1)
			return true;
		else
			return false;
	}
	public boolean hit_me(Hand my_open,
             Card dealer_open,
             ArrayList<Hand> current_table){
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter 1 for YES or 0 for NO: ");
		int hit = scanner.nextInt();

		if(hit == 1)
			return true;
		else
			return false;
	}
 //    public final void decrease_chips(double diff)
 //                          throws Player.NegativeException,
 //                                 Player.BrokeException {

 //    }
 //    public final void increase_chips(double diff)
 //                          throws Player.NegativeException {

 //  	}

  	public String toString() {

  		String result = "";
  		result += "|================PlayerB03901023 CURRENT STATUS================|\n";
  		result += "|	@ PLAYER NAME: Adrian Hsu\n";
  		result += "|	@ CURRENT NUMBER OF CHIPS: " + get_chips() + "\n";
		result += "|==============================================================|\n";

  		return result;
  	}
}