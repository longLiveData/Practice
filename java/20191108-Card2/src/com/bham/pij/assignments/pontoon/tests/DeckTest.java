package com.bham.pij.assignments.pontoon.tests;

//import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

//import org.junit.jupiter.api.Test;

import com.bham.pij.assignments.pontoon.Card;
import com.bham.pij.assignments.pontoon.Deck;
import static org.junit.Assert.assertFalse;


import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class DeckTest {

	private final int NUM_TEST_ITERATIONS = 20;

	private final String[] values = {"TWO", "THREE", "FOUR", "FIVE", "SIX",
			"SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE"
	};
	
	private final String[] suits = {"HEARTS", "SPADES", "CLUBS", "DIAMONDS"};


	/*
	@TestParams 
	(
			purpose = "This test checks that your Deck constructor creates a deck of the correct size.",
			fail = "The Deck constructor does not create a deck of the correct size.",
			marks = 3.0f
	)
	*/
	@Test
	void test_Deck() {
		Deck deck = new Deck();
		int size = deck.getDeckSize();
		assertTrue(size == 52);
	}
	
	/*
	@TestParams 
	(
			purpose = "This test checks that your Deck constructor creates correct cards.",
			fail = "The Deck constructor does not create correct cards.",
			marks = 9.0f
	)
	*/
	@Test
	void test_Cards_correct() {
		Deck deck = new Deck();
		
		for (int i = 0; i < 52; i++) {
			Card c = deck.getCard(i);
			assertTrue(isValidSuit(c.getSuit()));
			assertTrue(isValidValue(c.getValue()));
		}
	}

	/*
	@TestParams 
	(
			purpose = "This test checks that your Deck constructor creates correct cards with no duplicates.",
			fail = "The Deck constructor creates duplicate cards.",
			marks = 9.0f
	)
	*/
	@Test
	void test_Cards_duplicates() {
		Deck deck = new Deck();
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < 52; i++) {
			cards.add(deck.getCard(i));
		}
		
		for (int i = 0; i < 52; i++) {
			for (int j = 0; j < 52; j++) {
				if (i != j) {
					boolean dup = cards.get(i).getSuit() == cards.get(j).getSuit() && cards.get(i).getValue() == cards.get(j).getValue();
					assertFalse(dup);
				}
			}
		}		
	}

	/*
	@TestParams 
	(
			purpose = "This test checks that your Deck class will not deal dealt cards.",
			fail = "The Deck constructor deals dealt cards.",
			marks = 9.0f
	)
	*/
	@Test
	void test_Cards_dealt() {
		
		Deck deck = new Deck();
		
		Card c1 = deck.dealCard(0);
		assertTrue(c1 != null);
		Card c2 = deck.dealCard(0);
		assertTrue(c2 == null);
		
		c1 = deck.dealCard(51);
		assertTrue(c1 != null);
		c2 = deck.dealCard(51);
		assertTrue(c2 == null);
		c2 = deck.dealCard(51);
		assertTrue(c2 == null);

		c1 = deck.dealCard(22);
		assertTrue(c1 != null);

		c1 = deck.dealCard(0);
		assertTrue(c1 == null);
	}

	/*
	@TestParams 
	(
			purpose = "This test checks that your Deck can be reset.",
			fail = "The Deck constructor does not correctly reset the deck.",
			marks = 7.0f
	)
	*/
	@Test
	void test_Deck_reset() {
		
		Deck deck = new Deck();
		
		for (int i = 0; i < 52; i++) {
			deck.dealCard(i);
		}
		
		deck.reset();
		
		for (int i = 0; i < 52; i++) {
			assertFalse(deck.dealCard(i) == null);
		}
	}

	private boolean isValidSuit(String s) {
		for (String suit: suits) {
			if (s.equals(suit)) {
				return true;
			}
		}
		return false;
	}

	private boolean isValidValue(String s) {
		for (String val: values) {
			if (s.equals(val)) {
				return true;
			}
		}
		return false;
	}
}

