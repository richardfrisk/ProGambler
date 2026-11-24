package io.github.OOprojektG10.model;

import java.util.HashMap;
import java.util.Map;

public class Card {

    private final String rank;
    private final String suit;

    private final HashMap<String, Integer> rankValueMap = new HashMap<>(Map.ofEntries(
        Map.entry("2", 2),
        Map.entry("3", 3),
        Map.entry("4", 4),
        Map.entry("5", 5),
        Map.entry("6", 6),
        Map.entry("7", 7),
        Map.entry("8", 8),
        Map.entry("9", 9),
        Map.entry("10", 10),
        Map.entry("J", 11),
        Map.entry("Q", 12),
        Map.entry("K", 13),
        Map.entry("A", 14)
    ));


    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int translateRankToInt(){
        try {
            return rankValueMap.get(this.rank);
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public String toString() {
        return rank + " + " + suit;   // vet inte hur vi vill ha det för format
    }
}

//skapa unitester

