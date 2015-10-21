public class POOCasino {

	public static void main( String [] args ) {

		final String author = "b03901023 Pin-Chun Hsu";
		Computer mComputer = new Computer(author);
		Shuffler mShuffler = new Shuffler(52, mComputer.totalCards);

		Player mPlayer = new Player();
		mComputer.init(mPlayer);

		int round = 1;
		while( true ) {

			int bet = mComputer.enterBet(round);
			if(bet == 0) {
				mComputer.quitGame(mPlayer.getName(), mPlayer.getDollars(), round);
				break;
			}

			round++;
		}
	}
}