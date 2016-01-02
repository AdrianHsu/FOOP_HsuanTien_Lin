import foop.*;
import java.util.ArrayList;

public class POOCasino {
	
	private static int N_ROUND;
	private static int N_CHIP;
	private static final int MAX_N_PLAYERS = 4;
	private static int N_PLAYERS;



	public static void main(String [] args) {

		//RUN COMMAND:
		//java POOCasino nRound nChip Player1 Player2 Player3 Player4

		//BOUND CHECKING
		if(args.length <= 2) 
			throw new IndexOutOfBoundsException("0 PLAYER ATTENDED");
		else if(args.length > 2 + MAX_N_PLAYERS)
			throw new IndexOutOfBoundsException("MORE THAN 4 PLAYERS ATTENDED");

		// INIT
		N_ROUND = Integer.valueOf(args[0]);
		N_CHIP = Integer.valueOf(args[1]);
		ArrayList<Player> players = new ArrayList<>();

		for(int i = 2; i < args.length; i++) {

			players.add(new PlayerB03901023(args[i], N_CHIP));
			PlayerB03901023 me = (PlayerB03901023) players.get(0);
			System.out.println(me.getName());
			N_PLAYERS++;
		}

	}
}