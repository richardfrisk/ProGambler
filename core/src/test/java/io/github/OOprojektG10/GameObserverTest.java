package io.github.OOprojektG10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameObserverTest {

    @Test
    void testObserverReceivesEvent() {
        GameEvent[] receivedEvent = new GameEvent[1];

        GameObserver observer =
            event -> receivedEvent[0] = event;

        observer.onGameUpdated(GameEvent.PLAYER_RAISED);

        assertEquals(
            GameEvent.PLAYER_RAISED,
            receivedEvent[0]
        );
    }
}
