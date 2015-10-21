public class POOCasino {

	public static void main( String [] args ) {

		Computer mComputer = new Computer();
		mComputer.setAuthor("b03901023 Pin-Chun Hsu");
		String playerName = "";
		mComputer.init(playerName);
		Player mPlayer = new Player(playerName, 1000);
	}
}