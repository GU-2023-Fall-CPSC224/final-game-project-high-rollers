/*
 * Final project main driver class
 * 
 * 
 * Project Description:
 * 
 * 
 * Contributors:
 * 
 * 
 * Copyright: 2023
 */
package edu.gonzaga;


/** Main program class for launching your team's program. */
public class MainGame {
    public static void main(String[] args) {
        System.out.println("Hello Team Game");
        playGame();
        //new BlackJackGUI();
        //new StartingScreenGUI();
        // Your code here. Good luck!
    }


    public static void playGame(){
        int playerWins = 0;
        int dealerWins = 0;
        //BIG REMINDER if winStatus is 0 == dealer won, 1 == tie, 2 == player won
        int winStatus;

        for(int i = 0; i < 10; i++){
            Round round = new Round();
            winStatus = round.playRound();
            if(winStatus == 2){
                playerWins++;
                System.out.println("PLAYER WIN");
            }
            else if (winStatus == 1){
                System.out.println("TIE");
            }
            else{
                dealerWins++;
                System.out.println("DEALER WIN");
            }
        }

        System.out.println("PLAYER WINS: " + playerWins);
        System.out.println("DEALER WINS: " + dealerWins);
    }
}
