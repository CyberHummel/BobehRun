package main.java.level.objects.npc;

import main.java.level.LevelHandler;
import main.java.level.objects.tiles.Tile_Manager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Enemy {
    private int x, y, sizeX, sizeY;
    private double vely, velx;
    BufferedImage texture;
    public boolean dead = false;
    private boolean falling;

    private Tile_Manager tM;
    private LevelHandler lH;

    public Rectangle hitbox;
    public Enemy(int x, int y, int sizeX, int sizeY, String texturePath, LevelHandler lH){
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        hitbox = new Rectangle(x, y, sizeX, sizeY);
        this.lH = lH;
        this.tM = lH.tileM;

        try {
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(texturePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void tick(){
        hitbox.x = x;
        hitbox.y = y;
        if(falling){
            y += vely;
        }
        if(vely < lH.Gravity){
            vely+= 0.2;
        }
    }

    public void Colission(){
        if(!dead){
            for(int i = 0; i < tM.tiles.length; i ++){
                if(tM.tiles[i].collission){
                    if(hitbox.intersects(tM.tiles[i].hitbox)){
                        y = lH.tileM.tiles[i].y - sizeY + 2;
                        hitbox.y = y;
                        falling = false;
                    }else {
                        falling = true;
                    }
                }
            }
        }
    }

    public void Render(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        if(!dead){
            g2.setColor(Color.BLACK);
            g2.draw(hitbox);
            g2.drawImage(texture, x, y, sizeX, sizeY, null);
        }
    }
}
