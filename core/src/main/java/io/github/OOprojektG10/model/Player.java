package io.github.OOprojektG10.model;

import io.github.OOprojektG10.model.playerstates.Unplayed;

public class Player {
    private int chip_count;

    protected Card[] hand = null;


    //private static String[] moves = { "Fold", "Call", "Check", "Raise", "Out" }; Replaced with ENUM
    private Move last_move = null;

    playerState state;

    // Create the player with a hand and a starting chip count
    public Player(int _chip_count) {
        chip_count = _chip_count;
        set_state(new Unplayed());
    }

    // give the Player a new hand of cards for the next round of play
    public void set_hand(Card c1, Card c2) {
        hand = new Card[]{c1, c2};
    }

    public void set_state(playerState state){ this.state = state; }
    public String get_stateString() { return state.get_stateString(this); }

    public void set_last_move(Move move){ last_move = move; }

    // changes the players state to folded so the model knows it isn't playing more
    // of this hand
    public void fold() {
        state.fold(this);
    }

    // calls the change_chip_count funktion and changes the state of the player to
    // call
    public int call(int bet) {
        return state.call(this, bet);
    }

    public void check() {
        state.check(this);
    }

    // calls the change_chip_count funktion and changes the state of the player to
    // raise
    public int raise(int bet) {
        return state.raise(this, bet);
    }

    // check so that you have enough chips for the bet and handles if you dont
    public void change_chip_count(int bet, boolean add) {
        if (!add) {
            if (chip_count - bet >= 0) {
                chip_count -= bet;
            } else {
                chip_count = 0;
                last_move = Move.OUT;
            }
        } else {
            chip_count += bet;
        }

        // add logic for when you call but don't have enough chips to match the previous
        // bet?

        // add logic for error handling to large of a bet
    }

    // returns the hand of the player
    public Card[] get_hand() {
        return hand;
    }

    // returns the state of the player
    public Move get_lastmove() {
        return last_move;
    }

    public int get_chipCount() {
        return chip_count;
    }

    public void add_chips(int chips) {
        if (chips < 0){
            throw new IllegalArgumentException("Cannot add a negative number of chips: " + chips);
        }
        else{
            change_chip_count(chips, true);
        }

    }


}
