package edu.gonzaga;
import java.util.ArrayList;
import java.util.Collections;
public class Deck {
    public ArrayList<Card> cards = new ArrayList<>();
    ArrayList<String> suits = new ArrayList<>();
    Integer availableCards;
    Integer totalCards;
    ArrayList<String> faceCards = new ArrayList<>();


    public Deck() {
        availableCards = 52;
        totalCards = 52;
        suits.add(0, "diamonds");
        suits.add(1, "hearts");
        suits.add(2, "spades");
        suits.add(3, "clubs");

        faceCards.add(0,"jack");
        faceCards.add(1,"king");
        faceCards.add(2, "queen");

        String fileName;
        //for values 2-9 of each suit
        for (Integer value = 2; value <= 10; value++) {
            for (String suit : suits) {
                fileName = "PNG-cards-1.3/" + value + "_of_" + suit + ".png";
                Card card = new Card(value, suit, fileName);
                cards.add(card);
            }
        }
        //for each suit add 4 values of 10
        for (int k = 0; k <= 2; k++) {
            for (String suit : suits) {
                fileName = "PNG-cards-1.3/" + faceCards.get(k) + "_of_" + suit + ".png";
                System.out.println(fileName);
                Card card = new Card(10, suit, fileName);
                cards.add(card);
            }
        }
        //for each suit add a value 11
        for (String suit : suits) {
            fileName = "PNG-cards-1.3/" + "ace_of_" + suit + ".png";
            Card card = new Card(11, suit, fileName);
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
                playerCard = new Card(cards.get(index).getValue(), cards.get(index).getSuit(), cards.get(index).getCardName());
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
                dealerCard = new Card(cards.get(index).getValue(), cards.get(index).getSuit(), cards.get(index).getCardName());
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
