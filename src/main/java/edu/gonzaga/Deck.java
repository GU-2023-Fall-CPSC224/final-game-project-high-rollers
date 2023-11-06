package edu.gonzaga;
import java.util.ArrayList;
import java.util.Collections;
public class Deck {
    public ArrayList<Card> cards = new ArrayList<>();
    ArrayList<String> suits = new ArrayList<>();
    Integer availableCards;
    Integer totalCards;
    public Deck(){
        availableCards = 52;
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
        for (int k = 0; k <= 3; k++) {
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
    //gives player the first card in the deck that isnt used
    public Boolean givePlayerCard(){
        Boolean cardGiven = false;
        int index = 0;
        while(!cardGiven && index < totalCards){
            if(availableCard(cards.get(index))){
                cards.get(index).setPlayerHand(true);
                cardGiven = true;
                availableCards--;
            }else index++;
        }
        return cardGiven;
    }
    //suffles the deck *MAKE SURE TO RESET DECK IF THE START OF A NEW ROUND*
    public void shuffleDeck(){
        Collections.shuffle(cards);
    }
    //gives the dealer the first card in the deck that isnt used
    public void giveDealerCard(){
        Boolean cardGiven = false;
        int index = 0;
        while(!cardGiven && index < totalCards){
            if(availableCard(cards.get(index))){
                cards.get(index).setDealerHand(true);
                cardGiven = true;
                availableCards--;
            }else index++;
        }
    }
    // takes cards used by dealer and player and puts them in the discard pile
    public void discardUsedCards(){
        for(Card card : cards){
           if(card.getDealerHand() || card.getPlayerHand()){
               card.setDiscard(true);
               card.setPlayerHand(false);
               card.setDealerHand(false);
           }
        }
    }
    //sets ALL cards back to being unused
    public void resetDeck() {
        for (Card card : cards) {
            card.setPlayerHand(false);
            card.setDealerHand(false);
            card.setDiscard(false);
            availableCards = totalCards;
        }
    }
    //checks to see if a card is used
    public Boolean availableCard(Card card){
        boolean cardAvailable = !card.getPlayerHand() && !card.getDealerHand() && !card.getDiscard();
        return cardAvailable;
    }

// prints all cards
    public void print(){
        System.out.println("ALL CARDS");
        for (Card card : cards) {
            System.out.print(card.getValue() + " of " + card.getSuit() + " - ");
        }
        System.out.println();
    }
    // only prints the players cards
    public void printPlayerCards(){
        System.out.println("PLAYER CARDS");
        for (Card card : cards) {
            if(card.getPlayerHand()) {
                System.out.print(card.getValue() + " of " + card.getSuit() + " - ");
            }
        }
        System.out.println();
    }
    // only prints the dealers cards
    public void printDealerCards(){
        System.out.println("DEALER CARDS");
        for (Card card : cards) {
            if(card.getDealerHand()) {
                System.out.print(card.getValue() + " of " + card.getSuit() + " - ");
            }
        }
        System.out.println();
    }
}
