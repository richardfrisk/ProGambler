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
        assertEquals(player.last_move, "Fold");
    }

    @Test
    void testChangeChipCount() {
        player.change_chip_count(200, false);
        assertEquals(player.get_chipCount(), 2800);

        player.change_chip_count(3000, false);
        assertEquals(player.get_chipCount(), 0);

        player.change_chip_count(200, true);
        assertEquals(player.get_chipCount(), 200);
    }

}
