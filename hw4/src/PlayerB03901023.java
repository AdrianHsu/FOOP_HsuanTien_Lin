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
		int total = countHandSoftTotal(hand);


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
		int total = countHandSoftTotal(hand);

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
		int total = countHandSoftTotal(hand);

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

	public int countHandSoftTotal(ArrayList<Card> hand) {

		int total = 0;
		int numOfAce = 0;
		int size = hand.size();

		for(int i = 0 ; i < size; i++) {
			if(hand.get(i).getValue() == 1) {
				// move ACE to front
				numOfAce++;
				Card ace = hand.get(i);
				hand.remove(i);
				hand.trimToSize();
				hand.add(0, ace);
			}
		}

		if(numOfAce == 0) {
			
			for(int i = 0; i < hand.size(); i++) {
				if(hand.get(i).getValue() > 10) // 11, 12, 13 for J,Q,K
					total += 10;
				else
					total += hand.get(i).getValue();
			}
		} else if (numOfAce == 1) {
			
			for(int i = 1; i < hand.size(); i++) { // start from i = 1 since sorted
				if(hand.get(i).getValue() > 10) // 11, 12, 13 for J,Q,K
					total += 10;
				else
					total += hand.get(i).getValue();
			}
			if(total + 11 > 21)
				total += 1;
			else
				total += 11;
		} else if (numOfAce > 1) { //numOfAce == 2, 3, 4
			
			for(int i = numOfAce; i < hand.size(); i++) { // start from i = 1 since sorted
				if(hand.get(i).getValue() > 10) // 11, 12, 13 for J,Q,K
					total += 10;
				else
					total += hand.get(i).getValue();
			}
			total += numOfAce - 1;
			if(total + 11 > 21)
				total += 1;
			else
				total += 11;
		}
		return total;
	}


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