public class Exercise6 {
	public static void main(String[] args) {
		// INSERT YOUR CODE HERE
		String name = Input.askString("Username:");
		String pwd = Input.askString("Password:");
		if (name.equals("joe") && pwd.equals("guess")){
		    System.out.println("Welcome, joe!");
		} else {
		    System.out.println("Incorrect username or password.");
		}
	}
}
