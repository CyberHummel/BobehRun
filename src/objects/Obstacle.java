package objects;

import java.awt.*;

public abstract class Obstacle {
    //OBJ IDS
    public static byte Platform = Byte.MIN_VALUE;
    //End of OBJ IDS
    protected byte ID;
    protected int x;

    public Obstacle(byte ID){
        this.ID=ID;
    }
    public abstract void updateCords(int speed);
    public abstract void Render(Graphics g);

    public abstract void tick();



}
