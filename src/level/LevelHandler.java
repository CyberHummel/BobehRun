package level;
import core.Window;
import objects.Platform;
import objects.Obstacle;
import objects.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class LevelHandler {
    //Vars

    private String path_Dirt = "/Dirt.png";
    private String path_Grass = "/Grass-1.png.png";
    private  String path_StoneBricks = "/StoneBricks_Long.png";
    public double Gravity = 8;

    public Player player = null;

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
       items.add(new Platform(Obstacle.Platform,100, 400 ,84, 84, path_Grass, path_Dirt));
       items.add(new Platform(Obstacle.Platform,300, 350 ,84, 84, path_Grass, path_Dirt));

       items.add(new Platform(Obstacle.Platform,500, 300 ,84, 84, path_Grass, path_Dirt));

       items.add(new Platform(Obstacle.Platform, -500, floorHeight+41,10000,100,path_StoneBricks, path_StoneBricks));
       player = new Player(w, 100, 100, 84, 84);
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

