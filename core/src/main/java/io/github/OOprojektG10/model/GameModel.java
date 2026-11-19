package io.github.OOprojektG10.model;

import io.github.OOprojektG10.model.playerstates.Unplayed;

import java.util.ArrayList;
import java.util.Random;

public class GameModel {

    /* Innehåller pot, kortleken, lyssnar på controller efter knapptryck och botarnas drag
funktioner: Deala korten, pull card from kortlek
spellogik: dvs spelets gång, vem som vinner, när man går bust*/
    public int pot;
    public int prePot; //den mängd chips som läggs till i poten i slutet av rundan.
    public int bet;
    private int blindCoinPos;
    private int bigBlind;

    protected ArrayList<Card> river = new ArrayList<Card>();
    private Deck deck;

    protected ArrayList<Player> players; //= new ArrayList<Player>();

    public GameModel(int Starting_Chips, Deck deck, ArrayList<Player> players, int initialBlind){
        setChipsOnTableToZero();
        blindCoinPos = new Random().nextInt(players.size());
        this.bigBlind = initialBlind;

        this.deck = deck;
        this.players = players;
    }

    public int activePlayersCount(ArrayList<Player> players){
        int count = 0;
        for (Player p : players){
            if (p.get_stateString().equals("Played") || p.get_stateString().equals("Unplayed")){
                count++;
            }
        }
        return count;
    }

    private int getHandValue(Card[] hand){
        // TODO: Hitta på ett sätt att evaluera hand värdet
        return 0;
    }

    public Player getWinner(ArrayList<Player> players){
        Player winningPlayer = null;
        for (Player p1 : players){
            for (Player p2 : players) {
                if (p1.isActive() && p2.isActive() && !p1.equals(p2)){
                    if (getHandValue(p1.get_hand()) < getHandValue(p2.get_hand())) {
                        winningPlayer = p2;
                    } else if (getHandValue(p1.get_hand()) > getHandValue(p2.get_hand())){
                        winningPlayer = p1;
                    } else {
                        p1.add_chips(pot/2);
                        p2.add_chips(pot/2);
                    }
                }
            }
        }
        return winningPlayer;
    }

    private void dealCards(){
        for (Player player : this.players){
            if (!player.get_stateString().equals("OUT")){
                Card c1 = this.deck.drawCard();
                Card c2 = this.deck.drawCard();

                player.set_hand(c1, c2);

            }
        }
    }

    private static Card drawCard(Deck d){
        return d.drawCard();
    }

    public void setChipsOnTableToZero(){
        pot = 0;
        prePot = 0;
        bet = 0;
    }

    private void StartGame(Deck deck){
        deck.shuffle();
        dealCards();
        setChipsOnTableToZero();
        StartRound();
    }

    private void StartRound(){
        payBlinds();
        PlayRound();
        NextRound();
    }

    private void payBlinds() {
        players.get(blindCoinPos).pay_chips(bigBlind);
        players.get(blindCoinPos -1).pay_chips(bigBlind/2);
    }

    private void NextRound(){
        moveBlindCoin();
        if (river.isEmpty()){
            for (int i = 0; i < 2; i++) {
                river.add(drawCard(this.deck));
            }
            PlayRound();
            NextRound();
        } else if (river.size() < 5) {
            river.add(drawCard(this.deck));
            PlayRound();
            NextRound();
        } else if (river.size() == 5) {
            PlayRound();
            EndGame();
        }
    }

    private void moveBlindCoin() {
        blindCoinPos++;
        blindCoinPos = blindCoinPos % players.size();
    }

    private void increaseBlind(int chips){
        bigBlind += chips;
    }

    private void PlayRound(){
        if (activePlayersCount(this.players) == 1){
            EndGame();
        } else {
            while (!haveAllPlayersPlayed(this.players)) {
                //TODO: current player call/raise/fold -> call the respective method

            }
            pot += prePot;
            prePot = 0;
            bet = 0;
        }
    }

    private Boolean haveAllPlayersPlayed(ArrayList<Player> ps){
        for (Player p : ps){
            if (p.get_stateString().equals("Unplayed")){
                return false;
            }
        }
        return true;
    }

    private void EndGame(){
        //give pot the winning player. The only player left without the state "Fold" or "Busted" or with best hand
        try {
            getWinner(players).add_chips(pot);
        } catch (NullPointerException ignored) { }

        setChipsOnTableToZero();
        river.clear();
        for (Player player : this.players) {
            if (player.get_stateString().equals("OUT")){ continue; }
            player.set_state(new Unplayed());
        }
        StartGame(new Deck());
    }

    /* -- DEPRECATED --
    private static void nextTurn(){
        // Byter turen till nästa spelarens tur i listan med ospelade turer.
        //Player klassen kan ha en state som heter "played" vilket indikerar till GameModel när rundan är över.
        //När alla spelare har en annan state än "unplayed", gå till nästa runda.
    }

    private static void playedPlayersToUnplayed(){
        //om en spelare raisar betten omvandla samtliga spelare med staten "played" till "unplayed".
    }



    private static void aPlayerRaised(){
        //Sätt det nya bettet som måste callas/raisas och lägg till i prePot.
        nextTurn();
    }

    private static void aPlayerCalled(){
        //lägg till bettet i prePot
        nextTurn();
    }

    private static void aPlayerFolded(){
        //sätt dess players state till "folded"
        nextTurn();
    }
*/
}
