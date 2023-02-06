package objects;

import java.awt.*;

public abstract class Obstacle {
    //OBJ IDS
    public static byte Platform = Byte.MIN_VALUE;
    //End of OBJ IDS
    protected byte ID;

    public Obstacle(byte ID){
        this.ID=ID;
    }
    public abstract void Render(Graphics g);

    public abstract void tick();



}
