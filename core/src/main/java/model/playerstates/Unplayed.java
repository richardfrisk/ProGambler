package model.playerstates;

import model.Move;
import model.Player;
import model.playerState;

public class Unplayed implements playerState {
    @Override
    public String get_stateString(Player player) {
        return "Unplayed";
    }

    // changes the players state to folded so the model knows it isn't playing more
    // of this hand
    @Override
    public void fold(Player player) {
        player.set_last_move(Move.FOLD);
    }

    // calls the change_chip_count funktion and changes the state of the player to
    // call
    @Override
    public int call(Player player, int bet) {
        player.change_chip_count(bet, false);
        player.set_last_move(Move.CALL);
        return player.get_chipCount();
    }

    @Override
    public void check(Player player) {
        player.set_last_move(Move.CHECK);
    }

    // calls the change_chip_count funktion and changes the state of the player to
    // raise
    @Override
    public int raise(Player player, int bet) {
        player.change_chip_count(bet, false);
        player.set_last_move(Move.RAISE);
        return player.get_chipCount();
    }
}
