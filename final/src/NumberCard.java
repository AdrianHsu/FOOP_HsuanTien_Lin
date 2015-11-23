public class NumberCard extends Card {
	

	public NumberCard(int _number, Colors _color) {
		super(_color);
		number = _number;
	}

	public int getNumber() {return number;}

	private int number;
}