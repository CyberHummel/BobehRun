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

    private BufferedImage[] imgs;
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
       items.add(new Platform(Obstacle.Platform,100, 400 ,100, 4, Color.CYAN));
       imgs[1] = ImageIO.read(getClass().getResourceAsStream("/player.png"));
       items.add(new Platform(Obstacle.Platform,300, 350 ,100, 4, Color.RED));

        items.add(new Platform(Obstacle.Platform,500, 300 ,100, 4, Color.GREEN));

       items.add(new Platform(Obstacle.Platform, -500, floorHeight+41,10000,100, Color.BLACK));
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

        for(BufferedImage i : imgs){
            g2.drawImage(i, (int) x, (int) y, null);
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

