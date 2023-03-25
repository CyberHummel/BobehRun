package main.java.level.objects.backround;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class cloud {
    public double x, y;
    public int size;
    public  int startX;
    public BufferedImage texture;

    String cloudSmallPath = "/main/ressources/textures/Cloud_Small.png";
    String cloudBigPath = "/main/ressources/textures/Cloud_Big.png";

    int[] Size = new int[2];

    String Path;

    public cloud(int x, int y, int size, int startX) {
        this.x = x;
        this.y = y;
        this.startX = startX;
        this.size = size;
        if (size == 0) {
            Path = cloudSmallPath;
            Size[0] = 192;
            Size[1] =  96;
        } else if (size == 1) {
            Path = cloudBigPath;
            Size[0] = 348;
            Size[1] = 192;
        }
        try {
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Render(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(texture, (int) x, (int) y, Size[0], Size[1], null);
    }




}

