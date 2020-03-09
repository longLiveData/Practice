public class Exercise3 {
	public static void main(String[] args) {
		// INSERT YOUR CODE HERE
		double rad = Input.askDouble("What is the circle radius?");
		Circle c = new Circle(rad);
		c.showArea();
	}
}
