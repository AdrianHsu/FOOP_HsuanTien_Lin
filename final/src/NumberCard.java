public class NumberCard extends Card {

	public NumberCard(int _number, Colors _color) {
		super(_color, _number); // number = score
		number = _number;
	}

	public int getNumber() {return number;}

	private final int number;
}