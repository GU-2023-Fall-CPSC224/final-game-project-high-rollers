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
 public static final int BLACKJACK = 21;


 public Round(){
  System.out.println("created round");
  deck.shuffleDeck();
  playerHand.getPlayerCard(deck);
  dealerHand.getDealerCard(deck);
  playerHand.getPlayerCard(deck);
  dealerHand.getDealerCard(deck);
  playerCardScore = playerHand.calculateScore();
  dealerCardScore = dealerHand.calculateScore();
 }


 public void playRound(){
  // Check for Blackjack at the beginning
  if (playerHand.isBlackJack() && !dealerHand.isBlackJack()) {
   System.out.println("PLAYER WINS BLACKJACK");
   displayScores();
   playerWin = true;
   return; // Exit the method
  } else if (!playerHand.isBlackJack() && dealerHand.isBlackJack()) {
   System.out.println("DEALER WINS BLACKJACK");
   displayScores();
   dealerWin = true;
   return; // Exit the method
  }

  // Continue with the game
  while (!dealerWin || !playerWin) {
   displayScores();

   Scanner scanner = new Scanner(System.in);
   System.out.print("Enter H to hit or S to stand: ");
   char userInput = scanner.next().charAt(0);

   if (Character.toLowerCase(userInput) == 'h') {
    playerHand.getPlayerCard(deck);
    System.out.println("Player Hit");
    playerCardScore = playerHand.calculateScore();
    if (playerCardScore > BLACKJACK) {
     System.out.println("PLAYER BUST");
     dealerWin = true;
    }
   } else if (Character.toLowerCase(userInput) == 's') {
    System.out.println("Player Stand");

    // Dealer's turn
    while (dealerCardScore < BLACKJACK && dealerCardScore != playerCardScore) {
     dealerHand.getPlayerCard(deck);
     System.out.println("Dealer Hit");
     dealerCardScore = dealerHand.calculateScore();
     if (dealerCardScore > BLACKJACK) {
      System.out.println("DEALER BUST");
      playerWin = true;
     }
    }
    if (dealerCardScore <= BLACKJACK && dealerCardScore >= playerCardScore) {
     dealerWin = true;
    }
    else {
     playerWin = true;
    }
    break; // Exit the loop
   }
   // Avoid closing the scanner in the loop to prevent issues
  }
  displayScores();
  announceWinner();


 }
 private void displayScores() {
  System.out.println("Player score: " + playerCardScore);
  System.out.println("Dealer score: " + dealerCardScore);
 }
 private void announceWinner() {
  if (playerWin) {
   System.out.println("PLAYER WINS!");
  } else if (dealerWin) {
   System.out.println("DEALER WINS!");
  } else {
   System.out.println("It's a TIE!");
  }
 }

 }

