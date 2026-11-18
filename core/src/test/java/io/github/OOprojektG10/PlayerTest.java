package io.github.OOprojektG10;

import io.github.OOprojektG10.model.Player;
import io.github.OOprojektG10.model.Deck;
import io.github.OOprojektG10.model.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;
    Deck deck;
    @BeforeEach
    void setUp() {
        player = new Player(3000);
        deck = new Deck();
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

    @Test
    void testAddChips(){
        player.add_chips(200);
        assertEquals(player.get_chipCount(), 3200);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,() -> player.add_chips(-200));
        assertTrue(e.getMessage().contains("negative"));
        assertEquals(3200, player.get_chipCount());
    }

    @Test
    void testHand(){
        Card[] Hand = new Card[]{deck.drawCard(), deck.drawCard()};
        player.set_hand(Hand);
        assertEquals(player.get_hand(), Hand);



    }

}
