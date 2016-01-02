import foop.*;
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

		if(get_chips() <= 0)
			return 0;

		// NegativeException Will not happened

		// BrokeException Handling
		// double bet = get_chips() * 0.6 * Math.random();
		// Normal cases, with no BrokeException chips
		double bet = get_chips() * 0.3 * Math.random();

		return (int)bet;
	}
	public boolean buy_insurance(Card my_open,
                    Card dealer_open,
                    ArrayList<Hand> current_table){
		
		//dealer_open must be ACE
		System.out.print("Please enter YES or NO: ");
		// 0 to 1
		double buy = Math.random();

		if(buy >= 0.5) {
			System.out.println("YES");
			return true;
		}
		else{
			System.out.println("NO");
			return false;
		}
	}
	public boolean do_surrender(Card my_open,
                   Card dealer_open,
                   ArrayList<Hand> current_table){

		System.out.print("Please enter YES or NO: ");
		// 0 to 1
		double var = Math.random();

		if(var >= 0.85) {
			System.out.println("YES");
			return true;
		}
		else{
			System.out.println("NO");
			return false;
		}
	}

	public boolean do_split(ArrayList<Card> my_open,
               Card dealer_open,
               ArrayList<Hand> current_table){
		
		System.out.print("Please enter YES or NO: ");
		// 0 to 1
		double var = Math.random();
		ArrayList<Card> hand = my_open;
		int total = POOCasino.countHandSoftTotal(hand);


		if(var >= 0.5 || total == 20) { // K, K or J, J...etc
			System.out.println("YES");
			return true;
		}
		else{
			System.out.println("NO");
			return false;
		}
	}
	public boolean do_double(Hand my_open,
                Card dealer_open,
                ArrayList<Hand> current_table){
		
		System.out.print("Please enter YES or NO: ");
		// 0 to 1
		double var = Math.random();
		ArrayList<Card> hand = my_open.getCards();
		int total = POOCasino.countHandSoftTotal(hand);

		if(var >= 0.5 || (total < 15 && total > 11)) {
			System.out.println("YES");
			return true;
		}
		else{
			System.out.println("NO");
			return false;
		}
	}
	public boolean hit_me(Hand my_open,
             Card dealer_open,
             ArrayList<Hand> current_table){
		

		System.out.print("Please enter YES or NO: ");
		// 0 to 1
		double var = Math.random();
		ArrayList<Card> hand = my_open.getCards();
		int total = POOCasino.countHandSoftTotal(hand);

		if(var >= 0.5 || total < 11) {
			System.out.println("YES");
			return true;
		}
		else{
			System.out.println("NO");
			return false;
		}
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
  		// result += "|================PlayerB03901023 CURRENT STATUS================|\n";
  		// result += "|	@ PLAYER NAME: ADRIAN_HSU\n";
  		// result += "|	@ CURRENT NUMBER OF CHIPS: " + get_chips() + "\n";
		// result += "|==============================================================|\n";
  		result += get_chips();
  		return result;
  	}
}