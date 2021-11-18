package util;

import java.util.Random;

public class RandomNumberGenerator {

    public static int get(){
        return new Random().nextInt(100);
    }

}
