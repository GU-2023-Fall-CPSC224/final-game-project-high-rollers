package edu.gonzaga;

import java.util.Scanner;

public class Round {
    Deck deck;
    Hand playerHand = new Hand();
    Hand dealerHand = new Hand();
    int playerCardScore = 0;
    int dealerCardScore = 0;
    int dealerStartScore = 0;
    // 0 win status is dealer win, 1 win status is tie, 2 win status in player win.
    int winStatus;
    public static final int BLACKJACK = 21;


    //create round
    public Round(Deck deck) {
        this.deck = deck;
        System.out.println("------------ NEW ROUND ----------");
        //shuffle the deck
        deck.shuffleDeck();
        //deal as normal order give player top card then dealer top card
        //playerHand.getPlayerCard(deck);
        //dealerHand.getDealerCard(deck);
        //using start score bc player can only see the first card dealer draws
        dealerStartScore = dealerHand.calculateScore();
        //then give each player another card
        //playerHand.getPlayerCard(deck);
        dealerHand.getDealerCard(deck);
        //calculate full scores of each hand
        playerCardScore = playerHand.calculateScore();
        dealerCardScore = dealerHand.calculateScore();

    }
    public String getCard(){
        return playerHand.getPlayerCard(deck);
    }
    public String getDealerCard(){
        return dealerHand.getDealerCard(deck);
    }




    public int playRound() {
        // Check for Blackjack at the beginning
        if (playerHand.isBlackJack() && !dealerHand.isBlackJack()) {
            System.out.println("PLAYER WINS - BLACKJACK");
            displayScores();
        } else if (!playerHand.isBlackJack() && dealerHand.isBlackJack()) {
            System.out.println("DEALER WINS - BLACKJACK");
            displayScores();
        }
        //must display start scores bc player shouldnt see dealer full score
        displayStartScores();
        //start the players turn as they go first
        playerTurn();
        //if the player busted theres no reason for the dealer to go
        if (playerCardScore < 21) {
            dealerTurn();
        }
        //if the dealer didn't bust and they ended up with a score close to 21 then the player
        if ((dealerCardScore <= BLACKJACK && dealerCardScore > playerCardScore) || playerCardScore > 21) {
            //remember win status 0 is dealer win
            winStatus = 0;
        } else if (dealerCardScore == playerCardScore) {
            //if they tied win status is 1
            winStatus = 1;
        } else {
            // if neither of these things then player one and win status is 2
            winStatus = 2;
        }
        //return win status for main game to process
        return winStatus;
    }
//displays the full scores
    private void displayScores() {
        System.out.println("Player score: " + playerCardScore);
        System.out.println("Dealer score: " + dealerCardScore);
    }
// prints out the players card score and then the dealers first card
    private void displayStartScores() {
        System.out.println("Player score: " + playerCardScore);
        System.out.println("Dealer score: " + dealerStartScore + " + ?");
    }
// player algorithm
    public String playerTurn() {

        if(!(playerCardScore < BLACKJACK)){
            return "";
        }

        String cardNum = playerHand.getPlayerCard(deck);
        //calculate their new score
        playerCardScore = playerHand.calculateScore();
        //if that hit caused them to bust
        if (playerCardScore > BLACKJACK) {
            System.out.println("PLAYER BUST");
        }
        System.out.println("Player score: " + playerCardScore);
        displayScores();
        return cardNum;
    }
    //dealer algorithm
    public void dealerTurn() {
        //while the dealer card score is less than the player cards score
        while (dealerCardScore < playerCardScore) {
            //while it's less than we need a card so give dealer the card
            dealerHand.getDealerCard(deck);
            System.out.println("Dealer Hit");

            //calculate the score with that new card
            dealerCardScore = dealerHand.calculateScore();
            //if the dealer busted then their turn is over
            System.out.println("Dealer score: " + dealerCardScore);
            if (dealerCardScore > BLACKJACK) {
                System.out.println("DEALER BUST");
                break;
            }
        }
    }
}



