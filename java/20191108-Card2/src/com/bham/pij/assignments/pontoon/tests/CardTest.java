package com.bham.pij.assignments.pontoon.tests;

//import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

//import org.junit.jupiter.api.Test;

import com.bham.pij.assignments.pontoon.Card;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class CardTest {

	private final int NUM_TEST_ITERATIONS = 20;

	private final String[] values = {"TWO", "THREE", "FOUR", "FIVE", "SIX",
			"SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE"
	};
	
	private final String[] suits = {"HEARTS", "SPADES", "CLUBS", "DIAMONDS"};

	/*
	@TestParams 
	(
			purpose = "This test checks that you have provided the correct Card constructor.",
			fail = "The correct constructor does not exist.",
			marks = 3.0f
	)
	*/
	@Test
	void test_Card() {
		Card c = new Card("CLUBS", "TWO");
	}

	/*
	@TestParams 
	(
			purpose = "This test checks that your getSuit() method returns the correct value.",
			fail = "Your getSuit() method does not return the correct value.",
			marks = 3.0f
	)
	*/
	@Test
	void test_getSuit() {
		Card c = new Card("CLUBS", "TWO");
		assertTrue(c.getSuit().equals("CLUBS"));
		c = new Card("DIAMONDS", "ACE");
		assertTrue(c.getSuit().equals("DIAMONDS"));		
	}

	/*
	@TestParams 
	(
			purpose = "This test checks that your getValue() method returns the correct value.",
			fail = "Your getValue() method does not return the correct value.",
			marks = 3.0f
	)
	*/
	@Test
	void test_getValue() {
		Card c = new Card("CLUBS", "TWO");
		assertTrue(c.getValue().equals("TWO"));
		c = new Card("DIAMONDS", "ACE");
		assertTrue(c.getValue().equals("ACE"));		
	}

	/*
	@TestParams 
	(
			purpose = "This test checks that your getRandomSuit() method returns a valid suit.",
			fail = "Your getRandomSuit() method does not return a valid suit.",
			marks = 6.0f
	)
	*/
	@Test
	void test_getRandomSuit() {
		
		String suit = Card.getRandomSuit();
		assertTrue(isValidSuit(suit));
		
		for (int i = 0; i < NUM_TEST_ITERATIONS ; i++) {
			String s = Card.getRandomSuit();
			assertTrue(isValidSuit(s));			
		}		
	}

	/*
	@TestParams 
	(
			purpose = "This test checks that your getRandomValue() method returns a valid suit.",
			fail = "Your getRandomValue() method does not return a valid suit.",
			marks = 6.0f
	)
	*/
	@Test
	void test_getRandomValue() {
		String value = Card.getRandomValue();
		assertTrue(isValidValue(value));
		
		for (int i = 0; i < NUM_TEST_ITERATIONS ; i++) {
			String v = Card.getRandomValue();
			assertTrue(isValidValue(v));			
		}		
	}
	
	/*
	@TestParams 
	(
			purpose = "This test checks that your getNumericalValue() method returns the correct value(s).",
			fail = "Your getNumericalValue() method does not return the correct value(s).",
			marks = 4.0f
	)
	*/
	
	@Test
	void test_getNumericalValue_1() {
		
		for (int i = 1; i < 10; i++) {
			Card c = new Card("DIAMONDS", values[i]);
			ArrayList<Integer> vals = c.getNumericalValue();
			assertTrue(vals.size() == 1);
		}
		
	}

	/*
	@TestParams 
	(
			purpose = "This test checks that your getNumericalValue() method returns the correct value(s).",
			fail = "Your getNumericalValue() method does not return the correct value(s).",
			marks = 10.0f
	)
	*/
	@Test
	void test_getNumericalValue_2() {
		
		for (int i = 0; i < 9; i++) {
			Card c = new Card("DIAMONDS", values[i]);
			ArrayList<Integer> vals = c.getNumericalValue();
			assertTrue(vals.get(0) == i+2);
		}
	}

	/*
	@TestParams 
	(
			purpose = "This test checks that your getNumericalValue() method returns the correct value(s).",
			fail = "Your getNumericalValue() method does not return the correct value(s).",
			marks = 8.0f
	)*/
	@Test
	void test_getNumericalValue_3() {
		Card c = new Card("HEARTS", "JACK");
		ArrayList<Integer> vals = c.getNumericalValue();
		assertTrue(vals.get(0) == 10);
		c = new Card("HEARTS", "QUEEN");
		vals = c.getNumericalValue();
		assertTrue(vals.get(0) == 10);
		c = new Card("HEARTS", "KING");
		vals = c.getNumericalValue();
		assertTrue(vals.get(0) == 10);		
	}

	/*
	@TestParams 
	(
			purpose = "This test checks that your getNumericalValue() method returns the correct value(s).",
			fail = "Your getNumericalValue() method does not return the correct value(s).",
			marks = 7.0f
	)
	*/
	@Test
	void test_getNumericalValue_4() {
		Card c = new Card("HEARTS", "ACE");
		ArrayList<Integer> vals = c.getNumericalValue();
		assertTrue(vals.size()==2);
		assertTrue(vals.get(0) == 11);
		assertTrue(vals.get(1) == 1);
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
