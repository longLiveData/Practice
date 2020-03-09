package com.bham.pij.assignments.pontoon.tests;

public class Test {
    
    public static void main(String[] args) {
        
        CardTest ct = new CardTest();
        ct.test_Card();
        ct.test_getSuit();
        ct.test_getValue();
        ct.test_getRandomSuit();
        ct.test_getRandomValue();
        ct.test_getNumericalValue_1();
        ct.test_getNumericalValue_2();
        ct.test_getNumericalValue_3();
        ct.test_getNumericalValue_4();
        
        DeckTest dt = new DeckTest();
        dt.test_Deck();
        dt.test_Cards_correct();
        dt.test_Cards_duplicates();
        dt.test_Cards_dealt();
        dt.test_Deck_reset();
        
        HandTest ht = new HandTest();
        ht.test_Hand();
        ht.test_addCard();
        ht.test_addGetHandSize();
        ht.test_addGetNumericalValue_1();
        ht.test_addGetNumericalValue_2();
        
    }
}
