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

    public boolean falling = true;
    public boolean Jumpable;


    public Player(Window w, int x, int y, int width, int height) {            //x=StartX, y=StartY
        this.w = w;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void tick() {
        if ((w.FrameWidth / 2) == x && !w.Keylistener.MovingLeft) {
            x = w.FrameWidth / 2;
            if (w.Keylistener.Moving) {
                w.level.updateObstacles(-2);
            } else {
                w.level.updateObstacles(0);
            }
        } else {
            x += velx;
            w.level.updateObstacles(0);
        }

        if (x <= 0 && w.Keylistener.MovingLeft) {
            x = 0.0;

        }

        y += vely;

        if (vely < w.level.Gravity && falling) {
            vely += 0.1;
        }
        else if(!falling && vely > 0){
            vely = 0;
        }

        Collision();
     }

     public void Collision(){
        Jumpable = false;
        falling = true;
        for(Obstacle i : w.level.items){
            if(i.ID == Obstacle.Platform){
                Platform p = (Platform)i;

                    //CollisionDetec.
                    if(new Rectangle((int)x+ (int) velx, (int) y+ (int)vely,width,height).intersects(p.x, p.y, p.width, p.height)){
                        if  (y+height <= p.y+1){
                                falling = false;
                                if (vely > 0) {
                                    vely = 0;
                                    y = p.y - height + 1;
                                }
                        }
                        else if (y < p.y){
                                velx = 0;
                        }

                        if(vely < 0){
                            falling = true;
                            vely = -1*vely;
                            y-=(vely+1);
                        }
                    }

                    //Future Detec. for smoother jumping
                    float CollisionTimeDetectionTicks = 1;
                    if(new Rectangle((int)(x+ velx), (int)(y+vely*CollisionTimeDetectionTicks),width,height).intersects(p.x, p.y, p.width, p.height)){
                        Jumpable = true;
                    }

            }
        }
     }



     public void Render(Graphics g){
         g.setColor(Color.BLUE);
         g.fillRect((int)x, (int) y, width, height);
     }
}
