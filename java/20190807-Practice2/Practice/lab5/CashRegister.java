import java.text.*;

/**
 * The cash register stores cash.
 *
 * You can add cash to the cash register.
 */
public class CashRegister {
    private double cash;

    public CashRegister(double cash) {
        this.cash = cash;
    }

    // insert 1 method here
    public void cashAdd(double c){
        this.cash += c;
    }

    /**
     * Return a string in the form:
     *
     * Cash register: $[cash]
     *
     * e.g. "Cash register: $29.90"
     *
     * If there is no cash, instead return:
     *
     * "Cash register: empty"
     */
    @Override
    public String toString() {
        if (this.cash == 0.0){
            return "Cash register: empty";
        } else {
            return "Cash register: $" + String.format("%.2f", this.cash);
        }
    }
}
