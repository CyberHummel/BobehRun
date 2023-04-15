package main.java.core.hud;
import main.java.core.Window;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
public class MainMenu { //gesamte Klasse selber

    public Rectangle startButton;
    private final Window w;
    Font font;
    Color transparent = new Color(160, 160, 160, 191);

    public boolean active = true;
    Graphics g;
    public MainMenu( Window w){
        this.w = w;
        startButton = new Rectangle((w.FrameWidth/2)-100, (w.FrameHeight/2)-50, 200, 100);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/fonts/RobotoMono-Bold.ttf")));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        font = font.deriveFont(Font.PLAIN, 20);
    }

    public void Render(Graphics g) {
        if(active){
        Graphics2D g2 = (Graphics2D) g;
        this.g = g;
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, w.FrameWidth, w.FrameHeight);
        g2.setColor(Color.BLACK);
        g2.drawString("Main Menu:", (w.FrameWidth /2) - (g.getFontMetrics(font).stringWidth("Main Menu:") /2) , 30);
        g2.setColor(Color.BLUE);
        g2.drawString("BobehRunn", (w.FrameWidth /2) - (g.getFontMetrics(font).stringWidth("Main Menu:") /2), 50);
        g2.setColor(transparent);
        startButton.x = (w.FrameWidth/2) -100;
        startButton.y = (w.FrameHeight/2)-50;
        g2.fill(startButton);
        g2.setColor(Color.BLACK);
        g2.drawString("START", (w.FrameWidth / 2) - (g.getFontMetrics(font).stringWidth("START") /2), (w.FrameHeight /2) +10);
        }
    }

}
