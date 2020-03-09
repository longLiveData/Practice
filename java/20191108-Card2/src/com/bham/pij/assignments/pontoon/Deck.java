package com.bham.pij.assignments.pontoon;

import java.util.ArrayList;

public class Deck {
    
    private final int size = 52;
    private ArrayList<Card> cardList;
    
    private final String[] suits = {"HEARTS", "SPADES", "CLUBS", "DIAMONDS"};
    private final String[] values = {"TWO", "THREE", "FOUR", "FIVE", "SIX",
	"SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE"};
    
    public Deck(){
        this.cardList = new ArrayList<>();
        for (String suit : suits) {
            for (String value : values) {
                this.cardList.add(new Card(suit, value));
            }
        }
    }
    
    public int getDeckSize() {
        return size;
    }
    
    public Card getCard(int index) {
        return this.cardList.get(index);
    }
    
    public Card dealCard(int index) {
        if (this.cardList.get(index) != null) {
            Card card = this.cardList.get(index);
            this.cardList.set(index, null);
            return card;
        }
        return null;
    }
    
    public void reset() {
        this.cardList = new ArrayList<>();
        for (String suit : suits) {
            for (String value : values) {
                this.cardList.add(new Card(suit, value));
            }
        }
    }
    
    
}
