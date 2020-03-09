package src;

import bagel.Keys;

public class Controller {

    public Keys upKey, downKey, fireKey;

    public Controller(Keys upKey, Keys downKey, Keys fireKey) {
        this.upKey = upKey;
        this.downKey = downKey;
        this.fireKey = fireKey;
    }
}
