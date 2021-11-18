package model;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void shouldReturnTrueForServeChangeAfterEvenPoints(){
        IntStream.range(1,10).filter(i -> i !=0 && i%2 == 0).forEach(i->{
            assertTrue(Game.isServeChangeRequired(i));
        });
    }

    @Test
    public void shouldReturnFalseForServeChangeAfterOddPointsAndMatchStart(){
        IntStream.range(0,10).filter(i -> i ==0 || i%2!=0).forEach(i->{
            assertFalse(Game.isServeChangeRequired(i));
        });
    }

}
