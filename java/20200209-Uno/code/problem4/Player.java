import java.util.ArrayList;

public class Player {

    private ArrayList<String> cards = new ArrayList();

    public Player(String cards) {
        for (String c : cards.split(" ")) {
            this.cards.add(c);
        }
    }

    public boolean isEmpty(){
        return this.cards.size() == 0;
    }

    public void removeCard(String card){
        this.cards.remove(card);
    }

    public void addCard(String card){
        this.cards.add(card);
    }

    public String getNextCard(String card){
        // judge loco
        String loco = card.charAt(0) + "8";
        if (this.cards.indexOf(loco) >= 0) {
            return loco;
        }

        int color = 0;
        int num = 0;
        for (String temp: this.cards) {
            if (temp.charAt(0) == card.charAt(0)) {
                color += 1;
            }
            if (temp.charAt(1) == card.charAt(1)) {
                num += 1;
            }
        }
        String res = null;
        if (color == 0 && num == 0) { // no card
            return res;
        }
        if (color >= num) { // color card
            res = card.charAt(0) + "8";
            for (String temp: this.cards) {
                if (temp.charAt(0) == card.charAt(0) && temp.compareTo(res) < 0) {
                    res = temp;
                }
            }
            return res;
        } else { //number card
            String colorString = "RYGB";
            res = "B" + card.charAt(1);
            for (String temp: this.cards) {
                if (temp.charAt(1) == card.charAt(1) && colorString.indexOf(temp.charAt(0)) < colorString.indexOf(res.charAt(0))) {
                    res = temp;
                }
            }
            return res;
        }
    }

    public String getTwo() {
        String colorString = "RYGBN";
        String res = "N2";
        for (String temp: this.cards) {
            if (temp.charAt(1) == '2' && colorString.indexOf(temp.charAt(0)) < colorString.indexOf(res.charAt(0))) {
                res = temp;
            }
        }
        if (res.equals("N2")) {
            return null;
        } else {
            return res;
        }
    }

    public String getNewColor() {
        String newColor = "N";
        String colorString = "RYGBN";
        for (String temp: this.cards) {
            if (colorString.indexOf(temp.charAt(0)) < colorString.indexOf(newColor.charAt(0))) {
                newColor = temp.substring(0, 1);
            }
        }
        return newColor + "9";
    }

    public String getCardByColor(String color) {
        String res = "";

        // first return a "X8"
        String colorString = "RYGB";
        for (String temp: this.cards) {
            if (temp.charAt(1) == '8') {
                if (res.equals("")) {
                    res = temp;
                } else {
                    if (colorString.indexOf(temp.charAt(0)) < colorString.indexOf(res.charAt(0))) {
                        res = temp;
                    }
                }
            }
        }
        if (!res.equals("")) {
            return res;
        }

        // then return a new color card
        res = color;
        for (String temp: this.cards) {
            if (temp.charAt(0) == color.charAt(0) && temp.compareTo(res) < 0) {
                res = temp;
            }
        }
        if (res.charAt(1) == '9') {
            return null;
        } else {
            return res;
        }
    }
}
