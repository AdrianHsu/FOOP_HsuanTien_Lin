public class JokerCard extends BaseCard {

	public JokerCard(int _color) {
		this.color = _color;
	}


	public boolean isJoker(){ return true; }
	public int color;
	public int getJokerColor() { return color; }
}