public class CustomerDemoProgram {
    public static void main(String[] args) {
        Customer customer = new Customer("Tom Smith");
        customer.addAccount(new BankAccount("Tom Smith", "savings", 1500.0));
        customer.addAccount(new BankAccount("Tom Smith", "credit", -53.99));
        customer.addAccount(new BankAccount("Tom Smith", "loan", -79000.0));
        customer.showAllAccounts();
    }
}
