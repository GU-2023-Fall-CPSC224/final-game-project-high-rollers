package edu.gonzaga;

public class Round {
 Player player = new Player();
 Dealer dealer = new Dealer();
 Deck deck = new Deck();
 Hand playerHand = new Hand();
 Hand dealerHand = new Hand();

 public Round(){
  System.out.println("created round");
  deck.shuffleDeck();
  //deck.givePlayerCard();
  deck.giveDealerCard();
 // deck.givePlayerCard();
  deck.giveDealerCard();
  System.out.print("PLAYER CARDS: ");
  deck.printPlayerCards();

 }
}
