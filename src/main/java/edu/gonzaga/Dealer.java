package edu.gonzaga;

import java.util.ArrayList;

public class Dealer {
    private Integer totalScore;
    private Hand dealerHand;

    public Dealer(){
        this.dealerHand = new Hand();
    }

    public Hand getDealerHand(){
        return dealerHand;
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
