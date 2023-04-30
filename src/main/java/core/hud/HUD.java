package main.java.core.hud;

import main.java.core.Window;
import main.java.level.LevelHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class HUD { //gesamte Klasse selber
    public int Coins;
    public Rectangle Exit;
    LevelHandler lH;
    public boolean restarted = false;

    public boolean canPlayerAttack = true;
    Font font;

    Graphics g;
    BufferedImage heart, deadHeart, heartOverlay, attackIndicator1, attackIndicator2;
    Color transparent = new Color(160, 160, 160, 191);
    Rectangle hud = new Rectangle(0, 25, 100, 60);

    Window w;

    public HUD(LevelHandler lH, Window w) {
        this.lH = lH;
        this.w = w;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/fonts/RobotoMono-Bold.ttf")));
            font = font.deriveFont(Font.PLAIN, 20);
            heart = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/HeartIconFull.png")));
            deadHeart = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/HeartIconEmpty.png")));
            heartOverlay = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/Overlay Hearts.png")));
            attackIndicator1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/AttackIndicator_1.png")));
            attackIndicator2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/AttackIndicator_2.png")));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        this.g = g;
        g2.setFont(font);
        g2.setColor(transparent);
        g2.fill(hud);
        g2.setColor(Color.BLACK);
        g2.drawString("COINS:" + Coins, 0, 60);
        HealthBarRender(g2);
        AttackIndicator(g);
    }

    public void HealthBarRender(Graphics2D g2) {
        int amountHearts = lH.player.health / 10;
        switch (amountHearts) {
            case 1:
                g2.drawImage(heartOverlay, 0, 0, 259, 41, null);
                g2.drawImage(heart, 0, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 2, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 3, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 4, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 5, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 6, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 7, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 8, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 9, 10, 24, 24, null);
                break;
            case 2:
                g2.drawImage(heartOverlay, 0, 0, 259, 41, null);
                g2.drawImage(heart, 0, 10, 24, 24, null);
                g2.drawImage(heart, 24, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 2, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 3, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 4, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 5, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 6, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 7, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 8, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 9, 10, 24, 24, null);
                break;
            case 3:
                g2.drawImage(heartOverlay, 0, 0, 259, 41, null);
                g2.drawImage(heart, 0, 10, 24, 24, null);
                g2.drawImage(heart, 24, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 2, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 3, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 4, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 5, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 6, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 7, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 8, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 9, 10, 24, 24, null);
                break;
            case 4:
                g2.drawImage(heartOverlay, 0, 0, 259, 41, null);
                g2.drawImage(heart, 0, 10, 24, 24, null);
                g2.drawImage(heart, 24, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 2, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 3, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 4, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 5, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 6, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 7, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 8, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 9, 10, 24, 24, null);
                break;
            case 5:
                g2.drawImage(heartOverlay, 0, 0, 259, 41, null);
                g2.drawImage(heart, 0, 10, 24, 24, null);
                g2.drawImage(heart, 24, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 2, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 3, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 4, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 5, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 6, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 7, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 8, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 9, 10, 24, 24, null);
                break;
            case 6:
                g2.drawImage(heartOverlay, 0, 0, 259, 41, null);
                g2.drawImage(heart, 0, 10, 24, 24, null);
                g2.drawImage(heart, 24, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 2, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 3, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 4, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 5, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 6, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 7, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 8, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 9, 10, 24, 24, null);
                break;
            case 7:
                g2.drawImage(heartOverlay, 0, 0, 259, 41, null);
                g2.drawImage(heart, 0, 10, 24, 24, null);
                g2.drawImage(heart, 24, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 2, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 3, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 4, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 5, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 6, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 7, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 8, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 9, 10, 24, 24, null);
                break;
            case 8:
                g2.drawImage(heartOverlay, 0, 0, 259, 41, null);
                g2.drawImage(heart, 0, 10, 24, 24, null);
                g2.drawImage(heart, 24, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 2, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 3, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 4, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 5, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 6, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 7, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 8, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 9, 10, 24, 24, null);
                break;
            case 9:
                g2.drawImage(heartOverlay, 0, 0, 259, 41, null);
                g2.drawImage(heart, 0, 10, 24, 24, null);
                g2.drawImage(heart, 24, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 2, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 3, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 4, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 5, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 6, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 7, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 8, 10, 24, 24, null);
                g2.drawImage(deadHeart, 24 * 9, 10, 24, 24, null);
                break;
            case 10:
                g2.drawImage(heartOverlay, 0, 0, 259, 41, null);
                g2.drawImage(heart, 0, 10, 24, 24, null);
                g2.drawImage(heart, 24, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 2, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 3, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 4, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 5, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 6, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 7, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 8, 10, 24, 24, null);
                g2.drawImage(heart, 24 * 9, 10, 24, 24, null);
                break;
        }
    }

    public void DeathScreen(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0, w.getWidth(), w.getHeight());
        g2.setColor(Color.RED);
        g2.drawString("You DIED", w.getWidth()/2-g.getFontMetrics(font).stringWidth("You DIED")/2, w.getHeight()/2);
        Exit = new Rectangle((w.FrameWidth/2)-100, (w.FrameHeight/2)-50, 200, 100);
        Exit.x = (w.FrameWidth/2)-100;
        Exit.y = (w.FrameHeight/2)-100;
        g2.setColor(Color.RED);
        g2.fill(Exit);
        g2.setColor(Color.WHITE);
        g2.drawString("Exit", w.getWidth()/2-g.getFontMetrics(font).stringWidth("Exit")/2 , (w.getHeight()/2)+50);
    }

    public void AttackIndicator(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        if(canPlayerAttack){
            g2.drawImage(attackIndicator2, (int) lH.player.x+(48/2), (int) (lH.player.y-(lH.player.height/3)), 48, 48, null);
        }else {
            g2.drawImage(attackIndicator1, (int) lH.player.x+(48/2), (int) (lH.player.y-(lH.player.height/3)), 48, 48, null);
        }
    }


}
