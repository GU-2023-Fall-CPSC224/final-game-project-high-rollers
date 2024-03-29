package edu.gonzaga;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BetTest {

    @Test
    void testGamingMode() {
        // if checkbox is enabled for modifiedpayout;
        Bet bet = new Bet();
        bet.setPayoutGamingMode();
        bet.addBet(500);

        bet.betWin();
        assertEquals(4000, bet.getBankRollAmount());

    }
    @Test
    void testAddBet() {
        Bet bet = new Bet();

        bet.addBet(500);
        //bet.addBet(1000);
        //bet.addBet(2000);

        assertEquals(500, bet.getBetValue());
        //assertEquals(1000, bet.getBetValue());
        //assertEquals(0, bet.getBetValue());

    }

    @Test
    void testBetWin() {
        Bet bet = new Bet();
        bet.addBet(500);

        bet.betWin();
        assertEquals(2750, bet.getBankRollAmount());

    }
    @Test
    void testDiffWin() {
        Bet bet = new Bet();
        bet.addBet(2000);

        //bet.betLoss();
        bet.betWin();
        //assertEquals(0, bet.getBankRollAmount());
        assertEquals(5000, bet.getBankRollAmount());
    }

    @Test
    void testBetLoss() {
        Bet bet = new Bet();
        bet.addBet(1000);

        bet.betLoss();
        assertEquals(1000, bet.getBankRollAmount());
    }

    @Test
    void testGetPayoutAmount() {
        Bet bet = new Bet();
        bet.addBet(750);

        assertEquals(1125.0, bet.getPayout());
    }
}
