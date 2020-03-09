import java.text.*;

/**
 * Each product has a name, stock and price.
 *
 * You can sell and restock a product.
 */
public class Product {
    // insert 3 fields here
    // insert 1 constructor here
    private String name;
    private int stock;
    private double price;
    
    Product(String name, int stock, double price){
        this.name = name;
        this.stock = stock;
        this.price = price;
    }
    
    public int getStock(){
        return this.stock;
    }
    

    /**
     * This pseudo-procedure sells "n" amount of stock.
     * The stock should decrease by "n".
     *
     * This method also returns the money earned from the sale.
     *
     * This is a rare occasion where a procedure returns something.
     */
    public double sell(int n) {
        // insert your code here to update the stock
        this.stock -= n;
        return n * this.price; // a dummy return value - which you will change
    }

    /**
     * Add "n" amount to this product's stock.
     */
    public void restock(int n) {
        this.stock += n;
    }

    /**
     * Return a string in the form:
     *
     * [stock] [name] at $[price]
     *
     * e.g. "200 Sticky tape at $2.99"
     *
     * DO NOT hard code the data in this string
     * or you will be penalised for a spoof.
     */
    @Override
    public String toString() {
        return this.stock + " " + this.name + " at $" + String.format("%.2f", this.price);
    }
}
