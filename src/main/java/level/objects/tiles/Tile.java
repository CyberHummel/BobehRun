package main.java.level.objects.tiles;

import main.java.level.LevelHandler;
import main.java.level.objects.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {     //selber
    public BufferedImage image;
    public boolean collission = false;

    LevelHandler lH;

    public int x;
    public int y;
    public int tileSize;
    public  boolean onPlatform;
    public Rectangle Hitbox = new Rectangle();


    public Tile(int x, int y, int tileSize, int HitboxSize, LevelHandler lh) {
        this.lH = lh;
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
        Hitbox.width = HitboxSize;
        Hitbox.height = HitboxSize;
        Hitbox.x = x;
        Hitbox.y = y;
    }

    public void Render(Graphics g){

        //System.out.println(tileSize);

        Graphics2D g2 = (Graphics2D) g;
        //System.out.println("RENDER");

        //System.out.println(image);
        g2.drawImage(image, x, y ,tileSize, tileSize,null);
        //System.out.println("X RT:" + x);
        //System.out.println("Y RT:" + y);

    }



    public void updateTile(int speed){
        x += speed;
    }

    public void checkCollission(Player player){
        if(player.collisionOn){
            if(collission){
                if(Hitbox.intersects(player.hitBox)){
                    System.out.println("Intersect!");
                    //TODO Gravity etc Jumping
                    onPlatform = true;
                    player.y = y-player.height;
                    System.out.println(player.falling);
                    player.falling = false;
                }
                else{
                    System.out.println("no intersect");
                    }
            }
        }
    }
}
