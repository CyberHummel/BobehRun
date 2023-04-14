package main.java.level.objects;

import main.java.core.Window;
import main.java.level.LevelHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player {       //Gestamte Klasse selber geschrieben
    public Window w;
    public boolean jumping;
    public int width, height;
    public int health = 100;
    public boolean jumpable;
    public double x, y;
    public double velx, vely;
    public double JumpVelocity = 5;
    public int speed = 2;

    public boolean falling = true;


    public Rectangle hitBox;
    public boolean collisionOn = true;

    public LevelHandler lH;

    public Player(Window w, int x, int y, int width, int height, LevelHandler lH) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.lH = lH;
        hitBox = new Rectangle(x, y, width, height);

    }

    public void tick() {

        hitBox.x = (int) x;
        hitBox.y = (int) y;


        if ((w.FrameWidth / 2) == x && !w.Keylistener.MovingLeft) {
            x = w.FrameWidth / 2;
            if (w.Keylistener.Moving) {
                w.level.updateObstacles(-2, -0.5, -0.3);
            } else {
                w.level.updateObstacles(0, 0, 0);
            }
        } else {
            x += velx;
            w.level.updateObstacles(0, 0, 0);
        }

        if (x <= 0 && w.Keylistener.MovingLeft) {
            x = 0.0;

        }

        if (jumping || falling) {
            y += vely;
            jumping = false;
        }


        if (vely < lH.Gravity) {
            vely += 0.2;
        }

    }

    public void Collision() {
        if (collisionOn && !jumping) {
            for (int i = 0; i < lH.tileM.tiles.length; i++) {
                if (lH.tileM.tiles[i].collission) {
                    if (hitBox.intersects(lH.tileM.tiles[i].hitbox)) {
                        y = lH.tileM.tiles[i].y - height + 2;
                        hitBox.y = (int) y;
                        jumpable = true;
                        falling = false;
                    } else {
                        falling = true;
                    }
                }
            }
        } else {
            jumpable = false;
            falling = true;
        }

    }

    public void ItemCollission() {
        for (int i = 0; i < lH.itemM.items.length; i++) {
            if (hitBox.intersects(lH.itemM.items[i].hitbox)) {
                lH.itemM.items[i].pickUp(this);
            }
        }
    }


    public void Render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Image resplayer;
        try {
            BufferedImage player = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/player.png")));
            resplayer = player.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
        g2.drawImage(resplayer, (int) x, (int) y, null);



    }
}
