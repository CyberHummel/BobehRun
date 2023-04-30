package main.java.core.hud;

import main.java.core.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MainMenu { //gesamte Klasse selber
    public Rectangle startButton;
    private final Window w;
    Font font;
    Color transparent = new Color(160, 160, 160, 191);
    BufferedImage A_Key, D_Key, Q_Key, Space_Key;
    public boolean active = true;
    Graphics g;

    public MainMenu(Window w) {
        this.w = w;
        startButton = new Rectangle((w.FrameWidth / 2) - 100, (w.FrameHeight / 2) - 50, 200, 100);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/fonts/RobotoMono-Bold.ttf")));
            A_Key = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/mainMenu/A-KeyCap.png")));
            D_Key = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/mainMenu/D-KeyCap.png")));
            Q_Key = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/mainMenu/Q-KeyCap.png")));
            Space_Key = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/mainMenu/Space-Key.png")));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        font = font.deriveFont(Font.PLAIN, 20);
    }

    public void Render(Graphics g) {
        if (active) {
            Graphics2D g2 = (Graphics2D) g;
            this.g = g;
            g2.setFont(font);
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, w.FrameWidth, w.FrameHeight);
            g2.setColor(Color.BLACK);
            g2.drawString("Main Menu:", (w.FrameWidth / 2) - (g.getFontMetrics(font).stringWidth("Main Menu:") / 2), 30);
            g2.setColor(Color.BLUE);
            g2.drawString("BobehRunn", (w.FrameWidth / 2) - (g.getFontMetrics(font).stringWidth("Main Menu:") / 2), 50);
            g2.setColor(transparent);
            startButton.x = (w.FrameWidth / 2) - 100;
            startButton.y = (w.FrameHeight / 2) - 50;
            g2.fill(startButton);
            g2.setColor(Color.BLACK);
            g2.drawString("START", (w.FrameWidth / 2) - (g.getFontMetrics(font).stringWidth("START") / 2), (w.FrameHeight / 2) + 10);
            g2.drawImage(Q_Key, (w.FrameWidth / 2) - 48, (w.FrameHeight / 2) - 384, 96, 96, null);
            g2.drawString("Attack", (int) ((w.FrameWidth / 2) - (g.getFontMetrics(font).stringWidth("Attack") / 2)), (w.FrameHeight / 2) - 384);
            g2.drawImage(A_Key, (w.FrameWidth / 2) - 96, (w.FrameHeight / 2) - 288, 96, 96, null);
            g2.drawString("GoLeft", (int) (((w.FrameWidth / 2) - 48) - (g.getFontMetrics(font).stringWidth("Attack") * 1.75)), (w.FrameHeight / 2) - 240);
            g2.drawImage(D_Key, (w.FrameWidth / 2), (w.FrameHeight / 2) - 288, 96, 96, null);
            g2.drawString("GoRight", (int) (((w.FrameWidth / 2) - 24) + (g.getFontMetrics(font).stringWidth("Attack") * 1.75)), (w.FrameHeight / 2) - 240);
            g2.drawImage(Space_Key, (int) ((w.FrameWidth / 2) - (96 * 1.5)), (w.FrameHeight / 2) - 370, 288, 96 * 4, null);
            g2.drawString("JUMP", (w.FrameWidth / 2) - (g.getFontMetrics(font).stringWidth("START") / 2), (w.FrameHeight / 2) - 70);
        }
    }
}

