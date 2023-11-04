package edu.gonzaga;
import java.util.ArrayList;
public class Deck {
    ArrayList<Card> cards = new ArrayList<>();
    ArrayList<String> suits = new ArrayList<>();
    Integer totalCards;
    public Deck(){
        totalCards = 52;
        suits.add(0,"diamond");
        suits.add(1,"heart");
        suits.add(2,"spade");
        suits.add(3,"club");

        //for values 2-9 of each suit
        for (int value = 2; value <= 9; value++) {
            for (String suit : suits) {
                Card card = new Card(value, suit);
                cards.add(card);
            }
        }
        //for each suit add 4 values of 10
        for (int k = 0; k <= 4; k++) {
            for (String suit : suits) {
                Card card = new Card(10, suit);
                cards.add(card);
            }
        }
        //for each suit add a value 11
        for (String suit : suits) {
            Card card = new Card(11, suit);
            cards.add(card);
        }
    }
    public void print(){
        for (Card card : cards) {
            System.out.print(card.getValue() + ": " + card.getSuit() + " ");
        }
    }
}
