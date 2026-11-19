package io.github.OOprojektG10.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

class DeckTest {

    @Test
    void testDeckStartsWith52Cards() {
        Deck deck = new Deck();
        assertEquals(52, deck.getCardsRemaining());
    }

    @Test
    void testDrawCard() {
        Deck deck = new Deck();

        Card card = deck.drawCard();

        assertNotNull(card);
        assertEquals(51, deck.getCardsRemaining());
    }

    @Test
    void testDrawAllCards() {
        Deck deck = new Deck();

        for (int i = 0; i < 52; i++) {
            assertNotNull(deck.drawCard());
        }

        assertEquals(0, deck.getCardsRemaining());
    }

    @Test
    void testDrawCardFromEmptyDeck() {
        Deck deck = new Deck();

        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        assertThrows(IllegalStateException.class, () -> deck.drawCard());
    }

    @Test
    void testShuffle() {
        Deck deck = new Deck();

        deck.shuffle();

        assertEquals(52, deck.getCardsRemaining());
    }

    @Test
    void testAllCardsAreUnique() {
        Deck deck = new Deck();
        Set<String> cards = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            Card card = deck.drawCard();
            cards.add(card.getRank() + "-" + card.getSuit());
        }

        assertEquals(52, cards.size());
    }

    @Test
    void testDeckHasCorrectSuits() {
        Deck deck = new Deck();
        Set<String> suits = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            suits.add(deck.drawCard().getSuit());
        }

        assertEquals(4, suits.size());
        assertTrue(suits.contains("Hearts"));
        assertTrue(suits.contains("Diamonds"));
        assertTrue(suits.contains("Clubs"));
        assertTrue(suits.contains("Spades"));
    }
}







//   ./gradlew :core:cleanTest :core:test