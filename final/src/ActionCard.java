public class ActionCard extends Card {

	public ActionCard(Actions _action, Colors _color) {		
		super(_color, 20);
		action = _action;
	}

	public Actions getAction() {return action;}
	private final Actions action;
}