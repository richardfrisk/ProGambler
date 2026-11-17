package model;

public class gamemodel {

    /* Innehåller pot, kortleken, lyssnar på controller efter knapptryck och botarnas drag
funktioner: Deala korten, pull card from kortlek
spellogik: dvs spelets gång, vem som vinner, när man går bust*/
    private int pot;
    private int prePot; //den mängd chips som läggs till i poten i slutet av rundan.
    private int bet;

    private Card[] river = new Card[];
    private Deck deck;

    private Player[] players;

    public gamemodel(int Starting_Chips, Deck deck, int player_count){
        setChipsOnTableToZero();


    }

    private static void dealCards(){
        //Dela ut kort till samtliga ACTIVA spelare
        //använd drawCard()
    }

    private static Card drawCard(Deck d){
        return d.drawCard();
    }
/*
    private static void nextTurn(){
        // Byter turen till nästa spelarens tur i listan med ospelade turer.
        //Player klassen kan ha en state som heter "played" vilket indikerar till gamemodel när rundan är över.
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
    private void setChipsOnTableToZero(){
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
        if (river.length == 0){
            for (int i = 0; i < 2; i++) {
                river.add(drawCard(self.deck));
            }
            PlayRound();
            NextRound();
        } else if (river.length < 5) {
            river.add(drawCard(self.deck));
            PlayRound();
            NextRound();
        } else if (river.length == 5) {
            PlayRound();
            EndGame();
        }
    }

    private void PlayRound(){
        while (!haveAllPlayersPlayed(players)){
            //current player call/raise/fold -> call the respective method
        }
        pot += prePot;
        prePot = 0;
        bet = 0;
    }

    private Boolean haveAllPlayersPlayed(Player[] ps){
        for (Player p : ps){
            if (!p.get_lastmove().equals("Fold") || !p.get_lastmove().equals("Out")){
                return false;
            }
        }
        return true;
    }

    private void EndGame(){
        //give pot the winning player. The only player left without the state "Fold"
        setChipsOnTableToZero();
        river.clear();
        /*
        for player in players:
            if player.get_state == "busted"
                continue;
            player.set_state("unplayed")
         */
        StartGame(new Deck());
    }
}
