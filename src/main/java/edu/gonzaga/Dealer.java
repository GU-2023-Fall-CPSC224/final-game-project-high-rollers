package edu.gonzaga;

public class Dealer {
    private Integer totalCardScore;
    private Hand dealerHand;

    public Dealer(){
        this.dealerHand = new Hand();
    }

    public Hand getDealerHand(){
        return dealerHand;
    }

    public void setInitialScore(){
        totalCardScore = 0;
    }

    public void setScore(Integer cardValue){
        totalCardScore += cardValue;
    }

    public Integer getTotalCardScore() {
        return totalCardScore;
    }

}
