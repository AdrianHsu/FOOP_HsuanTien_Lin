public class JokerCard extends BaseCard {

	public JokerCard(int _color) {
		this.color = _color;
		this.isJoker = true;
	}

	public int color;
	public int getJokerColor() { return color; }
}