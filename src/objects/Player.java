package objects;
import core.Window;

import java.awt.*;

public class Player {
    public Window w;
    public int width, height;
    public double x, y;
    public double velx, vely;
    public double JumpVelocity = 4;

    public int speed = 2;


     public Player(Window w,int x, int y, int width, int height){            //x=StartX, y=StartY
         this.w=w;
         this.x=x;
         this.y=y;
         this.width=width;
         this.height=height;
     }

    public void tick(){
        x+=velx;

        if(y+vely < w.level.floorHeight){
            y+=vely;
        }

        else{
            vely = 0;
        }


        if(vely > -w.level.Gravity){
            vely+=0.1;
        }
     }


     public void Render(Graphics g){
         g.setColor(Color.BLUE);
       g.fillRect((int)x, (int) y, width, height);
     }
}
