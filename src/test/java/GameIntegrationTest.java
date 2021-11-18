import model.Game;
import model.Match;
import model.TTPlayer;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class GameIntegrationTest {

    private Match ttMatch;
    private Game ttGame;
    private TTPlayer player1;
    private TTPlayer player2;

    @BeforeEach
    public void init(){
        player1 = new TTPlayer("Anand Zaveri");
        player2 = new TTPlayer("Neha Zaveri");
        ttMatch = new Match(player1.getFullName(), player2.getFullName());
        ttGame = new Game(ttMatch);
    }

    @Test
    public void matchShouldStartAndEnd(){
        assertFalse(ttMatch.isEnded());
        assertEquals(ttMatch.getState(), Match.State.NOT_STARTED);
        ttGame.play();
        assertEquals(ttMatch.getState(), Match.State.COMPLETED);
        assertTrue(ttMatch.isEnded());
    }

    @AfterEach
    public void tearDown(){
        player1 = null;
        player2 = null;
        ttMatch = null;
        ttGame = null;
    }

}
