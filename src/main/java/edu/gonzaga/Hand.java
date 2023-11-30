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

    public void givePlayerCard(Deck deck){
        entityCards.add(deck.givePlayerCard());
    }

    public void giveDealerCard(Deck deck){
        entityCards.add(deck.giveDealerCard());
    }




}
