package model;

public final class Scoreboard {

    private Player p1;
    private Player p2;

    Scoreboard(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public String toString(){
        StringBuilder scoreString = new StringBuilder();
        scoreString.append("Score is as follows: \n")
                .append(p1)
                .append("\n")
                .append(p2)
                .toString();
        return scoreString.toString();
    }

}
