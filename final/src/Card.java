public abstract class Card {

	public Card(Colors _color){
		color = _color;
	}

	public Colors getColor() {return color;}
	private Colors color;
}