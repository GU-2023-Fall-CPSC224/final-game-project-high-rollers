package edu.gonzaga;
import java.util.ArrayList;
import java.util.Collections;
public class Deck {
    public ArrayList<Card> cards = new ArrayList<>();
    ArrayList<String> suits = new ArrayList<>();
    Integer availableCards;
    Integer totalCards;

    public Deck() {
        availableCards = 52;
        totalCards = 52;
        suits.add(0, "diamonds");
        suits.add(1, "hearts");
        suits.add(2, "spades");
        suits.add(3, "clubs");

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

    //for testing purposes to set the deck to what cards we want
    public Deck(ArrayList<Card> importCards) {
        cards = importCards;
    }

    //gives player the first card in the deck that isn't used
    public Card givePlayerCard() {
        Boolean cardGiven = false;
        int index = 0;
        Card playerCard = null;
        while (!cardGiven && index < totalCards) {
            if (availableCard(cards.get(index))) {
                cards.get(index).setPlayerHand(true);
                //duplicated card being given to player to add to the player array
                playerCard = new Card(cards.get(index).getValue(), cards.get(index).getSuit());
                //displays card being given to entity
                cardGiven = true;
                availableCards--;
            } else index++;
        }
        return playerCard;
    }

    //suffles the deck *MAKE SURE TO RESET DECK IF THE START OF A NEW ROUND*
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    //gives the dealer the first card in the deck that isn't used
    public Card giveDealerCard() {
        Boolean cardGiven = false;
        int index = 0;
        Card dealerCard = null;
        while (!cardGiven && index < totalCards) {
            if (availableCard(cards.get(index))) {
                cards.get(index).setDealerHand(true);
                //duplicated card being given to player to add to the player array
                dealerCard = new Card(cards.get(index).getValue(), cards.get(index).getSuit());
                //displays card being given to entity
                cardGiven = true;
                availableCards--;
            } else index++;
        }
        return dealerCard;
    }

    //checks to see if a card is used
    public Boolean availableCard(Card card) {
        return !card.getPlayerHand() && !card.getDealerHand() && !card.getDiscard();
    }
}
