package main.java.level.objects.items;

import main.java.level.objects.Player;

import java.awt.*;

public class jumpPotion extends Item {
    public jumpPotion(int x, int y, int size, boolean pickedUp, String texturePath) {
        super(x, y, size, pickedUp, texturePath);
    }

    @Override
    public void Render(Graphics g) {
        if (!pickedUp) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(image, x, y, size, size, null);
        }
    }

    @Override
    public void updateCoords(int speed) {
        hitbox.x += speed;
        x += speed;
    }

    @Override
    public void pickUp(Player p) {
        pickedUp = true;
        p.JumpVelocity = 8;
    }
}
