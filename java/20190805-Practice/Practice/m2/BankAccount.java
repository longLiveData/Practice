import java.text.DecimalFormat;

/**
 * Each bank account has an account name and a balance, and provides
 * methods to access and manipulate the balance of that account.
 */
public class BankAccount {
	private static DecimalFormat MONEY_FORMAT = new DecimalFormat("$###,###.00");
	private String accountName;
	private double balance;

	/**
	 * Creates a new bank account, asking the user to enter the
	 * account name and initial balance.
	 */
	public BankAccount() {
		System.out.println("Creating a new bank account");
		accountName = Input.askString("Enter account name:");
		balance = Input.askDouble("Enter initial balance: $");
	}

	/**
	 * Shows the balance of this account.
	 */
	public void showBalance() {
		System.out.println(accountName + "'s account has " + MONEY_FORMAT.format(balance));
	}

	/**
	 * Deposits the specified amount of money into this account.
	 */
	public void deposit(double amount) {
		balance += amount;
	}

	/**
	 * Withdraws the specified amount of money into this account.
	 */
	public void withdraw(double amount) {
		balance -= amount;
	}

	/**
	 * Transfers the specified amount of money from this account into
	 * the target account.
	 */
	public void transfer(double amount, BankAccount targetAccount) {
		System.out.println("Transferring " + MONEY_FORMAT.format(amount) + " from " + accountName + " to " + targetAccount.accountName);
		withdraw(amount);
		targetAccount.deposit(amount);
	}

	/**
	 * Gets the balance of this account.
	 *
	 * @return the balance of this account
	 */
	public double getBalance() {
		return balance;
	}
}
