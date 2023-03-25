package main.java.core.hud;

import main.java.level.LevelHandler;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class HUD{
    LevelHandler lH;
    public int Coins;
    Font font;

    Color transparent = new Color(160, 160, 160, 191);
    Rectangle hud = new Rectangle(0, 25, 100, 50);

    public HUD(LevelHandler lH){
        this.lH = lH;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/fonts/RobotoMono-Bold.ttf")));
            font = font.deriveFont(Font.PLAIN, 20);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Render(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(font);
        g2.setColor(transparent);
        g2.fill(hud);
        g2.setColor(Color.BLACK);
        g2.drawString("COINS:" + Coins, 0, 50);
    }
}
