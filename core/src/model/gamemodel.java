package model;

public class gamemodel {

    /* Innehåller pot, kortleken, lyssnar på controller efter knapptryck och botarnas drag
funktioner: Deala korten, pull card from kortlek
spellogik: dvs spelets gång, vem som vinner, när man går bust*/
    private int pot;
    private int prePot; //den mängd chips som läggs till i poten i slutet av rundan.
    private int bet;

    //private card river[];

    //private Player players[];
    //private Player ingame_players[];
    //private Player active_players[];
    //Använd state machines istället för högre betyg.


    private static void dealCards(){
        //Dela ut kort till samtliga ACTIVA spelare
        //använd pullCard()
    }

    private static void pullCard(){
        //dra kort från kortleken. Vet inte än om det kommer dras från ett objekt i denna klass eller från Kortleksklassen.
        //Använd denna i dealCards()
    }

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
        playedPlayersToUnplayed();
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

    private static void nextRound(){
        //när ingen spelare längre har staten "unplayed"
        //lägg till prePot summan till pot
        //
    }
}
