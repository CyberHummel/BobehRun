package level;

import java.util.Random;

public class LevelHandler {
    public double Gravity = 8;
    public int floorHeight = 400;

    private int seed;
    //runs when lvl is created
    public LevelHandler(){
        Random r = new Random();
        seed = r.nextInt();
    }
}
