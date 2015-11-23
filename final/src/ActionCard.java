public class ActionCard extends Card {

	public ActionCard(Actions _action, Colors _color) {
		
		super(_color);
		action = _action;
	}

	private Actions action;
}