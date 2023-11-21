package edu.gonzaga;

import java.util.Scanner;

public class Round {
    Player player = new Player();
    Dealer dealer = new Dealer();
    Deck deck = new Deck();
    Hand playerHand = new Hand();
    Hand dealerHand = new Hand();
    Boolean playerWin = false;
    Boolean dealerWin = false;
    int playerCardScore = 0;
    int dealerCardScore = 0;
    int dealerStartScore = 0;
    // 0 win status is dealer win, 1 win status is tie, 2 win status in player win.
    int winStatus;
    public static final int BLACKJACK = 21;


    public Round() {
        System.out.println("------------ NEW ROUND ----------");
        deck.shuffleDeck();
        playerHand.getPlayerCard(deck);
        dealerHand.getDealerCard(deck);
        dealerStartScore = dealerHand.calculateScore();
        playerHand.getPlayerCard(deck);
        dealerHand.getDealerCard(deck);
        playerCardScore = playerHand.calculateScore();
        dealerCardScore = dealerHand.calculateScore();
    }


    public int playRound() {
        // Check for Blackjack at the beginning
        if (playerHand.isBlackJack() && !dealerHand.isBlackJack()) {
            System.out.println("PLAYER WINS - BLACKJACK");
            displayScores();
            //return playerWin; // Exit the method
        } else if (!playerHand.isBlackJack() && dealerHand.isBlackJack()) {
            System.out.println("DEALER WINS - BLACKJACK");
            displayScores();
        }
        displayStartScores();
        // Continue with the game
        playerTurn();
        if (playerCardScore < 21) {
            dealerTurn();
        }
        if (dealerCardScore <= BLACKJACK && dealerCardScore > playerCardScore) {
            winStatus = 0;
        } else if (dealerCardScore == playerCardScore) {
            winStatus = 1;
        } else {
            winStatus = 2;
        }
        return winStatus;
    }

    private void displayScores() {
        System.out.println("Player score: " + playerCardScore);
        System.out.println("Dealer score: " + dealerCardScore);
    }

    private void displayStartScores() {
        System.out.println("Player score: " + playerCardScore);
        System.out.println("Dealer score: " + dealerStartScore + " + ?");
    }

    public void playerTurn() {

        while (playerCardScore < BLACKJACK) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter H to hit or S to stand: ");
            char userInput = scanner.next().charAt(0);
            if (Character.toLowerCase(userInput) == 'h') {
                playerHand.getPlayerCard(deck);
                System.out.println("Player Hit");
                playerCardScore = playerHand.calculateScore();
                if (playerCardScore > BLACKJACK) {
                    System.out.println("PLAYER BUST");
                    //dealerWin = true;
                    playerWin = false;
                    //return playerWin;
                }
                System.out.println("Player score: " + playerCardScore);
            } else if (Character.toLowerCase(userInput) == 's' || playerCardScore > BLACKJACK) {
                System.out.println("Player Stand");
                break;
            }
        }
        displayScores();
    }

    public void dealerTurn() {
        while (dealerCardScore < playerCardScore) {
            dealerHand.getPlayerCard(deck);
            System.out.println("Dealer Hit");
            dealerCardScore = dealerHand.calculateScore();
            if (dealerCardScore > BLACKJACK) {
                System.out.println("DEALER BUST");
                playerWin = true;
                break;
            }
        }
    }
}



