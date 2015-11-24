import java.util.ArrayList;

public class UNOGame {
	
	private static int PLAYER_NUM = 3;
	private static int HUMAN_PLAYER_NUM = 3;
	private static int AI_PLAYER_NUM = 0;

	public static void main(String[] args) {

		ArrayList<Player> players = new ArrayList<Player>();
		
		//suppose 3 players
		for( int i = 0; i < HUMAN_PLAYER_NUM; i++ ) {
			players.add(new HumanPlayer());
		}

		UNOSystem.dealCards(players);

	}
}