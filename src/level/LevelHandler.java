package level;
import core.CollissionDetection;
import core.Window;
import core.Tile_manager;
import objects.Obstacle;
import objects.Player;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class LevelHandler {
    //Vars
    private double deathY = 800;
    private String path_Dirt = "/Dirt.png";
    private String path_Grass = "/Grass.png";
    private  String path_StoneBricks = "/StoneBricks_Long.png";
    public double Gravity = 8;

    public Player player = null;
    Tile_manager tileM = new Tile_manager(this);
    CollissionDetection cDetection = new CollissionDetection();

    public ArrayList<Obstacle> items = new ArrayList<Obstacle>();

    public int floorHeight = 400;

     Window w;


    private int seed;

    //vars

    //runs when lvl is created
    public LevelHandler(Window w) throws IOException {
        this.w=w;
        Random r = new Random();
        seed = r.nextInt();
       //items.add(new Platform(Obstacle.Platform,100, 400 ,48, 48, path_Grass, path_Dirt));
       //items.add(new Platform(Obstacle.Platform,300, 350 ,48, 48, path_Grass, path_Dirt));

       //items.add(new Platform(Obstacle.Platform,500, 300 ,48, 48, path_Grass, path_Dirt));

       player = new Player(w, 100, 100, 84, 84);
    }

    public void updateObstacles(int speed){
        for (int i = 0; i < items.size(); i++){
            items.get(i).updateCords(-speed);
        }
    }


    public void Render(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2, w);


        player.Render(g);
    }

    public void tick(){

            player.collisionOn = false;

            //cDetection.collissionDetectTile(player, );
            //for(Obstacle i : items){
                //i.tick();
            //}
            player.tick();
            if(player.y >= deathY){     //Death Zone
              System.exit(0);
            }
        }

    }

