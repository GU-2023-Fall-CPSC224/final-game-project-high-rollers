package edu.gonzaga;

/**
 * This class is used to control and keep the state of the players betting amount called the bankRoll.
 */
public class Bet {
    private int bankRoll;
    double betValue = 0;
    double payout = 0;
    double payoutRateNormal = 1.5;
    public Bet(){
        // default value of the players Bankroll at the start of the game.
        bankRoll = 2000;
    }

    public Bet(int customValue) {
        bankRoll = customValue;
    }

    /**
     * Used to take in the betting amount that the player has chosen and takes it out of the bankRoll total amount and stores the value amount in a variable
     * @param betValue holds the integer value of the bet amount.
     */
    public void addBet(Integer betValue){
        // used to assign the state the betValue with the parameter
        this.betValue = betValue;

        System.out.println(payoutRateNormal);
        payout = payoutRateNormal * betValue;
        System.out.println(payout);

        System.out.println("Possible payout is: " + payout);
        betValue = 0;
    }

    public void setBetValue() {
        betValue = 0;
    }
    public double getBetValue() {
        return betValue;
    }

    public void betLoss() {
        bankRoll -= (int) betValue;
    }

    public double getPayout(){
        return payout;
    }

    public void setPayoutGamingMode(){
        payoutRateNormal = 4;
    }

    /**
     * If the round results in a tie, then the bankRoll is reset to the amount at the start of the round before the bet
     */
    public void resetBet() {
        bankRoll += betValue;
    }


    /**
     * If the player wins the round, they win the bet and their bankRoll gets the value of the bet which was stored in the betValue field.
     */
    public void betWin(){
        bankRoll += (int) (payoutRateNormal * betValue);
    }

    /**
     * Used to return the value of the players bankRoll
     * @return
     */
    public Integer getBankRollAmount(){
        return bankRoll;
    }
}
