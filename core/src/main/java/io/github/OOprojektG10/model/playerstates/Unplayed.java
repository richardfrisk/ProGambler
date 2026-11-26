package io.github.OOprojektG10.model.playerstates;

import io.github.OOprojektG10.model.Move;
import io.github.OOprojektG10.model.Player;
import io.github.OOprojektG10.model.playerState;

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
        player.set_state(new Folded());
    }

    // calls the change_chip_count funktion and changes the state of the player to
    // call
    @Override
    public int call(Player player, int bet) {
        player.set_last_move(Move.CALL);
        player.set_state(new Played());
        return player.pay_chips(bet - player.contribution);
    }

    @Override
    public void check(Player player, int bet) {
        if (player.contribution == bet) {
            player.set_last_move(Move.CHECK);
            player.set_state(new Played());
        }

    }

    // calls the change_chip_count funktion and changes the state of the player to
    // raiseBy
    @Override
    public int raiseBy(Player player, int bet) {
        player.set_last_move(Move.RAISE);
        player.set_state(new Played());
        return player.pay_chips(bet);
    }
}
