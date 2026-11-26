package io.github.OOprojektG10.model.playerstates;

import io.github.OOprojektG10.model.Player;
import io.github.OOprojektG10.model.playerState;

public class Folded implements playerState {
    @Override
    public String get_stateString(Player player) {
        return "Folded";
    }

    @Override
    public void fold(Player player) {

    }

    @Override
    public int call(Player player, int bet) {
        return -1;
    }

    @Override
    public void check(Player player, int bet) {

    }

    @Override
    public int raiseBy(Player player, int bet) {
        return -1;
    }
}
