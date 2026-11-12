package io.github.OOprojektG10;

import javax.smartcardio.Card;

public class Player {
    protected int chip_count;
    protected Card[] hand;
    private String[] states = { "Fold", "Call", "Check", "Raise" };
    private String state = null;

    // Create the player with a hand and a starting chip count
    public Player(int _chip_count, Card[] _hand) {
        chip_count = _chip_count;
        set_hand(_hand);

    }

    // give the Player a new hand of cards for the next round of play
    public set_hand(Card[] _hand){
        hand = _hand;
    }

    // changes the players state to folded so the model knows it isn't playing more
    // of this hand
    public fold(){
        state = states[0];
    }

    // calls the change_chip_count funktion and changes the state of the player to
    // call
    public int call(int bet) {
        change_chip_count(bet);
        state = states[1];
        return chip_count;
    }

    public check(){
        state = states[2];
    }

    // calls the change_chip_count funktion and changes the state of the player to
    // raise
    public int raise(int bet) {
        change_chip_count(bet);
        state = states[3];
        return chip_count;
    }

    // check so that you have enough chips for the bet and handles if you dont
    private void change_chip_count(int bet) {
        if (chip_count - bet >= 0) {
            chip_count = -bet;
        } else if (state == states[1]) {
            // add logic for when you call but don't have enough chips to match the previous
            // bet
        }
        // add logic for error handling to large of a bet
    }

    // returns the hand of the player
    public Card[] get_hand() {
        return hand;
    }

    // returns the state of the player
    public String get_state() {
        return state;
    }

}
