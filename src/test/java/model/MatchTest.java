package model;

import model.Match.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import util.RandomNumberGenerator;
import util.TTConstants;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    private MockedStatic<RandomNumberGenerator> randomNumberGenerator;

    @BeforeEach
    public void setup(){
        randomNumberGenerator= Mockito.mockStatic(RandomNumberGenerator.class);
    }

    @AfterEach
    public void tearDown(){
        randomNumberGenerator.close();
    }

    @Test
    public void shouldInitialiseMatchCorrectlyWhenOnlyNamesAreProvided() {
        Match match = new Match("Anand", "Neha");
        assertEquals(State.NOT_STARTED,match.getState());
        assertEquals("Anand", match.getPlayer1().getFullName());
        assertEquals("Neha", match.getPlayer2().getFullName());
    }

    @Test
    public void shouldStartMatch() {
        Match match = new Match("Anand", "Neha");
        assertFalse(match.getPlayer1().isServing());
        assertFalse(match.getPlayer2().isServing());
        assertEquals(State.NOT_STARTED,match.getState());

        match.start();

        assertTrue(match.getPlayer1().isServing());
        assertFalse(match.getPlayer2().isServing());
        assertEquals(State.IN_PROGRESS, match.getState());
    }

    @Test
    public void shouldChangeServe() {
        TTPlayer player1 = new TTPlayer("Anand Zaveri");
        player1.setServing(true);
        TTPlayer player2 = new TTPlayer("Neha Zaveri");
        Match match = new Match(State.IN_PROGRESS, player1,
                player2);

        assertTrue(match.getPlayer1().isServing());
        assertFalse(match.getPlayer2().isServing());
        assertEquals(State.IN_PROGRESS, match.getState());

        match.changeServe();

        assertFalse(match.getPlayer1().isServing());
        assertTrue(match.getPlayer2().isServing());
        assertEquals(State.IN_PROGRESS, match.getState());
    }

    @Test
    public void shouldAllotPointsToPlayer1WhenRandomPointGeneratorReturnEvenNumber(){
        TTPlayer player1 = new TTPlayer("Anand Zaveri");
        player1.setServing(true);
        TTPlayer player2 = new TTPlayer("Neha Zaveri");
        Match match = new Match(State.IN_PROGRESS, player1,
                player2);

        randomNumberGenerator.when(RandomNumberGenerator::get).thenReturn(2);
        match.updatePlayerPoints();

        assertEquals(match.getPlayer1().getPoints(), 1);
        assertEquals(match.getPlayer2().getPoints(),0);
    }

    @Test
    public void shouldAllotPointsToPlayer2WhenRandomPointGeneratorReturnOddNumber(){
        TTPlayer player1 = new TTPlayer("Anand Zaveri");
        player1.setServing(true);
        TTPlayer player2 = new TTPlayer("Neha Zaveri");
        Match match = new Match(State.IN_PROGRESS, player1,
                player2);

        randomNumberGenerator.when(RandomNumberGenerator::get).thenReturn(3);
        match.updatePlayerPoints();

        assertEquals(match.getPlayer1().getPoints(), 0);
        assertEquals(match.getPlayer2().getPoints(),1);
    }

    @Test
    public void shouldReturnTrueForEndMatchWhenPlayerReaches11Points(){
        TTPlayer player1 = new TTPlayer("Anand Zaveri");
        player1.setServing(true);
        player1.setPoints(11);
        TTPlayer player2 = new TTPlayer("Neha Zaveri");
        Match match = new Match(State.IN_PROGRESS, player1,
                player2);

        assertTrue(match.isEnded());
    }

    @Test
    public void shouldReturnTrueForWhenPlayerPointDifferenceIs2Points(){
        TTPlayer player1 = new TTPlayer("Anand Zaveri");
        player1.setServing(true);
        player1.setPoints(11);
        TTPlayer player2 = new TTPlayer("Neha Zaveri");
        player2.setPoints(13);
        Match match = new Match(State.IN_PROGRESS, player1, player2);

        assertTrue(match.isEnded());
    }

    @Test
    public void shouldReturnFalseForWhenPlayerPointDifferenceIs1Points(){
        TTPlayer player1 = new TTPlayer("Anand Zaveri");
        player1.setServing(true);
        player1.setPoints(12);
        TTPlayer player2 = new TTPlayer("Neha Zaveri");
        player2.setPoints(13);
        Match match = new Match(State.IN_PROGRESS, player1, player2);

        assertFalse(match.isEnded());
    }

    @Test
    public void shouldReturnFalseForWhenPlayerAreTied(){
        TTPlayer player1 = new TTPlayer("Anand Zaveri");
        player1.setServing(true);
        player1.setPoints(20);
        TTPlayer player2 = new TTPlayer("Neha Zaveri");
        player2.setPoints(20);
        Match match = new Match(State.IN_PROGRESS, player1, player2);

        assertFalse(match.isEnded());
    }

    @Test
    public void shouldReturnTrueWhenPlayerAreReaches21Points(){
        TTPlayer player1 = new TTPlayer("Anand Zaveri");
        player1.setServing(true);
        player1.setPoints(21);
        TTPlayer player2 = new TTPlayer("Neha Zaveri");
        player2.setPoints(20);
        Match match = new Match(State.IN_PROGRESS, player1, player2);

        assertTrue(match.isEnded());
    }

    @Test
    public void shouldSetStateToCompletedWhenMatchEnd() {
        TTPlayer player1 = new TTPlayer("Anand Zaveri");
        player1.setServing(true);
        player1.setPoints(21);
        TTPlayer player2 = new TTPlayer("Neha Zaveri");
        player2.setPoints(20);
        Match match = new Match(State.IN_PROGRESS, player1, player2);

        match.end();

        assertEquals(State.COMPLETED,match.getState());
    }

    @Test
    public void shouldReturnPlayer1IsWinnerIfMorePoints(){
        TTPlayer player1 = new TTPlayer("Anand Zaveri");
        player1.setServing(true);
        TTPlayer player2 = new TTPlayer("Neha Zaveri");
        Match match = new Match(State.IN_PROGRESS, player1, player2);
        player1.setPoints(21);
        player2.setPoints(12);
        match.end();
        assertEquals(player1, match.getWinner());
    }

    @Test
    public void shouldReturnPlayer1IsLoserIfLessPoints(){
        TTPlayer player1 = new TTPlayer("Anand Zaveri");
        player1.setServing(true);
        TTPlayer player2 = new TTPlayer("Neha Zaveri");
        Match match = new Match(State.IN_PROGRESS, player1, player2);
        player1.setPoints(11);
        player2.setPoints(21);
        match.end();
        assertEquals(player1, match.getLoser());
    }

    /*@Test
    public void shouldCheckForConsecutivePointsAfterConsPoint(){
        TTPlayer player1 = new TTPlayer("Anand Zaveri");
        player1.setServing(true);
        TTPlayer player2 = new TTPlayer("Neha Zaveri");
        Match match = new Match(State.NOT_STARTED, player1, player2);
        match.start();

        player1.setPoints(TTConstants.CONSECUTIVE_POINT);
        player2.setPoints(TTConstants.CONSECUTIVE_POINT);
        randomNumberGenerator.when(RandomNumberGenerator::get).thenReturn(2);
        assertFalse(match.isEnded());
        match.updatePlayerPoints();
        assertFalse(match.isEnded());
        match.updatePlayerPoints();
        assertTrue(match.isEnded());
        assertEquals(player1, match.getWinner());

    }*/

    /*@Test
    public void shouldCheckForConsecutivePointsAfterAlternatePlayerPoints(){
        TTPlayer player1 = new TTPlayer("Anand Zaveri");
        player1.setServing(true);
        TTPlayer player2 = new TTPlayer("Neha Zaveri");
        Match match = new Match(State.NOT_STARTED, player1, player2);
        match.start();

        player1.setPoints(TTConstants.CONSECUTIVE_POINT);
        player2.setPoints(TTConstants.CONSECUTIVE_POINT);
        randomNumberGenerator.when(RandomNumberGenerator::get).thenReturn(3).thenReturn(2);
        assertFalse(match.isEnded());
        match.updatePlayerPoints();
        assertFalse(match.isEnded());
        match.updatePlayerPoints();
        assertFalse(match.isEnded());
        match.updatePlayerPoints();
        assertTrue(match.isEnded());
        assertEquals(player1, match.getWinner());

    }*/

    @Test
    public void shouldCheckForConsecutivePointsByChangingValueOfConsecutivePoints(){
        TTPlayer player1 = new TTPlayer("Anand Zaveri");
        player1.setServing(true);
        TTPlayer player2 = new TTPlayer("Neha Zaveri");
        Match match = new Match(State.NOT_STARTED, player1, player2);
        match.start();

        player1.setPoints(TTConstants.CONSECUTIVE_POINT);
        player2.setPoints(TTConstants.CONSECUTIVE_POINT);
        randomNumberGenerator.when(RandomNumberGenerator::get)
                .thenReturn(3)
                .thenReturn(3)
                .thenReturn(2)
                .thenReturn(2)
                .thenReturn(2);
        assertFalse(match.isEnded());
        match.updatePlayerPoints();
        assertFalse(match.isEnded());
        match.updatePlayerPoints();
        assertFalse(match.isEnded());
        match.updatePlayerPoints();
        assertFalse(match.isEnded());
        match.updatePlayerPoints();
        assertFalse(match.isEnded());
        match.updatePlayerPoints();
        assertTrue(match.isEnded());
        assertEquals(player1, match.getWinner());

    }
}
