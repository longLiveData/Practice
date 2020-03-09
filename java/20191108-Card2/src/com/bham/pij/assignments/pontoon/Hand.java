package com.bham.pij.assignments.pontoon;

import java.util.ArrayList;

public class Hand {
    
    private ArrayList<Card> cardList = new ArrayList<>();
    private ArrayList<Integer> numericalValueList = new ArrayList<>();
    
    public Hand(){}
    
    public void addCard(Card card) {
        this.cardList.add(card);
        if (card.getValue().equals("ACE")) {
            if (this.numericalValueList.size() == 2) {
                this.numericalValueList.set(0, this.numericalValueList.get(0) + 11);
                this.numericalValueList.set(1, this.numericalValueList.get(1) + 1);
            } else if(this.numericalValueList.size() == 1){
                this.numericalValueList.set(0, this.numericalValueList.get(0) + 11);
                this.numericalValueList.add(1);
            } else {
                this.numericalValueList.add(11);
                this.numericalValueList.add(1);
            }
        } else {
            if (this.numericalValueList.size() == 2) {
                this.numericalValueList.set(0, this.numericalValueList.get(0) + card.getNumericalValue().get(0));
                this.numericalValueList.set(1, this.numericalValueList.get(1) + card.getNumericalValue().get(0));
            } else if(this.numericalValueList.size() == 1){
                this.numericalValueList.set(0, this.numericalValueList.get(0) + card.getNumericalValue().get(0));
            } else {
                this.numericalValueList.add(card.getNumericalValue().get(0));
            }
        }
    }
    
    public Card getCard(int index) {
        return this.cardList.get(index);
    }
    
    public int getHandSize() {
        return this.cardList.size();
    }
    
    public ArrayList<Integer> getNumericalValue() {
        return numericalValueList;
    }
    
}
