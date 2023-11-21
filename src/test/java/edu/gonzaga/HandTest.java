package edu.gonzaga;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class HandTest {
    @Test
    public void testAces(){
        Hand hand = new Hand();
        ArrayList<Card> cards = new ArrayList<>();
        Card card1 = new Card(11,"clubs");
        card1.setPlayerHand(true);
        Card card2 = new Card(10,"clubs");
        card2.setPlayerHand(true);
        hand.entityCards.add(card1);
        hand.entityCards.add(card2);
        Integer score = hand.calculateScore();
        assertEquals(21,score);
    }
    @Test
    public void testAces2(){
        Hand hand = new Hand();
        Card card1 = new Card(9,"clubs");
        Card card2 = new Card(10,"clubs");
        Card card3 = new Card(11,"clubs");
        card1.setPlayerHand(true);
        card2.setPlayerHand(true);
        card3.setPlayerHand(true);
        hand.entityCards.add(card1);
        hand.entityCards.add(card2);
        hand.entityCards.add(card3);
        Integer score = hand.calculateScore();
        assertEquals(20,score);
    }
    @Test
    public void testNormalScore(){
        Hand hand = new Hand();
        Card card1 = new Card(7,"clubs");
        Card card2 = new Card(7,"clubs");
        Card card3 = new Card(3,"clubs");
        card1.setPlayerHand(true);
        card2.setPlayerHand(true);
        card3.setPlayerHand(true);
        hand.entityCards.add(card1);
        hand.entityCards.add(card2);
        hand.entityCards.add(card3);
        Integer score = hand.calculateScore();
        assertEquals(17,score);
    }
    @Test
    public void testOver21(){
        Hand hand = new Hand();
        Card card1 = new Card(10,"clubs");
        Card card2 = new Card(9,"clubs");
        Card card3 = new Card(7,"clubs");
        card1.setPlayerHand(true);
        card2.setPlayerHand(true);
        card3.setPlayerHand(true);
        hand.entityCards.add(card1);
        hand.entityCards.add(card2);
        hand.entityCards.add(card3);
        Integer score = hand.calculateScore();
        assertEquals(26,score);
    }

}

