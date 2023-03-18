package main.java.level;
import main.java.core.Tile_Manager;
import main.java.core.Window;


import main.java.level.objects.Player;

import java.awt.*;
import java.io.IOException;


//gesamte Classe seber
public class LevelHandler {
    public double Gravity = 4;

    public Player player;
    public Tile_Manager tileM = new Tile_Manager(this, 16, 12, 48);



     Window w;




    //vars

    //runs when lvl is created
    public LevelHandler(Window w){
        this.w=w;
       player = new Player(w, 100, 100, 84, 84, this);

        tileM.getImages();
        tileM.readLevelData("/main/ressources/leveldata/LevelBasic.txt");
        tileM.buildLevel();
    }

    public void updateObstacles(int speed){
        for(int i = 0; i < tileM.tiles.length; i++){
            tileM.tiles[i].updateTile(speed);
        }
    }


    public void Render(Graphics g){

        tileM.Render(g);

        player.Render(g);
    }

    public void tick(){
        //Vars
        //double deathY = 800;

            player.Collision();
            player.tick();

        }

    }

