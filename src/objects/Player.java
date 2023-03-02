package objects;
import core.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {
    public Window w;
    public int width, height;
    public double x, y;
    public double velx, vely;
    public double JumpVelocity = 4;
    public int speed = 2;

    public boolean falling = true;
    public boolean Jumpable;

    public Rectangle hitBox;
    public boolean collisionOn = false;


    public Player(Window w, int x, int y, int width, int height) {            //x=StartX, y=StartY
        this.w = w;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitBox = new Rectangle(x+8, y + 16, 32, 32);
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

        //Collision();
     }

     /*public void Collision(){
        Jumpable = false;
        falling = true;
        for(Obstacle i : w.level.items){
            if(i.ID == Obstacle.Platform){
                Platform p = (Platform)i;

                    //CollisionDetec.
                    if(new Rectangle((int)x+ (int) velx, (int) y+ (int)vely,width,height).intersects(p.x, p.y, (p.width - 20), (p.height - 20))){
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
     }*/



     public void Render(Graphics g) {
         Graphics2D g2 = (Graphics2D) g;
         //g2.fillRect((int)x, (int) y, width, height);

         Image resplayer;
         try {
             BufferedImage player = ImageIO.read(getClass().getResourceAsStream("/player.png"));
             resplayer = player.getScaledInstance(width, height, Image.SCALE_SMOOTH);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }

         g2.drawImage(resplayer, (int) x, (int) y, null);

     }
}
