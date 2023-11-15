package edu.gonzaga;

import java.util.ArrayList;

public class Hand {
    ArrayList<Card> cards = new ArrayList<>();
    public Hand(){

    }
    public int calculateScore(){
       Integer score = 0;
       // I have included an int to keep track of the aces in a players hand so that we can either add 1 or 11 to their point total depending on the situation and their score.
       int aces = 0;
       for(Card card : c){
           if(card.getPlayerHand()) {
               score += card.getValue();
               if(card.getValue() == 11){
                   aces++;
               }
           }
       }
       while(score > 21 && aces > 0){
           // If the players score is greater than 21 and having an ace worth 1 point puts them under 21, then the score will automatically be adjusted to have a 1 instead of an 11 to keep the player in the game
           score -= 10;
           aces--;
       }
       return score;
    }

    public Boolean isBlackJack(ArrayList<Card> checkCards){

        return false;
    }

}
