package io.github.OOprojektG10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;
    @BeforeEach
    void setUp() {
        player = new Player(3000);
    }

    @Test
    void testFold() {
        player.fold();
        assertEquals(player.get_lastmove(), "Fold");
    }

    @Test
    void testCall() {
        player.call(200);
        assertEquals(player.get_chipCount(), 2800);

        player.call(3000);
        assertEquals(player.get_chipCount(), 0);
        assertEquals(player.get_lastmove(), "Call");
    }

    @Test
    void testRaise(){
        player.raise(200);
        assertEquals(player.get_chipCount(), 2800);

        player.raise(3000);
        assertEquals(player.get_chipCount(), 0);

        assertEquals(player.get_lastmove(), "Raise");
    }

    @Test
    void testCheck(){
        player.check();
        assertEquals(player.get_lastmove(), "Check");
    }

}
