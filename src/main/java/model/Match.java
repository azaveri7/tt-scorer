package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Match {

    public enum State {
        NOT_STARTED, IN_PROGRESS, COMPLETED
    }

    private State state;
    private Player p1;
    private Player p2;

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public State getState(){
        return state;
    }

    public void changeMatchStatus(State state){
        this.state = state;
    }

    public Match(State state) {
        this.state = state;
    }

    public Match(State state, Player p1, Player p2){
        this.state = state;
        this.p1 = p1;
        this.p2 = p2;
    }

    public Match newMatch(State state){
        return new Match(state);
    }

    public boolean isMatchEnded(){
        return this.state == State.COMPLETED;
    }

    public List<Player> getPlayers(){
        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        return players;
    }


    public void displayScore(){
        System.out.println("*********");
        System.out.println("Status : " + getState().toString());
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println("*********");
    }

    public void displayServeChangeMsg(boolean serveIndicator){
        System.out.println("*********");
        System.out.println("Serve change");
        if(serveIndicator){
            System.out.println(p1.getScoreCardName() + " is serving.");
            System.out.println(p2.getScoreCardName() + " is receiving.");
        } else {
            System.out.println(p2.getScoreCardName() + " is serving.");
            System.out.println(p1.getScoreCardName() + " is receiving.");
        }
        System.out.println("*********");
    }

    public void displayMatchResult(){
        displayScore();
        System.out.println("*********");
        if(p1.getPoints() > p2.getPoints()){
            System.out.println("Winner is : " + p1.getScoreCardName());
        } else {
            System.out.println("Winner is : " + p2.getScoreCardName());
        }
        System.out.println("*********");
    }

    /*public int getTossResult(int size){

    }*/


}
