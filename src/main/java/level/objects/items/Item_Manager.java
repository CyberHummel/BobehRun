package main.java.level.objects.items;

import main.java.level.LevelHandler;

import java.awt.*;

public class Item_Manager { // gesamte Klasse selber
    public Item[] items;

    public void loadItems(LevelHandler lH) {
        items = new Item[9];
        items[0] = new jumpPotion(2000, 575, 48, false, "/main/ressources/textures/JumpPotion.png");
        items[1] = new Coin(618,882 , 48, false, "/main/ressources/textures/Coin.png", lH.hud);
        items[2] = new Coin(944,834 , 48, false, "/main/ressources/textures/Coin.png", lH.hud);
        items[3] = new Coin(1200,834 , 48, false, "/main/ressources/textures/Coin.png", lH.hud);
        items[4] = new Coin(1300,834 , 48, false, "/main/ressources/textures/Coin.png", lH.hud);
        items[5] = new Coin(1500,834 , 48, false, "/main/ressources/textures/Coin.png", lH.hud);
        items[6] = new Coin(1800,800 , 48, false, "/main/ressources/textures/Coin.png", lH.hud);
        items[7] = new Coin(2300,750 , 48, false, "/main/ressources/textures/Coin.png", lH.hud);
        items[8] = new Coin(2500,650 , 48, false, "/main/ressources/textures/Coin.png", lH.hud);
    }

    public void Render(Graphics g) {
        for (Item item : items) {
            item.Render(g);
        }
    }

    public void updateSpeed(int speed){
        for(Item item : items){
            item.updateCoords(speed);
        }
    }
}
