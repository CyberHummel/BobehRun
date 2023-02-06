package Objects;


import java.awt.*;

public class Plattform extends Item {
    int height;
    int width;
    int x;
    int y;
    Color c;

    public Plattform(byte ID, int x, int y, int width, int height, Color c) {
        super(ID);

        this.c=c;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    public void Render(Graphics g) {
        g.setColor(c);
        g.fillRect(x, y, width, height);

    }

    public void tick() {

    }
}
