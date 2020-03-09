import java.util.Random;

public class Exercise5 {
	public static void main(String[] args) {
		// INSERT YOUR CODE HERE
		Random rand = new Random(7777);
		int r = rand.nextInt(100);
		System.out.println("Here is a random number: " + r);
	}
}
