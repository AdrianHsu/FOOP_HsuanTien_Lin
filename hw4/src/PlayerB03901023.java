import foop.*;

import java.util.ArrayList;

public class PlayerB03901023 extends Player {

	public PlayerB03901023(int chips){
		super(chips);
	}
	
	// protected final double get_chips(){
		
	// 	return -1;
	// }
	public int make_bet(java.util.ArrayList<Hand> last_table,
           int total_player,
           int my_position){

		return 1;
	}
	public boolean buy_insurance(Card my_open,
                    Card dealer_open,
                    java.util.ArrayList<Hand> current_table){
		
		return true;
	}
	public boolean do_surrender(Card my_open,
                   Card dealer_open,
                   java.util.ArrayList<Hand> current_table){
		
		return true;
	}

	public boolean do_split(java.util.ArrayList<Card> my_open,
               Card dealer_open,
               java.util.ArrayList<Hand> current_table){
		
		return true;
	}
	public boolean do_double(Hand my_open,
                Card dealer_open,
                java.util.ArrayList<Hand> current_table){
		
		return true;
	}
	public boolean hit_me(Hand my_open,
             Card dealer_open,
             java.util.ArrayList<Hand> current_table){

		return true;
	}
	// public final void decrease_chips(double diff)
 //                          throws Player.NegativeException,
 //                                 Player.BrokeException {

 //    }
 //    public final void increase_chips(double diff)
 //                          throws Player.NegativeException {

 //  	}
  	public String toString() {

  		return "";
  	}
}