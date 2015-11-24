public abstract class Card {

	public Card(Colors _color, int _score){
		color = _color;
		score = _score;
	}

	public Colors getColor() {return color;}
	public void setColor(Colors _color){color = _color;}
	public int getScore() {return score;}
	private Colors color;
	protected int score;
}