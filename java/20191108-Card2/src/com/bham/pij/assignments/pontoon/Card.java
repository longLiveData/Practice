package com.bham.pij.assignments.pontoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Card {
    
    private final String suit;
    private final String value;
    
    private static final ArrayList<String> values = new ArrayList<>(
            Arrays.asList("", "ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", 
            "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING"));
    
    private static final String[] suits = {"HEARTS", "SPADES", "CLUBS", "DIAMONDS"};
    
    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }
    
    public String getSuit() {
        return this.suit;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public static String getRandomSuit() {
        Random r = new Random(1);
        int index = r.nextInt(4);
        return suits[index];
    }
    
    public static String getRandomValue() {
        Random r = new Random(1);
        int index = r.nextInt(13);
        return values.get(index);
    }
    
    public ArrayList<Integer> getNumericalValue() {
        ArrayList<Integer> res = new ArrayList<>();
        if (this.value.equals("ACE")) {
            res.add(11);
            res.add(1);
        } else {
            res.add(Math.min(values.indexOf(this.value), 10));     
        }
        return res;
    }
}
