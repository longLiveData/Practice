public class Exercise9 {
	public static void main(String[] args) {
		// INSERT YOUR CODE HERE
		double x1 = Input.askDouble("Enter x coordinate for point 1:");
		double y1 = Input.askDouble("Enter y coordinate for point 1:");
		double x2 = Input.askDouble("Enter x coordinate for point 2:");
		double y2 = Input.askDouble("Enter y coordinate for point 2:");
		double dis = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
		System.out.println("The distance between the two points is " + dis);
	}
}
