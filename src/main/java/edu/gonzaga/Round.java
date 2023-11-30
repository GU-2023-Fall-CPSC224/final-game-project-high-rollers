package edu.gonzaga;

import java.util.Scanner;

public class Round {
    Deck deck = new Deck();
    Hand playerHand = new Hand();
    Hand dealerHand = new Hand();
    int playerCardScore = 0;
    int dealerCardScore = 0;
    int dealerStartScore = 0;
    // 0 win status is dealer win, 1 win status is tie, 2 win status in player win.
    int winStatus;
    public static final int BLACKJACK = 21;
    public static final int DEALER_CUT_OFF = 17;

    //create round
    public Round() {
        System.out.println("------------ NEW ROUND ----------");
        //shuffle the deck
        deck.shuffleDeck();
        //deal as normal order give player top card then dealer top card
        playerHand.givePlayerCard(deck);
        dealerHand.giveDealerCard(deck);
        //using start score bc player can only see the first card dealer draws
        dealerStartScore = dealerHand.calculateScore();
        //then give each player another card
        playerHand.givePlayerCard(deck);
        dealerHand.giveDealerCard(deck);
        //calculate full scores of each hand
        playerCardScore = playerHand.calculateScore();
        dealerCardScore = dealerHand.calculateScore();
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
            //remeber win status 0 is dealer win
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
    public void playerTurn() {
        //while the player hasn't busted
        while (playerCardScore < BLACKJACK) {
            //console stuff
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter H to hit or S to stand: ");
            char userInput = scanner.next().charAt(0);
            // if player wants to hit
            if (Character.toLowerCase(userInput) == 'h') {
                System.out.println("Player Hit");
                //give the player a new card
                playerHand.givePlayerCard(deck);
                //calculate their new score
                playerCardScore = playerHand.calculateScore();
                //if that hit caused them to bust
                if (playerCardScore > BLACKJACK) {
                    System.out.println("PLAYER BUST");
                    break;
                }
                System.out.println("Player score: " + playerCardScore);
                //if the player wants to stand
            } else if (Character.toLowerCase(userInput) == 's' || playerCardScore > BLACKJACK) {
                System.out.println("Player Stand");
                //break ends the loop or their turn
                break;
            }
        }
        displayScores();
    }
    //dealer algorithm
    public void dealerTurn() {
        //while the dealer card score is less than the player cards score
        //dealer cut off is because the dealer has to hit if their score is below 17
        while (dealerCardScore < playerCardScore&& dealerCardScore <= DEALER_CUT_OFF) {
            //while it's less than we need a card so give dealer the card
            dealerHand.givePlayerCard(deck);
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



