package model;

public class TTPlayer implements Player{

    private final String fullName;
    private int points;
    private boolean isServing;

    public boolean isServing() {
        return isServing;
    }

    public void setServing(boolean serving) {
        isServing = serving;
    }

    public TTPlayer(String fullName){
        this.fullName = fullName;
        this.points = 0;
    }

    public String getFullName() {
        return fullName;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public void incrementPoint(){
        this.points += 1;
    }

    @Override
    public String toString(){
        StringBuilder playerString = new StringBuilder();
        playerString.append("Name: " + getFullName())
                .append(" Points: " + getPoints());
        return playerString.toString();
    }

    public String getScoreCardName(){
        String[] bits = this.fullName.split(" ");
        if (bits.length == 0 || bits[0].length() < 1) return this.fullName;
        return bits[0].charAt(0) + " " + bits[bits.length - 1];
    }
}
