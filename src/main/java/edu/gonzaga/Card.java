package edu.gonzaga;

public class Card {
    private Boolean dealerHand;
    private Boolean playerHand;
    private Boolean discard;
    private String suit;
    private Integer value;
    private String cardName;


    public Card(Integer valueSet, String suitSet, String cardNameSet){
        dealerHand = false;
        playerHand = false;
        discard = false;
        suit = suitSet;
        value = valueSet;
        cardName = cardNameSet;
    }

    public String getCardName(){
        return cardName;
    }



    public void setDealerHand(Boolean set){
        dealerHand = set;
    }
    public void setPlayerHand(Boolean set){
        playerHand = set;
    }
    public void setDiscard(Boolean set){
        discard = set;
    }
    public Boolean getDealerHand(){
        return dealerHand;
    }
    public Boolean getPlayerHand(){
        return playerHand;
    }
    public Boolean getDiscard(){
        return discard;
    }
    public String getSuit(){
        return suit;
    }
    public Integer getValue(){
        return value;
    }

    @Override
    public String toString() {
        return "Card{" +
                "dealerHand=" + dealerHand +
                ", playerHand=" + playerHand +
                ", discard=" + discard +
                ", suit='" + suit + '\'' +
                ", value=" + value +
                '}';
    }
    public String getCardImage() {
        if(value == 1){
            System.out.println("ace_of_" + suit + "s.png");
            return value + "_of_" + suit + "s.png";
        }
        else if(value <= 10){
            System.out.println(value + "_of_" + suit + "s.png");
            return value + "_of_" + suit + "s.png";
        }
        else if(value == 11){
            System.out.println("jack_of_" + suit + "s.png");
            return "jack_of_" + suit + "s.png";
        }
        else if(value == 12){
            System.out.println("queen_of_" + suit + "s.png");
            return "queen_of_" + suit + "s.png";
        }
        else if(value == 13){
            System.out.println("king_of_" + suit + "s.png");
            return "king_of_" + suit + "s.png";
        }
        return "";
    }
}
