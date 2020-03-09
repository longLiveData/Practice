import java.util.*;

public class Store {
    
    public static void main(String[] args){
        Store s = new Store();
        String c = "a";
        while (! c.equals("x")) {
            System.out.print("Choice (s/r/v/c/p/x): ");
            c = In.nextLine();
            if (c.equals("v")) {
                s.viewStock();
            } else if (c.equals("s")){
                s.sell();
            } else if (c.equals("r")){
                s.restock();
            } else if (c.equals("c")){
                s.viewCash();
            } else if (c.equals("p")){
                s.pruneProducts();
            } else if (c.equals("?") || c.equals("help me!")){
                s.help();
            }
        }
        
    }
    
    private CashRegister cashRegister;
    private LinkedList<Product> products = new LinkedList<Product>();

    public Store() {
        // Insert 6 lines of code to initialise the fields.
        this.cashRegister = new CashRegister();
        this.products.add(new Product("Whiteboard Marker", 85, 1.50));
        this.products.add(new Product("Whiteboard Eraser", 45, 5.00));
        this.products.add(new Product("Black Pen", 100, 1.50));
        this.products.add(new Product("Red Pen", 100, 1.50));
        this.products.add(new Product("Blue Pen", 100, 1.50));
    }

    private char readChoice() {
        return In.nextChar();
    }

    private void sell() {
        String name = readName();
        ArrayList<Product> temp = new ArrayList();
        for(Product p: products){
            String na = name.toUpperCase();
            String na1 = p.getName().toUpperCase();
            if (na1.contains(na)){
                temp.add(p);
            }
        }
        if (temp.size() == 0) {
            System.out.println("No such product");
        } else if (temp.size() == 1) {
            for(Product p1: temp) {
                System.out.println("Selling " + p1.getName());
                int num = readNumber();
                if(p1.has(num)){
                    this.cashRegister.add(p1.sell(num));
                } else {
                    System.out.println("Not enough stock");
                }
            }
        } else {
            System.out.println("Multiple products match:");
            for(Product p1: temp) {
                System.out.println(p1.toString());
            }
        }
        
    }

    private void restock() {
        String name = this.readName();
        boolean find = false;
        for(Product p: products){
            String na = name.toUpperCase();
            String na1 = p.getName().toUpperCase();
            if (na1.contains(na)) {
                find = true;
                System.out.println("Restocking " + p.getName());
                int num = this.readNumber();
                p.restock(num);
            }
        }
        if (!find){
            System.out.println("Adding new product");
            int num = readNumber();
            double price = readPrice();
            this.products.add(new Product(name, num, price));
        }
    }

    private void viewStock() {
        for(Product p: products){
            System.out.println(p.toString());
        }
    }

    private void viewCash() {
        System.out.println(this.cashRegister.toString());
    }

    private void pruneProducts() {
        for (Iterator<Product> pp = this.products.iterator(); pp.hasNext();){
            Product p = pp.next();
            if (p.isEmpty()){
                pp.remove();
            }
        }
    }

    private String readName() {
        System.out.print("Name: ");
        return In.nextLine();
    }

    private double readPrice() {
        System.out.print("Price: $");
        return In.nextDouble();
    }

    private int readNumber() {
        System.out.print("Number: ");
        return In.nextInt();
    }

    private void help() {
        System.out.println("Menu options");
        System.out.println("s = sell");
        System.out.println("r = restock");
        System.out.println("v = view stock");
        System.out.println("c = view cash");
        System.out.println("p = prune products");
        System.out.println("x = exit");
    }
}
