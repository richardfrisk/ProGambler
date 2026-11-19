package io.github.OOprojektG10.model;

import io.github.OOprojektG10.model.playerstates.Unplayed;

import java.util.ArrayList;

public class GameModel {

    /* Innehåller pot, kortleken, lyssnar på controller efter knapptryck och botarnas drag
funktioner: Deala korten, pull card from kortlek
spellogik: dvs spelets gång, vem som vinner, när man går bust*/
    public int pot;
    public int prePot; //den mängd chips som läggs till i poten i slutet av rundan.
    public int bet;

    protected ArrayList<Card> river = new ArrayList<Card>();
    private Deck deck;

    protected ArrayList<Player> players; //= new ArrayList<Player>();

    public GameModel(int Starting_Chips, Deck deck, ArrayList<Player> players){
        setChipsOnTableToZero();

        this.deck = deck;
        this.players = players;
    }

    public int activePlayers(ArrayList<Player> players){
        int count = 0;
        for (Player p : players){
            if (p.get_stateString().equals("Played") || p.get_stateString().equals("Unplayed")){
                count++;
            }
        }
        return count;
    }

    public Player getWinner(ArrayList<Player> players){
        return new Player(1010);
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
        //pay big/small blind
        PlayRound();
        NextRound();
    }

    private void NextRound(){
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

    private void PlayRound(){
        if (activePlayers(this.players) == 1){
            EndGame();
        } else {
            while (!haveAllPlayersPlayed(this.players)) {
                //current player call/raise/fold -> call the respective method

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
        //give pot the winning player. The only player left without the state "Fold" or "Busted"

        setChipsOnTableToZero();
        river.clear();
        for (Player player : this.players) {
            if (player.get_stateString().equals("OUT")){ continue; }
            player.set_state(new Unplayed());
        }
        StartGame(new Deck());
    }
}
