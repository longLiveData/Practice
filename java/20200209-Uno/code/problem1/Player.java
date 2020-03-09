package problem1;

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
}
