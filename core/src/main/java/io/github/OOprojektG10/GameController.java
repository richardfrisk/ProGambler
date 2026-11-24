package io.github.OOprojektG10;

import model.gamemodel;

public class GameController implements GameObserver {

    private gamemodel model;

    public GameController(gamemodel model) {
        this.model = model;
        model.addObserver(this); // Förutsätter att gamemodell implementerar addObserver
    }

    public void playerCalls() {

    }

    public void playerRaises(int amount) {

    }

    public void playerFolds() {

    }

    @Override      // DENNA SWITCH SATSEN SKA UPPDATERA GAMEVIEW SENARE ÄR TANKEN
    public void onGameUpdated(GameEvent event) {
        switch (event){
            case PLAYER_RAISED:
                System.out.println("Spelare raisar");
                break;

            case PLAYER_CALLED:
                System.out.println("Spelare callar");
                break;

            case PLAYER_FOLDED:
                System.out.println("Spelare foldar");
                break;

            case GAME_ENDED:
                System.out.println("Spelet är över");
                break;

            case ROUND_CHANGED:
                System.out.println("Ny runda");
                break;
        }

    }
}
