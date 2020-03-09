public class Exercise4 {
	public static void main(String[] args) {
		// INSERT YOUR CODE HERE
		double rad = Input.askDouble("What is the circle radius?");
		Circle c = new Circle(rad);
		if (c.calculateArea() >= 10) {
		    System.out.println("This circle is big.");
		} else {
		    System.out.println("This circle is small.");
		}
	}
}
