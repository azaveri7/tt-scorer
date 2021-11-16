package model;

import util.TTUtil;

import static util.TTConstants.*;

public final class Game {
    public static void main(String[] args) {
        System.out.println("Program for TT scorer");

        TTPlayer player1 = new TTPlayer("Sharath Achanta");
        TTPlayer player2 = new TTPlayer("Soumyajit Ghosh");
        Match match = new Match(Match.State.NOT_STARTED, player1, player2);
        boolean serveIndicator = false;
        while (!match.isMatchEnded()) {
            match.changeMatchStatus(Match.State.IN_PROGRESS);
            player1.setServing(!serveIndicator);
            player2.setServing(serveIndicator);
            for (int i = 0; i < MAX_POINTS * 2; i++) {
                if (i != 0 && i % SERVE_CHANGE == 0) {
                    player1.setServing(serveIndicator);
                    player2.setServing(!serveIndicator);
                    match.displayServeChangeMsg(serveIndicator);
                    match.displayScore();
                    serveIndicator = !serveIndicator;
                }

                updatePlayerPoints(player1, player2);

                if (player1.getPoints() == GOLDEN_POINT && player2.getPoints() == GOLDEN_POINT) {
                    continue;
                } else if (player1.getPoints() == GOLDEN_POINT + 1 || player2.getPoints() == GOLDEN_POINT + 1) {
                    break;
                } else if (player1.getPoints() > 10
                        || player2.getPoints() > 10) {
                    if (Math.abs(player1.getPoints() - player2.getPoints()) > 1)
                        break;
                }

            }
            match.changeMatchStatus(Match.State.COMPLETED);
        }
        match.displayMatchResult();

    }

    private static void updatePlayerPoints(Player p1, Player p2) {
        if (TTUtil.getRandomPoint() % 2 == 0) {
            p1.incrementPoint();
        } else {
            p2.incrementPoint();
        }
    }


}
