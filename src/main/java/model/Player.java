package model;

public interface Player {

    public String getScoreCardName();
    public int getPoints();
    public void incrementPoint();

    default boolean samePlayer(Player player){
        return this.equals(player);
    }

}
