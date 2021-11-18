package util;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class RandomNumberGeneratorTest {

    @Test
    public void shouldGenerateRandomPointLessThanHundred(){
        IntStream.range(0,20).forEach(i -> {
                assertTrue( RandomNumberGenerator.get() < 100);
        });
    }
}
