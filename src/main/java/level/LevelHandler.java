package main.java.level;

import main.java.level.objects.items.Item_Manager;
import main.java.level.objects.tiles.Tile_Manager;
import main.java.core.Window;


import main.java.level.objects.Player;

import java.awt.*;


//gesamte Classe seber
public class LevelHandler {
    public double Gravity = 4;

    public Player player;
    public Tile_Manager tileM = new Tile_Manager(this, 16, 12, 48);

    public Item_Manager itemM = new Item_Manager();

    Window w;

    public LevelHandler(Window w) {
        this.w = w;
        player = new Player(w, 100, 100, 84, 84, this);

        tileM.getImages();
        tileM.readLevelData("/main/ressources/leveldata/LevelBasic.txt");
        tileM.buildLevel();

        itemM.loadItems();
    }

    public void updateObstacles(int speed) {
        for (int i = 0; i < tileM.tiles.length; i++) {
            tileM.tiles[i].updateTile(speed);
        }
    }


    public void Render(Graphics g) {

        tileM.Render(g);

        player.Render(g);

        itemM.Render(g);
    }

    public void tick() {

        player.Collision();
        player.ItemCollission();
        player.tick();

    }

}

