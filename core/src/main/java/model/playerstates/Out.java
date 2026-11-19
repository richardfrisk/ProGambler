package model.playerstates;

import model.Player;
import model.playerState;

public class Out implements playerState {
    @Override
    public String get_stateString(Player player) {
        return "OUT";
    }

    @Override
    public void fold(Player player) {

    }

    @Override
    public int call(Player player, int bet) {
        return -1;
    }

    @Override
    public void check(Player player) {

    }

    @Override
    public int raise(Player player, int bet) {
        return -1;
    }
}
