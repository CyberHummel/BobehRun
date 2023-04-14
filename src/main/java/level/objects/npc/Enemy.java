package main.java.level.objects.npc;
import main.java.level.LevelHandler;
import main.java.level.objects.Player;
import main.java.level.objects.tiles.Tile_Manager;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Enemy extends Thread{


    private int x, y, sizeX, sizeY;

    private double vely, velx;
    private int damage = 10;

    private int attackDelay = 1000;
    BufferedImage texture;
    public boolean dead = false;
    private boolean falling;
    public int health = 100;
    private Tile_Manager tM;
     LevelHandler lH;
    public Rectangle hitbox;
    int maxDistance;



    public Enemy(int x, int y, int sizeX, int sizeY, String texturePath, LevelHandler lH, int maxDistance) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        hitbox = new Rectangle(x, y, sizeX, sizeY);
        this.lH = lH;
        this.tM = lH.tileM;
        this.maxDistance = maxDistance;


        try {
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(texturePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Attack(Player p){
        if(p.hitBox.intersects(hitbox)){
        if(p.health != 0){
            p.health -= damage;
        }
        }

    }

    public void tick() {
        hitbox.x = x;
        hitbox.y = y;
        if (falling) {
            y += vely;
        }
        if (vely < lH.Gravity) {
            vely += 0.2;
        }
    }
    public void Colission() {
        if (!dead) {
            for (int i = 0; i < tM.tiles.length; i++) {
                if (tM.tiles[i].collission) {
                    if (hitbox.intersects(tM.tiles[i].hitbox)) {
                        y = lH.tileM.tiles[i].y - sizeY + 2;
                        hitbox.y = y;
                        falling = false;
                    } else {
                        falling = true;
                    }
                }
            }
        }
    }


    public void Render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (!dead) {
            g2.setColor(Color.BLACK);
            g2.draw(hitbox);
            g2.drawImage(texture, x, y, sizeX, sizeY, null);
        }
    }

    public int CalculateDistance(Player p) {
        return (int) (x - p.x);
    }

    public void MoveToPlayer(int maxDistance, Player p) {
        if (!dead) {
            int Distance = CalculateDistance(p);
            if (health >= 50) {
                if (Distance <= maxDistance) {
                    if (Distance > 0 && !hitbox.intersects(p.hitBox)) {
                        velx = -1.5;
                        Distance = CalculateDistance(p);
                        x += velx;
                    } else if (Distance < 0 && !hitbox.intersects(p.hitBox)) {
                        velx = 1.5;
                        Distance = CalculateDistance(p);
                        x += velx;

                    }
                }
            }else{
                if(Distance <= maxDistance){
                    if (Distance > 0) {
                        velx = 1.5;
                        Distance = CalculateDistance(p);
                        x += velx;
                    } else if (Distance < 0) {
                        velx = -1.5;
                        Distance = CalculateDistance(p);
                        x += velx;

                    } else if (hitbox.intersects(p.hitBox)) {
                        velx = 1.5;
                        x += velx;
                        Distance = CalculateDistance(p);
                    }
                }
            }
        }
    }

    public void run(){
        while (health != 0 && lH.player.health != 0){
            Attack(lH.player);
            try {
                Thread.sleep(attackDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
