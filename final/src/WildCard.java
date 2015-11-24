public class WildCard extends Card {
	
	public WildCard(WildType _wildType, Colors _color) {
			
		super(_color, 50);
		wildType = _wildType;
	}

	public WildType getWildType() {return wildType;}
	private final WildType wildType;
}