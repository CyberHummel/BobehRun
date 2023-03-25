package main.java.level.objects.tiles;

import main.java.level.LevelHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {     //selber geschrieben
    public BufferedImage image;
    public boolean collission = false;

    LevelHandler lH;

    public int x;
    public int y;
    public int tileSize;
    public Rectangle hitbox = new Rectangle();


    public Tile(int x, int y, int tileSize, int HitboxSize, LevelHandler lh) {
        this.lH = lh;
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
        hitbox.width = HitboxSize;
        hitbox.height = HitboxSize;
        hitbox.x = x;
        hitbox.y = y;
    }

    public void Render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, x, y, tileSize, tileSize, null);
    }


    public void updateTile(int speed) {
        hitbox.x += speed;
        x += speed;
    }

}
