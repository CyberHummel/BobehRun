package main.java.level.objects.items;

import main.java.level.objects.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public abstract class Item {
    public int x, y, size;
    public boolean pickedUp = false;

    public Rectangle hitbox = new Rectangle();

    public BufferedImage image;

    public Item(int x, int y, int size, boolean pickedUp, String texturePath) {
        this.x = x;
        this.y = y;
        this.size = size;
        hitbox.x = x;
        hitbox.y = y;
        hitbox.width = size;
        hitbox.height = size;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(texturePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public abstract void Render(Graphics g);


    public abstract void updateCoords(int speed);

    public abstract void pickUp(Player p);


}
