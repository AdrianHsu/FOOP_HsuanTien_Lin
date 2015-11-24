public abstract class Card {

	public Card(Colors _color, int _score){
		color = _color;
		score = _score;
	}

	public Colors getColor() {return color;}
	public int getScore() {return score;}
	private final Colors color;
	protected final int score;
}