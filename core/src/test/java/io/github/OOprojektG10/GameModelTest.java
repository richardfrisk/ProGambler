package io.github.OOprojektG10;

import io.github.OOprojektG10.model.Deck;
import io.github.OOprojektG10.model.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameModelTest {
    GameModel dealer;
    @BeforeEach
    void setUp() {
        dealer = new GameModel(1000, new Deck(), 5);
    }

    @Test
    void testSetChipsOnTableToZero(){
        dealer.bet = 10; dealer.pot = 10; dealer.prePot = 10;

        dealer.setChipsOnTableToZero();

        assertEquals(dealer.pot, 0);
        assertEquals(dealer.bet, 0);
        assertEquals(dealer.prePot, 0);
    }
}
