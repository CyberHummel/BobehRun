package main.java.level.objects.items;

import java.awt.*;

public class Item_Manager { // gesamte Klasse selber
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
