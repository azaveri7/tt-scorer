package model;

import java.util.Scanner;

import static util.TTConstants.*;

public final class Game {

    private Match match;

    public Game(Match match){
        this.match = match;
    }

    public void play(){
        int serveCounter = 0;
        match.start();
        displayServeMessage();
        while (!match.isEnded()) {
            if (isServeChangeRequired(serveCounter)) {
                match.changeServe();
                displayServeMessage();
            }
            match.updatePlayerPoints();
            displayScore();
            serveCounter++;
        }
        match.end();
    }

    public static void main(String[] args) {
        System.out.println("Program for TT scorer");
        System.out.println("Please enter the player full names");
        //Commented code can be used to input player names
        /*Scanner in = new Scanner(System.in);
        String player1_name = in.nextLine();
        String player2_name = in.nextLine();*/
        String player1_name = "Anand Zaveri";
        String player2_name = "Neha Zaveri";
        Match ttMatch = new Match(Match.State.NOT_STARTED,
                new TTPlayer(player1_name),
                new TTPlayer(player2_name));
        Game ttGame = new Game(ttMatch);
        ttGame.play();
        ttGame.displayGameResult();

    }

    public static boolean isServeChangeRequired(int serveCounter){
        return serveCounter != 0 && serveCounter % SERVE_CHANGE == 0;
    }

    private void displayServeMessage() {
        System.out.println("*********");
        System.out.println("Serve change");
        if (match.getPlayer1().isServing()) {
            System.out.println(match.getPlayer1().getFullName() + " is serving.");
            System.out.println(match.getPlayer2().getFullName() + " is receiving.");
        } else {
            System.out.println(match.getPlayer2().getFullName() + " is serving.");
            System.out.println(match.getPlayer1().getFullName() + " is receiving.");
        }
        System.out.println("*********");
    }

    private void displayScore() {
        System.out.println("*********");
        System.out.println("Status : " + match.getState().toString());
        System.out.println(match.getPlayer1().toString());
        System.out.println(match.getPlayer2().toString());
        System.out.println("*********");
    }

    private void displayGameResult() {
        displayScore();
        System.out.println("*********");
        System.out.println("Winner is : " + match.getWinner().getFullName());
        System.out.println("Loser is : " + match.getLoser().getFullName());
        System.out.println("*********");
    }

}
