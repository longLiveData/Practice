public class BankAccount
{
    private String accountName;
    private String accountType;
    private double balance;

    public BankAccount(String accountName, String accountType, double balance) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String toString() {
        return accountName + "'s " + accountType + " account has $" + balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}
