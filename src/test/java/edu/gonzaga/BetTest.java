package edu.gonzaga;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BetTest {
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
