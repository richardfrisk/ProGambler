package io.github.OOprojektG10;

import io.github.OOprojektG10.controller.GameEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameEventTest {

    @Test
    void testGameEventsExist() {
        assertEquals(5, GameEvent.values().length);
    }

    @Test
    void testPlayerRaisedEventExists() {
        assertNotNull(GameEvent.PLAYER_RAISED);
    }

    @Test
    void testGameEndedEventExists() {
        assertNotNull(GameEvent.GAME_ENDED);
    }

    @Test
    void testPlayerFoldedEventExists(){
        assertNotNull(GameEvent.PLAYER_FOLDED);
    }

    @Test
    void testPlayerCalledEventExists(){
        assertNotNull(GameEvent.PLAYER_CALLED);
    }

    @Test
    void testPlayerRoundChangedEventExists(){
        assertNotNull(GameEvent.ROUND_CHANGED);
    }
}
