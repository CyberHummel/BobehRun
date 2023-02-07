package level;
import core.Window;
import objects.Plattform;
import objects.Obstacle;
import objects.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class LevelHandler {
    //Vars
    public double Gravity = 8;

    public Player player = null;

    public ArrayList<Obstacle> items = new ArrayList<Obstacle>();

    public int floorHeight = 400;

     Window w;


    private int seed;

    //vars

    //runs when lvl is created
    public LevelHandler(Window w){
        this.w=w;
        Random r = new Random();
        seed = r.nextInt();

        //add in player


       items.add(new Plattform(Obstacle.Platform,100, 300 ,100, 4, Color.CYAN));
       items.add(new Plattform(Obstacle.Platform,1000, 300 ,100, 4, Color.RED));
       items.add(new Plattform(Obstacle.Platform, -500, floorHeight+41,10000,100, Color.BLACK));
       player = new Player(w, 100, 100, 42, 42);
    }

    public void updateObstacles(int speed){
        for (int i = 0; i < items.size(); i++){
            items.get(i).updateCords(-speed);
        }
    }


    public void Render(Graphics g){
        for(Obstacle i : items){
            i.Render(g);
        }
        player.Render(g);
    }

    public void tick(){
            for(Obstacle i : items){
                i.tick();
            }
            player.tick();
        }

    }

