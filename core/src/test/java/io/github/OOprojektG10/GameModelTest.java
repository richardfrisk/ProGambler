package io.github.OOprojektG10;

import io.github.OOprojektG10.model.Deck;
import io.github.OOprojektG10.model.GameModel;
import io.github.OOprojektG10.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameModelTest {
    GameModel dealer;
    ArrayList<Player> players = new ArrayList<>(
        List.of(new Player(1000), new Player(1000),
            new Player(1000), new Player(1000), new Player(1000))
    );

    @BeforeEach
    void setUp() {
        dealer = new GameModel(1000, new Deck(), players);
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
