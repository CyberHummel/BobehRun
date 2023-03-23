package main.java.core;

import main.java.level.objects.items.Item;
import main.java.level.objects.items.jumpPotion;

import java.awt.*;

public class Item_Manager {
    public Item[] items;

    public void loadItems() {
        items = new Item[2];
        items[0] = new jumpPotion(200, 400, 48, false, "/main/ressources/textures/JumpPotion.png");
        items[1] = new jumpPotion(300, 400, 48, false, "/main/ressources/textures/JumpPotion.png");
    }

    public void Render(Graphics g) {
        for (int i = 0; i < items.length; i++) {
            items[i].Render(g);
        }
    }

}
