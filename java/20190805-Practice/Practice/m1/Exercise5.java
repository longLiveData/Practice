public class Exercise5 {
	public static void main(String[] args) {
		// INSERT YOUR CODE HERE
		String name = Input.askString("Are you a student?");
		if (name.equals("yes")) {
		    System.out.println("You are eligible for a discount.");
		} else {
		    System.out.println("You are not eligible for a discount.");
		}
	}
}
