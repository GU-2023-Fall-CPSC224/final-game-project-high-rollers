package edu.gonzaga;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerAndDealerTest {

    @Test
    void testScoringFalse(){
        Player player = new Player("Jerry");

        player.setInitialScore();
        player.setScore(5);
        player.setScore(10);

        assertFalse(player.checkBust());

    }
    @Test
    void testScoringTrue(){
        Player player = new Player("Jerry");

        player.setInitialScore();
        player.setScore(5);
        player.setScore(10);
        player.setScore(9);

        assertFalse(player.checkBust());
    }
}
