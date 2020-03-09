public class Exercise1 {
	public static void main(String[] args) {
		// INSERT YOUR CODE HERE
		int number = Input.askInt("Pick a number (1-13): ");
		String suit = Input.askString("Pick a suit:  ");
		if (number > 13) {
			System.out.println("The number you picked is too high");
		} else {
			System.out.println("You picked the " + number + " of " + suit);
		}
	}
}
