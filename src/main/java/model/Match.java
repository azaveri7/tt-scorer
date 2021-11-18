package model;

import util.RandomNumberGenerator;
import static util.TTConstants.*;

public class Match {

    public enum State {
        NOT_STARTED, IN_PROGRESS, COMPLETED
    }

    private State state;
    private final TTPlayer player1;
    private final TTPlayer player2;

    public Match( String player1, String player2) {
        this.state = State.NOT_STARTED;
        this.player1 = new TTPlayer(player1);
        this.player2 = new TTPlayer(player2);
    }

    public Match(State state, TTPlayer player1, TTPlayer player2) {
        this.state = state;
        this.player1 = player1;
        this.player2 = player2;
    }

    public TTPlayer getPlayer1() {
        return this.player1;
    }

    public TTPlayer getPlayer2() {
        return this.player2;
    }

    public State getState() {
        return this.state;
    }

    public void changeServe() {
        this.getPlayer1().setServing(!this.getPlayer1().isServing());
        this.getPlayer2().setServing(!this.getPlayer2().isServing());
    }

    public void updatePlayerPoints() {
        if (RandomNumberGenerator.get() % 2 == 0) {
            this.getPlayer1().incrementPoint();
        } else {
            this.getPlayer2().incrementPoint();
        }
    }

    public void start(){
        this.setState(Match.State.IN_PROGRESS);
        this.getPlayer1().setServing(true);
        this.getPlayer2().setServing(false);
    }

    public void end(){
        this.setState(State.COMPLETED);
    }

    private void setState(State state) {
        this.state = state;
    }

    public boolean isEnded() {
        if (this.getPlayer1().getPoints() == GOLDEN_POINT && this.getPlayer2().getPoints() == GOLDEN_POINT) {
            return false;
        } else if (this.getPlayer1().getPoints() == MAX_POINTS || this.getPlayer2().getPoints() == MAX_POINTS) {
            return true;
        } else if (this.getPlayer1().getPoints() > 10
                || this.getPlayer2().getPoints() > 10) {
              return Math.abs(this.getPlayer1().getPoints() - this.getPlayer2().getPoints()) > 1;
        }
        return false;
    }

    public TTPlayer getWinner(){
        if(player1.getPoints() > player2.getPoints())
            return player1;
        else
            return player2;
    }

    public TTPlayer getLoser(){
        if(player1.getPoints() > player2.getPoints())
            return player2;
        else
            return player1;
    }
}