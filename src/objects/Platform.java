package objects;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Platform extends Obstacle {
    int height;
    int width;
    int x;
    int y;

    int maxWidth;
    int maxHeight;

    private BufferedImage textureMain;
    private BufferedImage textureDown;

    private String path_textureMain;
    private String path_textureDown;
    Color c;

    public Platform(byte ID, int x, int y, int width, int height, String path_textureMain, String path_textureDown) {
        super(ID);

        this.path_textureDown = path_textureDown;
        this.path_textureMain = path_textureMain;
        this.c=c;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    @Override
    public void updateCords(int speed) {
        this.x -= speed;
    }

    public void Render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        try {
            textureMain= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path_textureMain)));
            textureDown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path_textureDown)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        maxWidth = textureMain.getWidth();
        maxHeight = 88;

        g2.drawImage(textureMain, x, y, null);
        g2.drawImage(textureDown, x, ((y+maxHeight )- 5), null);
    }

    public void tick() {

    }
}
