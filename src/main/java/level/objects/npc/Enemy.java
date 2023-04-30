package main.java.level.objects.npc;

import main.java.level.LevelHandler;
import main.java.level.objects.Player;
import main.java.level.objects.tiles.Tile_Manager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Enemy extends Thread {
    public int x;
    private int y;
    private final int sizeY;
    private double vely, velx;
    private final int damage = 10;
    private final double attackSpeed = 1;
    private final double defendSpeed = 1;
    private final int attackDelay = 2000;
    BufferedImage texture_idle, texture_left1, texture_left2, texture_right1, texture_right2, attackLeft, attackRight;
    public BufferedImage texture;
    public boolean dead = false;
    private boolean falling;
    public int health = 100;
    public final int maxhealth = 100;
    private final Tile_Manager tM;
    LevelHandler lH;
    public Rectangle hitboxFeet, hitboxBody;
    int maxDistance;
    public int direction = 0;
    int spriteNum = 1;

    public Enemy(int x, int y, int sizeX, int sizeY, String texturePath, String texturePath_FLeft1, String texturePath_FLeft2, String texturePath_FRight1, String texturePath_FRight2, LevelHandler lH, int maxDistance) {
        this.x = x;
        this.y = y;
        this.sizeY = sizeY;
        hitboxFeet = new Rectangle(x + 20, y - 20, sizeX - 20, 20);
        hitboxBody = new Rectangle(x, y, sizeX + 20, sizeY);
        this.lH = lH;
        this.tM = lH.tileM;
        this.maxDistance = maxDistance;
        try {
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(texturePath)));
            texture_idle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(texturePath)));
            texture_left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(texturePath_FLeft1)));
            texture_left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(texturePath_FLeft2)));
            texture_right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(texturePath_FRight1)));
            texture_right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(texturePath_FRight2)));
            attackLeft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/Wildschwein/WildschweinAttackLeft.png")));
            attackRight = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/Wildschwein/WildschweinAttackRight.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Attack(Player p) {
        if (p.hitboxBody.intersects(hitboxBody)) {
            if (p.health != 0) {
                if (p.directionX == -1) {
                    p.player = p.attackedLeft;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    p.player = p.attackedLeft;
                } else {
                    p.player = p.attackedRight;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    p.player = p.attackedRight;
                }
                if (direction == -1) {
                    texture = attackLeft;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    texture = texture_left1;
                } else if (direction == 1) {
                    texture = attackRight;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    texture = texture_right1;
                }
                p.health -= damage;
                lH.sP.setFile(2);
                lH.sP.Playsound();
                if (p.health <= 0) {
                    p.alive = false;
                    lH.sP.setFile(1);
                    lH.sP.Playsound();
                }
            }
        }
    }

    public void tick() {
        if (health == 0) {
            dead = true;
        }
        if (x <= 0) {
            x = 0;
        }
        hitboxBody.x = x - 5;
        hitboxBody.y = y + 5;
        hitboxFeet.x = x + 10;
        hitboxFeet.y = y + 30;
        if (falling) {
            y += vely;
        }
        if (vely < lH.Gravity) {
            vely += 0.2;
        }

        if (!hitboxBody.intersects(lH.player.hitboxBody)) {
            switch (direction) {
                case 0 -> {
                    if (spriteNum < 10) {
                        spriteNum++;
                        texture = texture_idle;
                        if (spriteNum == 10) {
                            spriteNum = 11;
                        }
                    }
                }
                case -1 -> {
                    if (spriteNum < 10) {
                        spriteNum++;
                        texture = texture_left1;
                        if (spriteNum == 10) {
                            spriteNum = 11;
                        }
                    } else if (spriteNum > 10 && spriteNum < 20) {
                        spriteNum++;
                        texture = texture_left2;
                        if (spriteNum == 20) {
                            spriteNum = 0;
                        }
                    }
                }
                case 1 -> {
                    if (spriteNum < 10) {
                        spriteNum++;
                        texture = texture_right1;
                        if (spriteNum == 10) {
                            spriteNum = 11;
                        }
                    } else if (spriteNum > 10 && spriteNum < 20) {
                        spriteNum++;
                        texture = texture_right2;
                        if (spriteNum == 20) {
                            spriteNum = 0;
                        }
                    }
                }
            }
        }
    }

    public void Colission() {
        if (!dead) {
            for (int i = 0; i < tM.tiles.length; i++) {
                if (tM.tiles[i].collission) {
                    if (hitboxFeet.intersects(tM.tiles[i].hitbox)) {
                        y = lH.tileM.tiles[i].y - sizeY + 2;
                        hitboxFeet.y = y;
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
            Image restexture;
            restexture = texture.getScaledInstance(98, 48, Image.SCALE_SMOOTH);
            g2.setColor(Color.BLACK);
            g2.draw(hitboxFeet);
            g2.draw(hitboxBody);
            g2.drawImage(restexture, x, y, 98, 48, null);
            g2.setColor(Color.RED);
            g2.drawString("Health" + health, x, y);
        }
    }

    public int CalculateDistance(Player p) {
        return (int) (x - p.x);
    }

    public void MoveToPlayer(int maxDistance, Player p) {
        if (!dead) {
            int Distance = CalculateDistance(p);
            if (health >= maxhealth / 2) {
                if (Distance <= maxDistance) {
                    if (Distance > 0 && !hitboxBody.intersects(p.hitboxBody)) {
                        direction = -1;
                        velx = -attackSpeed;
                        x += velx;
                    } else if (Distance < 0 && !hitboxBody.intersects(p.hitboxBody)) {
                        direction = 1;
                        velx = attackSpeed;
                        x += velx;
                    }
                } else {
                    texture = texture_idle;
                }
            } else {
                if (Distance <= maxDistance) {
                    if (Distance > 0) {
                        velx = defendSpeed;
                        direction = 1;
                        x += velx;
                    } else if (Distance < 0) {
                        velx = -defendSpeed;
                        direction = -1;
                        x += velx;
                    }
                }
            }
        }
    }

    public void run() {
        while (health != 0 && lH.player.health != 0) {
            Attack(lH.player);
            try {
                Thread.sleep(attackDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
