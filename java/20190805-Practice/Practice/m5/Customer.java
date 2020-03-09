import java.util.ArrayList;

public class Customer
{
    private String name;
    private ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();

    public Customer(String name) {
        this.name = name;
    }

    public void showAllAccounts() {
        System.out.println("List of all bank accounts for " + name + ":");
        for (BankAccount account : accounts) {
            System.out.println(account);
        }
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }
}
