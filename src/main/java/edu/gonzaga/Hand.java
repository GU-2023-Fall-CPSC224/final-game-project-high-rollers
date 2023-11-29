package edu.gonzaga;

import java.util.ArrayList;

public class Hand {
    public static final int BLACKJACK = 21;
    ArrayList<Card> entityCards = new ArrayList<>();
    public Hand(){

    }
    public int calculateScore(){
        int totalScore = 0;
        int aces = 0;
        for (Card card : entityCards) {
                totalScore += card.getValue();
                if (card.getValue() == 11) {
                    aces++;
                }
        }
        while (totalScore > BLACKJACK && aces > 0) {
            totalScore -= 10;
            aces--;
        }
        return totalScore;
    }

    public Boolean isBlackJack(){
        boolean isBlackJack = false;
        int score = calculateScore();
        if(score == BLACKJACK){
            isBlackJack = true;
        }
        return isBlackJack;
    }

    public String getPlayerCard(Deck deck){
        Card card = deck.givePlayerCard();
        entityCards.add(card);
        return card.getCardImage();
    }

    public String getDealerCard(Deck deck){
        Card card = deck.giveDealerCard();
        entityCards.add(card);
        return card.getCardImage();
    }




}
