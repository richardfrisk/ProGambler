package io.github.OOprojektG10.model;

import io.github.OOprojektG10.model.playerstates.Unplayed;

import java.util.*;

public class GameModel {

    /* Innehåller pot, kortleken, lyssnar på controller efter knapptryck och botarnas drag
funktioner: Deala korten, pull card from kortlek
spellogik: dvs spelets gång, vem som vinner, när man går bust*/
    private int pot;
    private int contributions; //den mängd chips som läggs till i poten i slutet av rundan.
    private int currentBet;
    private int blindCoinPos;
    private int bigBlind;
    private int currentPlayer;
    private final int dealerPos;

    private ArrayList<Card> river = new ArrayList<Card>();
    private Deck deck;

    private ArrayList<Player> players; //= new ArrayList<Player>();

    public GameModel(int Starting_Chips, Deck deck, ArrayList<Player> players, int initialBlind){
        setChipsOnTableToZero();
        blindCoinPos = new Random().nextInt(players.size());
        this.bigBlind = initialBlind;
        currentPlayer = (getBlindCoinPos() + 1) % players.size();
        dealerPos = getBlindCoinPos() - 2;

        this.deck = deck;
        this.players = players;
    }

    public ArrayList<Card> getRiver() {
        return this.river;
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

    private Score getHandValue(Card[] hand){
        ArrayList<Card> hand7 = new ArrayList<Card>(river);
        hand7.addAll(Arrays.asList(hand));

        Score bestHandValue = new Score("", 0, 0);

        for (int i = 0; i < hand7.size(); i++) {
            for (int j = i + 1; j < hand7.size(); j++) {
                ArrayList<Card> hand5 = new ArrayList<>();

                for (int k = 0; k < hand7.size(); k++) {
                    if (k != i && k != j) {
                        hand5.add(hand7.get(k));
                    }
                }

                Score handValue = handEval(hand5);
                if (handValue.getWeight() > bestHandValue.getWeight()){
                    bestHandValue = handValue;
                } else if (handValue.getValue() > bestHandValue.getValue()) {
                    bestHandValue = handValue;
                }
            }
        }
        return bestHandValue;
    }

    private Score handEval(ArrayList<Card> h) {
        ArrayList<Integer> ranks = new ArrayList<Integer>();


        HashMap<String, Integer> rankCounts = new HashMap<>(Map.ofEntries());
        ArrayList<Integer> ranksHighToLow = new ArrayList<>();

        for (Card c : h) {
            ranks.add(c.translateRankToInt());
            rankCounts.put(c.getRank(), (rankCounts.getOrDefault(c.getRank(), 0) + 1));

            int rank = c.translateRankToInt();

            if (!ranksHighToLow.contains(rank)) {
                ranksHighToLow.add(rank);
            }
        }
        Collections.sort(ranksHighToLow, Collections.reverseOrder());

        int handRankSum = 0;
        for (int i : ranks) {
            handRankSum += i;
        }

        boolean isFlush = true;
        int flushCount;
        String previousSuit = null;
        for (Card card : h) {
            if (previousSuit == null){
                previousSuit = card.getSuit();
                continue;
            }
            if (!previousSuit.equals(card.getSuit())) {
                isFlush = false;
            }
            previousSuit = card.getSuit();
        }

        int straightHigh = -1;
        if (rankCounts.size() == 5){
            if (ranks.contains(14) &&
                ranks.contains(2) &&
                ranks.contains(3) &&
                ranks.contains(4) &&
                ranks.contains(5))
            {
                straightHigh = 5;
            } else {
                int why = 0;
                for (int i = 0; i < ranksHighToLow.size() - 1; i++){
                    if (ranksHighToLow.get(i) == ranksHighToLow.get(i+1) + 1){
                        why++;
                    }
                }
                if (why == 4){
                    straightHigh = ranksHighToLow.get(0);
                }
            }
        }

        if (isFlush && straightHigh != -1){
            return new Score("STRAIGHT-FLUSH", 9, handRankSum);
        }

        if (rankCounts.containsValue(4)){
            return new Score("Four of a kind", 8, handRankSum);
        }

        if (rankCounts.containsValue(3) && rankCounts.containsValue(2)){
            return new Score("Full house", 7, handRankSum);
        }

        if (isFlush){
            return new Score("Flush", 6, handRankSum);
        }

        if (straightHigh != -1){
            return new Score("Straight", 5, handRankSum);
        }

        if (rankCounts.containsValue(3)) {
            return new Score("Three of a kind", 4, handRankSum);
        }

        ArrayList<Integer> kys = new ArrayList<>(rankCounts.values());

        if (kys.get(0) == 2 && kys.get(1) == 2 || kys.get(1) == 2 && kys.get(2) == 2 || kys.get(0) == 2 && kys.get(2) == 2 ){
            return new Score("Two pair", 3, handRankSum);
        }

        if (rankCounts.containsValue(2)){
            return new Score("Pair", 2, handRankSum);
        }

        return new Score("High card", 1, handRankSum);
    }

    public Player getWinner(ArrayList<Player> players){
        Player winningPlayer = null;
        for (int i = 0; i < players.size(); i++){
            for (int j = i + 1; j < players.size(); j++) {
                Player p1 = players.get(i);
                Player p2 = players.get(j);

                if (p1.isActive() && p2.isActive()){
                    winningPlayer = comparePlayerHands(p1, p2);
                }
            }
        }
        return winningPlayer;
    }

    private Player comparePlayerHands(Player p1, Player p2) {
        Player winningPlayer = null;
        if (getHandValue(p1.get_hand()).getWeight() < getHandValue(p2.get_hand()).getWeight()) {
            winningPlayer = p2;
        } else if (getHandValue(p1.get_hand()).getValue() > getHandValue(p2.get_hand()).getValue()){
            winningPlayer = p1;
        }
        return winningPlayer;
    }

    private void dealCards(){
        for (Player player : this.getPlayers()){
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
        contributions = 0;
        currentBet = 0;
    }

    private void StartGame(Deck deck){
        deck.shuffle();
        dealCards();
        setChipsOnTableToZero();
        moveBlindCoin();
        PlayRound();
    }

    private void payBlinds() {
        int smallBlindCoin = (getBlindCoinPos() -1);
        if (smallBlindCoin < 0){
            smallBlindCoin = getPlayers().size() - 1;
        }
        contributions = getContributions() + getPlayers().get(getBlindCoinPos()).pay_chips(getBigBlind());
        contributions = getContributions() + getPlayers().get(smallBlindCoin).pay_chips(getBigBlind() /2);
        currentBet = getBigBlind();
    }

    private void NextRound(){
        if (river.isEmpty()){
            for (int i = 0; i < 3; i++) {
                river.add(drawCard(this.deck));
            }
            playedPlayersToUnplayed();
            PlayRound();
        } else if (river.size() < 5) {
            river.add(drawCard(this.deck));
            playedPlayersToUnplayed();
            PlayRound();
        } else if (river.size() == 5) {
            EndGame();
        }
    }

    private void moveBlindCoin() {
        blindCoinPos = getBlindCoinPos() + 1;
        blindCoinPos = getBlindCoinPos() % getPlayers().size();
    }

    private void increaseBlind(int chips){
        bigBlind = getBigBlind() + chips;
    }

    private void PlayRound(){
        /*
        if (river.isEmpty()) {
            payBlinds();
            playedPlayersToUnplayed();
        } */

        if (activePlayersCount(this.getPlayers()) == 1) {
            pot = getPot() + getContributions();
            if (!getPlayers().get(getCurrentPlayer()).get_stateString().equals("Unplayed") &&
                !getPlayers().get(getCurrentPlayer()).get_stateString().equals("Played")){
                advancePlayer();
            }
            getPlayers().get(getCurrentPlayer()).add_chips(getPot());
            EndGame();
            return;
        }

        if (allPlayersHavePlayed(getPlayers())) {
            pot = getPot() + getContributions();
            currentPlayer = getDealerPos();
            if (!getPlayers().get(getCurrentPlayer()).get_stateString().equals("Unplayed") &&
                !getPlayers().get(getCurrentPlayer()).get_stateString().equals("Played")){
                advancePlayer();
            }
            NextRound();
            return;
        }

        if  (river.isEmpty()) {
            payBlinds();
            currentPlayer = getBlindCoinPos() + 1;
            currentPlayer = getCurrentPlayer() % getPlayers().size();
            if (!getPlayerStateString(getCurrentPlayer()).equals("Unplayed")){
                advancePlayer();
            }
        } else {
            for (Player player : getPlayers()) {
                player.contribution = 0;
                if (player.get_stateString().equals("Played")){
                    player.set_state(new Unplayed());
                }
            }
            currentPlayer = getDealerPos();
            currentBet = 0;
        }

    }

    public void act(Player caller, Move action, int raiseTo) {
        if (!caller.get_stateString().equals("Unplayed") || !caller.equals(getPlayers().get(getCurrentPlayer()))){
            return;
        }

        if (!getPlayerStateString(getCurrentPlayer()).equals("Unplayed")){
            advancePlayer();
        }
        Player p = getPlayers().get(getCurrentPlayer());

        int amountToCall = getCurrentBet() - p.contribution;

        switch (action){
            case FOLD -> {
                p.fold();
                advancePlayer();
            }
            case CHECK -> {
                if (amountToCall == 0){
                    p.check();
                    advancePlayer();
                } else {
                    act(caller, Move.CALL, raiseTo);
                }
            }
            case CALL -> {
                if (amountToCall == 0){ act(caller, Move.CHECK, raiseTo); }
                contributions = getContributions() + p.call(amountToCall);
                advancePlayer();
            }
            case RAISE -> {
                if (raiseTo > getCurrentBet()) {
                    playedPlayersToUnplayed();
                    contributions = getContributions() + p.raiseBy(raiseTo - p.contribution);
                    currentBet = p.contribution;
                    advancePlayer();
                } else {
                    throw new RuntimeException("Input valid raise amount");
                }
            }
            default -> throw new RuntimeException("Invalid action, in act()");
        }
        PlayRound();
    }

    private Boolean allPlayersHavePlayed(ArrayList<Player> ps){
        for (Player p : ps){
            if (p.get_stateString().equals("Unplayed")){
                return false;
            }
        }
        return true;
    }

    private void EndGame(){
        //give pot the winning player. The only player left without the state "Fold" or "Busted" or with best hand
        if (activePlayersCount(this.getPlayers()) > 1) {
            try {
                getWinner(getPlayers()).add_chips(getPot());
            } catch (NullPointerException ignored) {}
        }

        setChipsOnTableToZero();
        river.clear();
        for (Player player : this.getPlayers()) {
            if (player.get_stateString().equals("OUT")){ continue; }
            player.set_state(new Unplayed());
        }
        StartGame(new Deck());
    }

    private void playedPlayersToUnplayed(){
        for (Player player : this.getPlayers()) {
            if (player.get_stateString().equals("Played")){ player.set_state(new Unplayed()); }
        }
    }

    private void advancePlayer() {
        currentPlayer = getCurrentPlayer() + 1;
        currentPlayer = getCurrentPlayer() % getPlayers().size();
        if (!getPlayers().get(getCurrentPlayer()).get_stateString().equals("Unplayed") &&
            !noUnplayedPlayers()){
            advancePlayer();
        }
        if (noUnplayedPlayers() &&
            !getPlayers().get(getCurrentPlayer()).get_stateString().equals("Played")){
            advancePlayer();
        }
    }

    private boolean noUnplayedPlayers() {
        for (Player p : getPlayers()) {
            if (p.get_stateString().equals("Unplayed")){
                return false;
            }
        }
        return true;
    }

    private String getPlayerStateString(int i) {
        return getPlayers().get(i).get_stateString();
    }

    public int getPot() {
        return pot;
    }

    public int getContributions() {
        return contributions;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public int getBlindCoinPos() {
        return blindCoinPos;
    }

    public int getBigBlind() {
        return bigBlind;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getDealerPos() {
        return dealerPos;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    /* -- DEPRECATED --
    private static void nextTurn(){
        // Byter turen till nästa spelarens tur i listan med ospelade turer.
        //Player klassen kan ha en state som heter "played" vilket indikerar till GameModel när rundan är över.
        //När alla spelare har en annan state än "unplayed", gå till nästa runda.
    }





    private static void aPlayerRaised(){
        //Sätt det nya bettet som måste callas/raisas och lägg till i contributions.
        nextTurn();
    }

    private static void aPlayerCalled(){
        //lägg till bettet i contributions
        nextTurn();
    }

    private static void aPlayerFolded(){
        //sätt dess players state till "folded"
        nextTurn();
    }
*/
}
