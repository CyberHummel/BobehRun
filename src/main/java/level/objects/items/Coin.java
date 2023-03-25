package main.java.level.objects.items;

import main.java.core.hud.HUD;
import main.java.level.objects.Player;

import java.awt.*;

public class Coin extends Item {

    public HUD hud;

    public Coin(int x, int y, int size, boolean pickedUp, String texturePath, HUD hud) {
        super(x, y, size, pickedUp, texturePath);
        this.hud = hud;
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
        if (!pickedUp) {
            pickedUp = true;
            hud.Coins += 1;
        }
    }
}
