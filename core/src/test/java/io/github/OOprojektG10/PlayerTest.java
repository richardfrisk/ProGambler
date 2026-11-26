package io.github.OOprojektG10;

import io.github.OOprojektG10.model.Move;
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
        assertEquals(player.get_lastmove(), Move.FOLD);
    }

    @Test
    void testCall() {
        player.call(200);
        assertEquals(player.get_chipCount(), 2800);

        player.call(3000);
        assertEquals(player.get_chipCount(), 0);
        assertEquals(player.get_lastmove(), Move.CALL);
    }

    @Test
    void testRaiseBy(){
        player.raiseBy(200);
        assertEquals(player.get_chipCount(), 2800);

        player.raiseBy(3000);
        assertEquals(player.get_chipCount(), 0);

        assertEquals(player.get_lastmove(), Move.RAISE);
    }

    @Test
    void testCheck(){
        player.check(0);
        assertEquals(player.get_lastmove(), Move.CHECK);
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
        Card c1 = deck.drawCard(); Card c2 = deck.drawCard();
        Card[] Hand = new Card[]{c1, c2};
        player.set_hand(c1, c2);
        assertArrayEquals(player.get_hand(), Hand);


    }

}
