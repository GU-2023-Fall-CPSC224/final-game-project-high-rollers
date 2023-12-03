package edu.gonzaga;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


public class DeckTest {
    @Test
    void testNumberOfCards() {
        Deck deck = new Deck();
        int expectedCardCount = 52;
        int actualCardCount = 0;
        for (Card card : deck.cards) {
            actualCardCount++;
        }
        assertEquals(expectedCardCount,actualCardCount);
    }
    @Test
    void testNumberOfClub(){
        Deck deck = new Deck();
        int expectedClubs = 13;
        String club = "clubs";
        int actual = 0;
        for(Card card : deck.cards){
            if(card.getSuit().equals(club)){
                actual++;
            }
        }
        assertEquals(expectedClubs,actual);
    }
    @Test
    void testNumberOfHeart(){
        Deck deck = new Deck();
        int expectedClubs = 13;
        String club = "hearts";
        int actual = 0;
        for(Card card : deck.cards){
            if(card.getSuit().equals(club)){
                actual++;
            }
        }
        assertEquals(expectedClubs,actual);
    }
    @Test
    void testNumberOfDiamond(){
        Deck deck = new Deck();
        int expectedClubs = 13;
        String club = "diamonds";
        int actual = 0;
        for(Card card : deck.cards){
            if(card.getSuit().equals(club)){
                actual++;
            }
        }
        assertEquals(expectedClubs,actual);
    }
    @Test
    void testNumberOfSpade(){
        Deck deck = new Deck();
        int expectedClubs = 13;
        String club = "spades";
        int actual = 0;
        for(Card card : deck.cards){
            if(card.getSuit().equals(club)){
                actual++;
            }
        }
        assertEquals(expectedClubs,actual);
    }
    @Test
    void testCardNumberGivePlayer(){
        Deck deck = new Deck();
        int expectedAvailable = 42;
        for(int i = 0; i < 10; i++){
            deck.givePlayerCard();
        }
        assertEquals(expectedAvailable,deck.availableCards);
    }
    @Test
    void testCardNumberGiveDealer(){
        Deck deck = new Deck();
        int expectedAvailable = 42;
        for(int i = 0; i < 10; i++){
            deck.giveDealerCard();
        }
        assertEquals(expectedAvailable,deck.availableCards);
    }
    @Test
    void testNumberJacks(){
        Deck deck = new Deck();
        int expectedJacks = 4;
        int actual = 0;
        String name = null;
        for(Card card : deck.cards){
            name = "jack_of_" + card.getSuit() + ".png";
            if(Objects.equals(card.getCardName(), name)){
                actual++;
            }
        }
    }
    @Test
    void testNumberKing(){
        Deck deck = new Deck();
        int expectedKings = 4;
        int actual = 0;
        String name = null;
        for(Card card : deck.cards){
            name = "king_of_" + card.getSuit() + ".png";
            if(Objects.equals(card.getCardName(), name)){
                actual++;
            }
        }
    }
}
