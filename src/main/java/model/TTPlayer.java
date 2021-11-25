package model;

public class TTPlayer implements Comparable<TTPlayer>{

    private final String fullName;
    private int points;
    private boolean isServing;
    private boolean isConsecutive;

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isServing() {
        return isServing;
    }

    public void setServing(boolean serving) {
        isServing = serving;
    }

    public TTPlayer(String fullName) {
        this.fullName = fullName;
        this.points = 0;
    }

    public String getFullName() {
        return fullName;
    }

    public int getPoints() {
        return points;
    }

    public void incrementPoint() {
        this.points += 1;
    }

    @Override
    public String toString() {
        return "Name: " + getFullName() +
                " Points: " + getPoints();
    }

    @Override
    public int compareTo(TTPlayer player){
        return this.getFullName().compareTo(player.getFullName());
    }

}
