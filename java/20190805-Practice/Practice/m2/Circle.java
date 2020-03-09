/**
 * Each Circle object represents a perfectly round 2-dimensional shape.
 */
public class Circle {
	private double radius;

	/**
	 * Creates a new Circle with the given radius.
	 *
	 * @param radius the radius specifying half the width of the new circle
	 */
	public Circle(double radius) {
		this.radius = radius;
	}

	/**
	 * Prints the area of this circle to the terminal.
	 */
	public void showArea() {
		System.out.println("The area of the circle is " + calculateArea());
	}

	/**
	 * Calculates and returns the area of this circle.
	 *
	 * @return the circle's area
	 */
	public double calculateArea() {
		return Math.PI * radius * radius;
	}
}
