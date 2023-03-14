package main.java.level;
import main.java.core.Tile_Manager;
import main.java.core.Window;


import main.java.level.objects.Player;

import java.awt.*;
import java.io.IOException;


//gesamte Classe seber
public class LevelHandler {
    public double Gravity = 8;

    public Player player;
    public Tile_Manager tileM = new Tile_Manager(this, 16, 12, 48);



     Window w;




    //vars

    //runs when lvl is created
    public LevelHandler(Window w) throws IOException {
        this.w=w;
       player = new Player(w, 100, 100, 84, 84, this);

        tileM.getImages();
        tileM.readLevelData("/main/ressources/leveldata/LevelBasic.txt");
        tileM.buildLevel();
    }

    public void updateObstacles(int speed){
        //TODO
    }


    public void Render(Graphics g){

        tileM.Render(g);

        player.Render(g);
    }

    public void tick(){
        //Vars
        double deathY = 800;

            tileM.Tick(player);
            player.tick();

        }

    }

