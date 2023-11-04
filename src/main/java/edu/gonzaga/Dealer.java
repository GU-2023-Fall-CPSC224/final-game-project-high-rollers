package edu.gonzaga;

import java.util.ArrayList;

public class Dealer {
    private Integer totalScore;
    private Hand dealerHand;
    ArrayList<Integer> dealerValues;

    public Dealer(){
        dealerHand = new Hand();
    }

    public void setInitialScore(){
        totalScore = 0;
    }

    public void setScore(Integer cardValue){
        totalScore += cardValue;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

}
