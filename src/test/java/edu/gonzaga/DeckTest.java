package edu.gonzaga;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DeckTest {
    @Test
    void testNumberOfCards() {
        Deck deck = new Deck();
        int expectedCardCount = 52;
        int actualCardCount = 0;
        for (Card card : deck.cards) {
            actualCardCount++;
        }
        assertEquals(expectedCardCount,actualCardCount);
    }
}
