package com.bham.pij.assignments.pontoon.tests;

//import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
//import org.junit.jupiter.api.Test;

import com.bham.pij.assignments.pontoon.Card;
import com.bham.pij.assignments.pontoon.Hand;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class HandTest {

	private final int NUM_TEST_ITERATIONS = 20;

	private final String[] values = {"TWO", "THREE", "FOUR", "FIVE", "SIX",
			"SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE"
	};
	
	private final String[] suits = {"HEARTS", "SPADES", "CLUBS", "DIAMONDS"};

	/*
	@TestParams 
	(
			purpose = "This test checks that your class has the correct Hand constructor.",
			fail = "Your class does not have the correct constructor.",
			marks = 2.0f
	)
	*/
	@Test
	void test_Hand() {
		Hand hand = new Hand();
	}
	
	/*
	@TestParams 
	(
			purpose = "This test checks your addCard() method.",
			fail = "Your class does not have the correct constructor.",
			marks = 3.0f
	)
	*/
	@Test
	void test_addCard() {
		
		Hand hand = new Hand();
		
		Card card = new Card("CLUBS", "EIGHT");
		
		hand.addCard(card);
		
		Card c = hand.getCard(0);
		
		assertTrue(c.getSuit().equals("CLUBS") && c.getValue().equals("EIGHT"));
	}
	
	/*
	@TestParams 
	(
			purpose = "This test checks your getHandSize() method.",
			fail = "Your getHandSize() method doesn't return the correct value.",
			marks = 3.0f
	)
	*/
	@Test
	void test_addGetHandSize() {
		
		Hand hand = new Hand();
		
		assertTrue(hand.getHandSize() == 0);
		
		Card card = new Card("CLUBS", "EIGHT");
		
		hand.addCard(card);
		
		assertTrue(hand.getHandSize() == 1);
		
		for (int i = 0; i < 10; i++) {
			hand.addCard(new Card(Card.getRandomSuit(), Card.getRandomValue()));
		}
		
		assertTrue(hand.getHandSize() == 11);
	}
	
	/*
	@TestParams 
	(
			purpose = "This test checks your getNumericalValue method returns the correct value(s).",
			fail = "Your getNumericalValue() method did not return the correct value(s).",
			marks = 6.0f
	)
	*/
	@Test
	void test_addGetNumericalValue_1() {
		
		Hand hand = new Hand();
		
		hand.addCard(new Card("CLUBS", "TWO"));
		
		assertTrue(hand.getNumericalValue().get(0) == 2);

		hand.addCard(new Card("HEARTS", "TEN"));

		assertTrue(hand.getNumericalValue().get(0) == 12);

		hand.addCard(new Card("HEARTS", "QUEEN"));
		
		assertTrue(hand.getNumericalValue().get(0) == 22);

		hand.addCard(new Card("SPADES", "FIVE"));
		
		assertTrue(hand.getNumericalValue().get(0) == 27);
	}
	
	/*
	@TestParams 
	(
			purpose = "This test checks your getNumericalValue method returns the correct value(s).",
			fail = "Your getNumericalValue() method did not return the correct value(s).",
			marks = 6.0f
	)
	*/
	@Test
	void test_addGetNumericalValue_2() {
		
		Hand hand = new Hand();
		
		hand.addCard(new Card("CLUBS", "ACE"));
		
		ArrayList<Integer> values = hand.getNumericalValue();
		
		assertTrue(values.size() == 2);
		
		assertTrue(values.get(0) == 11);

		assertTrue(values.get(1) == 1);
		
		hand.addCard(new Card("CLUBS", "KING"));

		values = hand.getNumericalValue();
		
		assertTrue(values.size() == 2);
		
		assertTrue(values.get(0) == 21);

		assertTrue(values.get(1) == 11);
		
		hand.addCard(new Card("DIAMONDS", "ACE"));
		
		values = hand.getNumericalValue();
		
		assertTrue(values.size() == 2);
		
		assertTrue(values.get(0) == 32);

		assertTrue(values.get(1) == 12);

		hand.addCard(new Card("DIAMONDS", "SEVEN"));
		
		values = hand.getNumericalValue();
		
		assertTrue(values.size() == 2);
		
		assertTrue(values.get(0) == 39);

		assertTrue(values.get(1) == 19);
	}
}

