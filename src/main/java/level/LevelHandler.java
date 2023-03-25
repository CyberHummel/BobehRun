package main.java.level;

import main.java.core.hud.HUD;
import main.java.level.objects.backround.BackroundHandler;
import main.java.level.objects.items.Item_Manager;
import main.java.level.objects.tiles.Tile_Manager;
import main.java.core.Window;


import main.java.level.objects.Player;

import java.awt.*;


//gesamte Classe seber
public class LevelHandler {
    public double Gravity = 4;

    public Player player;
    public Tile_Manager tileM = new Tile_Manager(this, 32, 12, 48);

    public Item_Manager itemM = new Item_Manager();

    public BackroundHandler bM = new BackroundHandler(this);

    public HUD hud = new HUD(this);

    Window w;

    public LevelHandler(Window w) {
        this.w = w;
        player = new Player(w, 100, 100, 84, 84, this);

        tileM.getImages();
        tileM.readLevelData("/main/ressources/leveldata/LevelBasic.txt");
        tileM.buildLevel();

        itemM.loadItems(this);

        bM.BuildClouds(2);
    }

    public void updateObstacles(int speed, double cloudSpeed) {
        for (int i = 0; i < tileM.tiles.length; i++) {
            tileM.tiles[i].updateTile(speed);
        }
        bM.updateClouds(cloudSpeed);
    }


    public void Render(Graphics g) {

        tileM.Render(g);

        player.Render(g);

        itemM.Render(g);

        bM.Render(g);

        hud.Render(g);
    }

    public void tick() {

        player.Collision();
        player.ItemCollission();
        player.tick();

    }

}

