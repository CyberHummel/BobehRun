package main.java.level.objects.items;

import main.java.level.LevelHandler;

import java.awt.*;

public class Item_Manager { // gesamte Klasse selber
    public Item[] items;

    public void loadItems(LevelHandler lH) {
        items = new Item[3];
        items[0] = new jumpPotion(200, 400, 48, false, "/main/ressources/textures/JumpPotion.png");
        items[1] = new jumpPotion(300, 400, 48, false, "/main/ressources/textures/JumpPotion.png");
        items[2] = new Coin(400, 400, 48, false, "/main/ressources/textures/Coin.png", lH.hud);
    }

    public void Render(Graphics g) {
        for (Item item : items) {
            item.Render(g);
        }
    }
}
