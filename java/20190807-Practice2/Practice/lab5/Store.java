/**
 * The store has and sells one product: Sticky tape.
 *
 * You can sell and restock a product, view stock and view cash.
 */
public class Store {
    // insert main method here
    // insert 2 fields here
    // insert 1 constructor here
    public static void main(String[] args){
        Store s = new Store();
        s.use();
    }
    
    private Product product;
    private CashRegister cashRegister;
    
    Store(){
        this.product = new Product("Sticky tape", 200, 2.99);
        this.cashRegister = new CashRegister(0.0);
    }
    
    public void use() {
        char opt = 'a';
        while (opt != 'x'){    
            System.out.print("Choice (s/r/v/c/x): ");
            opt = In.nextChar();
            if (opt == '?'){
                this.help();
            } else if (opt == 's'){
                System.out.print("Number: ");
                int num = In.nextInt();
                this.sell(num);
            }else if (opt == 'r'){
                System.out.print("Number: ");
                int num = In.nextInt();
                this.restock(num);
            }else if (opt == 'v'){
                this.viewStock();
            }else if (opt == 'c'){
                this.viewCash();
            }
        }
    }

    private void sell(int num) {
        if (num > this.product.getStock()){
            System.out.println("Not enough stock");
        } else {
            double amount = this.product.sell(num);
            this.cashRegister.cashAdd(amount);
        }
    }

    private void restock(int num) {
        this.product.restock(num);
    }

    private void viewStock() {
        System.out.println(this.product.toString());
    }

    private void viewCash() {
        System.out.println(this.cashRegister.toString());
    }

    private void help() {
        System.out.println("Menu options");
        System.out.println("s = sell");
        System.out.println("r = restock");
        System.out.println("v = view stock");
        System.out.println("c = view cash");
        System.out.println("x = exit");
    }

    
}
