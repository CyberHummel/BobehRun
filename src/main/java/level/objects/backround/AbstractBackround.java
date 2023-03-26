package main.java.level.objects.backround;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class AbstractBackround {
    int x, y, sizeX, sizeY, startX;
    BufferedImage texture;
    String bigTexturePath = "/main/ressources/textures/Abstract Backround 1.png";
    public AbstractBackround(int x, int y, int sizeX, int sizeY, int size){
        String Path = "";
        this.startX = x;
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;


        if(size == 0){
            Path = bigTexturePath;
        }
        try {
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Render(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(texture, x, y, sizeX, sizeY, null);
    }
}
