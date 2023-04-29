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
    public int directionX = 0;

    BufferedImage left1, left2, right1, right2, idle1, idle2, PunshRight, PunshLeft;
    BufferedImage player;
    int spriteNum = 1;

    public Player(Window w, int x, int y, int width, int height, LevelHandler lH) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.lH = lH;
        hitBoxFeet = new Rectangle(x+(width/8), y+(height-10), width/4, 10);
        hitboxBody = new Rectangle(x+(width/4), y+40, width/2, 60);
        getPlayerSprites();
        player = left1;
        alive = true;
    }

    public void tick() {

        hitBoxFeet.x = (int) x+(width/3 + 5);
        hitBoxFeet.y = (int) y+(height-10);
        hitboxBody.x = (int) x+(width/4);
        hitboxBody.y = (int) y +20;

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

        switch (directionX){
            case 0:{
                if(spriteNum < 10){
                    spriteNum ++;
                    player = idle1;
                    if(spriteNum == 10){
                        spriteNum = 11;
                    }
                }else if(spriteNum > 10 && spriteNum < 20){
                    spriteNum++;
                    player = idle2;
                    if(spriteNum == 20){
                        spriteNum = 0;
                    }
            }
                break;
            }
            case -1:{
                if(spriteNum < 10){
                    spriteNum ++;
                    player = left1;
                    if(spriteNum == 10){
                        spriteNum = 11;
                    }
                }else if(spriteNum > 10 && spriteNum < 20){
                    spriteNum++;
                    player = left2;
                    if(spriteNum == 20){
                        spriteNum = 0;
                    }
                }
                break;
            }


            case 1:{
                if(spriteNum < 10){
                    spriteNum ++;
                    player = right1;
                    if(spriteNum == 10){
                        spriteNum = 11;
                    }
                }else if(spriteNum > 10 && spriteNum < 20){
                    spriteNum++;
                    player = right2;
                    if(spriteNum == 20){
                        spriteNum = 0;
                    }
                }
                break;
            }


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
                if(!lH.itemM.items[i].pickedUp){
                lH.itemM.items[i].pickUp(this);
                lH.sP.setFile(0);
                lH.sP.Playsound();
                }
            }
        }
    }


    public void getPlayerSprites(){
        try {
             right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/player/runningRight1.png")));
             left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/player/runningLeft1.png")));
             right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/player/runningRight2.png")));
             left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/player/runningLeft2.png")));
             idle1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/player/Idle1.png")));
             idle2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/player/Idle2.png")));
             PunshRight = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/player/PlayerPunshRight.png")));
             PunshLeft= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/player/PlayerPunshLeft.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Image resplayer;
        resplayer = player.getScaledInstance(width, height, Image.SCALE_SMOOTH);


        g2.drawRect(hitBoxFeet.x, hitBoxFeet.y, hitBoxFeet.width, hitBoxFeet.height);
        g2.draw(hitboxBody);
        g2.drawImage(resplayer, (int) x, (int) y, null);

    }
    public void Attack_Q(){
        if(directionX == -1){
            player = PunshLeft;
        } else if (directionX == 1 || directionX == 0) {
            player = PunshRight;
        }
        lH.sP.setFile(5);
        lH.sP.Playsound();
        for(int i = 0; i < lH.npcH.enemies.length; i++){
            if(hitboxBody.intersects(lH.npcH.enemies[i].hitboxBody)){
                canAttack_Q = false;
                lH.npcH.enemies[i].health -= attackDamage_Q;
                if(lH.npcH.enemies[i].health <= 0){
                    lH.npcH.enemies[i].dead = true;
                }
                if(lH.npcH.enemies[i].dead){
                    lH.sP.setFile(3);
                    lH.sP.Playsound();
                }
                System.out.println(lH.npcH.enemies[i].health);
            }else {
                canAttack_Q = true;
                System.out.println("You cannot attack!");
            }
        }
    }
}
