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
        dealer = new GameModel(1000, new Deck(), players, 100);
    }

    @Test
    void testSetChipsOnTableToZero(){
        dealer.currentBet = 10; dealer.pot = 10; dealer.contributions = 10;

        dealer.setChipsOnTableToZero();

        assertEquals(dealer.pot, 0);
        assertEquals(dealer.currentBet, 0);
        assertEquals(dealer.contributions, 0);
    }
}
