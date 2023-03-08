package level;
import core.CollissionDetection;
import core.Window;
import core.Tile_manager;

import objects.Player;

import java.awt.*;
import java.io.IOException;


//gesamte Classe seber
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

    public int floorHeight = 400;

     Window w;
     Tile_manager tM = new Tile_manager(this);


    private int seed;

    //vars

    //runs when lvl is created
    public LevelHandler(Window w) throws IOException {
        this.w=w;
       player = new Player(w, 100, 100, 84, 84);
    }

    public void updateObstacles(int speed){

    }


    public void Render(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2, w);


        player.Render(g);
    }

    public void tick(){
            player.collisionOn = true;
            player.tick();
            for(tM.tile.length)
            cDetection.collissionDetectTile(player,);
        }

    }

