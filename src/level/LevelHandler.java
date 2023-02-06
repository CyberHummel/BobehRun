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

    public int cameraX, cameraY;

    //vars

    //runs when lvl is created
    public LevelHandler(Window w){
        this.w=w;
        Random r = new Random();
        seed = r.nextInt();

        //add in player
        player = new Player(w, 100, 100, 42, 42);
       items.add(new Plattform(Obstacle.Platform,100, 300 ,100, 4, Color.CYAN));
    }

    public void Render(Graphics g){
        player.Render(g);
        g.translate(cameraX, cameraY);
        for(Obstacle i : items){
            i.Render(g);
        }
        g.translate(cameraX, cameraY);
    }

    public void tick(){
        for(Obstacle i : items){
            i.tick();
        }
        player.tick();
    }
}
