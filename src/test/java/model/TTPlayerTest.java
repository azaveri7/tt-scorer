package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TTPlayerTest {

    @Test
    public void shouldIncrementPointsByOne(){
        TTPlayer player = new TTPlayer("Anand Zaveri");
        assertEquals(player.getPoints(), 0);

        player.incrementPoint();

        assertEquals(player.getPoints(),1);

    }
}
