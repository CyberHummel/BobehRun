package level;
import core.Window;
import objects.Plattform;
import objects.Obstacle;
import objects.Player;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class LevelHandler {
    //Vars
    public double Gravity = 8;

    public Player player = null;

    public LinkedList<Obstacle> items = new LinkedList<Obstacle>();

    public int floorHeight = 400;

     Window w;


    private int seed;

    public double cameraX, cameraY;

    //vars

    //runs when lvl is created
    public LevelHandler(Window w){
        this.w=w;
        Random r = new Random();
        seed = r.nextInt();

        //add in player


       items.add(new Plattform(Obstacle.Platform,100, 300 ,100, 4, Color.CYAN));
       items.add(new Plattform(Obstacle.Platform, -500, floorHeight+41,10000,100, Color.BLACK));
       player = new Player(w, 100, 100, 42, 42);
    }

    public void Render(Graphics g){
        g.translate((int) cameraX, (int) cameraY);

        for(Obstacle i : items){
            i.Render(g);
        }
        player.Render(g);
        g.translate((int) cameraX, (int) cameraY);
    }

    public void tick(){
        System.out.println(player.x);
        if(player.x == w.FrameWidth){
            player.x = 0;
            cameraX = player.x;
            if(player.velx < 0 && player.vely != 0){
                w.level.cameraX += +w.level.player.speed+w.player.velx;
                for(Obstacle i : items){
                    i.tick();
                }
                player.tick();
            }

            else if(player.velx > 0 && player.vely != 0){
                w.level.cameraX += -w.level.player.speed-w.player.velx;
                for(Obstacle i : items){
                    i.tick();
                }
                player.tick();
            }
            for(Obstacle i : items){
                i.tick();
            }
            player.tick();
        }

        else{
            if(player.velx < 0 && player.vely != 0){
                w.level.cameraX += +w.level.player.speed+w.player.velx;
                for(Obstacle i : items){
                    i.tick();
                }
                player.tick();
            }

            else if(player.velx > 0 && player.vely != 0){
                w.level.cameraX += -w.level.player.speed-w.player.velx;
                for(Obstacle i : items){
                    i.tick();
                }
                player.tick();
            }




            for(Obstacle i : items){
                i.tick();
            }
            player.tick();
        }

    }
}
