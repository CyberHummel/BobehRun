package Objects;

import java.awt.*;

public abstract class Item {
    //OBJ IDS
    public static byte Platform = Byte.MIN_VALUE;
    //End of OBJ IDS
    protected byte ID;

    public Item(byte ID){
        this.ID=ID;
    }
    public abstract void Render(Graphics g);

    public abstract void tick();



}
