package edu.gonzaga;

public class Card {
    private Boolean dealerHand;
    private Boolean playerHand;
    private Boolean discard;
    private String suit;
    private Integer value;

    public Card(Integer valueSet, String suitSet){
        dealerHand = false;
        playerHand = false;
        discard = false;
        suit = suitSet;
        value = valueSet;
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
        if(value <= 10){
            System.out.println(value + "_of_" + suit + "s.png");
            return value + "_of_" + suit + "s.png";
        }
        return "";
    }
}
