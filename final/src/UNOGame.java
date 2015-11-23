public class UNOGame {
	
	public static void main(String[] args) {

		NumberCard numberCard = new NumberCard(3, Colors.RED);
		System.out.println(numberCard.getNumber() + " " + numberCard.getColor());
	}
}