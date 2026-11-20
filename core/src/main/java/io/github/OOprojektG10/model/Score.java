package io.github.OOprojektG10.model;

public class Score {
    private final int weight;
    private final String name;
    private final int value;

    Score(String n, int w, int v) {
        weight = w;
        name = n;
        value = v;
    }

    @Override
    public String toString() {
        return getName();
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
