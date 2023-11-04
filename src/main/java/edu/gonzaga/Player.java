package edu.gonzaga;

import java.util.ArrayList;

public class Player {
    private Integer totalScore;
    private Integer BettingValue;
    private Hand playerHand;
    private String name;
    ArrayList<Integer> playerValues;

    public Player(String name){
        this.name = "DEFAULT_NAME";
        this.playerHand = new Hand();
    }

    public Hand getHand(){
        return playerHand;
    }

    public void setHand(Hand hand){
        this.playerHand = hand;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
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
