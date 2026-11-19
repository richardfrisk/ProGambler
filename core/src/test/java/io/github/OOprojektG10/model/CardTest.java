package io.github.OOprojektG10.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void testGetRank() {
        Card card = new Card("A", "Hearts");
        assertEquals("A", card.getRank());
    }

    @Test
    void testGetSuit() {
        Card card = new Card("A", "Hearts");
        assertEquals("Hearts", card.getSuit());
    }

    @Test
    void testToString() {
        Card card = new Card("A", "Hearts");
        assertEquals("A + Hearts", card.toString());
    }

}

//   ./gradlew :core:cleanTest :core:test