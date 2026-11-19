package io.github.OOprojektG10.model;

public interface playerState {
    public String get_stateString(Player player);

    // changes the players state to folded so the model knows it isn't playing more
    // of this hand
    public void fold(Player player);

    // calls the change_chip_count funktion and changes the state of the player to
    // call
    public int call(Player player, int bet);

    public void check(Player player);

    // calls the change_chip_count funktion and changes the state of the player to
    // raise
    public int raise(Player player, int bet);
}
