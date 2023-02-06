package Objects;

import java.awt.*;

public class Player {
    public int x, y, width, height;
    public int velx, vely;
    public int speed = 2;


     public Player(int x, int y, int width, int height){            //x=StartX, y=StartY
         this.x=x;
         this.y=y;
         this.width=width;
         this.height=height;
     }

     public void tick(){
        x+=velx;
        y+=vely;
     }

     public void Render(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(x,y,width,height);
     }
}
