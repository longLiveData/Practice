public class Exercise6 {
	public static void main(String[] args) {
		// INSERT YOUR CODE HERE
		BankAccount ba1 = new BankAccount();
		ba1.showBalance();
		if (ba1.getBalance() >= 20) {
		    System.out.println("Purchasing movie ticket.");
		    ba1.withdraw(20);
		    ba1.showBalance();
		} else {
		    System.out.println("You need more money to buy a movie ticket.");
		}
 	}
}
