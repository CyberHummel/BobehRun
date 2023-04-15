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

    public boolean canAttack_Q = true;
    public boolean jumping;
    public int width, height;
    public int health = 100;
    public boolean jumpable;
    public double x, y;
    public double velx, vely;
    public double JumpVelocity = 5;
    public int speed = 2;
    public boolean falling = true;
    public boolean alive = true;
    public int attackDelay_Q = 2500;
    public Rectangle hitBoxFeet, hitboxBody ;
    public boolean collisionOn = true;
    public LevelHandler lH;

    public int attackDamage_Q = 25;

    public Player(Window w, int x, int y, int width, int height, LevelHandler lH) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.lH = lH;
        hitBoxFeet = new Rectangle(x+(width/8), y+(height-10), width/4, 10);
        hitboxBody = new Rectangle(x+(width/4), y, width/2, height);

    }

    public void tick() {

        hitBoxFeet.x = (int) x+(width/3 + 5);
        hitBoxFeet.y = (int) y+(height-10);
        hitboxBody.x = (int) x+(width/4);
        hitboxBody.y = (int) y -10;

        for (int i = 0; i < lH.tileM.tiles.length; i++){
            if(lH.tileM.tiles[i].collission){
                if(lH.tileM.tiles[i].hitbox.intersects(hitboxBody)){
                    y -= 8;
                    hitboxBody.y = (int) y;
                    hitBoxFeet.y = (int) y;
                    vely = -JumpVelocity;
                }
            }
        }

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
                    if (hitBoxFeet.intersects(lH.tileM.tiles[i].hitbox)) {
                        y = lH.tileM.tiles[i].y - height + 2;
                        hitBoxFeet.y = (int) y;
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
            if (hitBoxFeet.intersects(lH.itemM.items[i].hitbox)) {
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
        g2.drawRect(hitBoxFeet.x, hitBoxFeet.y, hitBoxFeet.width, hitBoxFeet.height);
        g2.draw(hitboxBody);
        g2.drawImage(resplayer, (int) x, (int) y, null);

    }
    public void Attack_Q(){
        for(int i = 0; i < lH.npcH.enemies.length; i++){
            if(hitboxBody.intersects(lH.npcH.enemies[i].hitbox)){
                canAttack_Q = false;
                lH.npcH.enemies[i].health -= attackDamage_Q;
                System.out.println(lH.npcH.enemies[i].health);
            }else {
                System.out.println("You cannot attack!");
            }
        }
    }
}
