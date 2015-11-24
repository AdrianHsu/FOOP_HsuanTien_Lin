public class WildCard extends Card {
	
	public WildCard(WildType _wildType, Colors _color) {
		
		super(_color);
		wildType = _wildType;
	}

	private WildType wildType;
}