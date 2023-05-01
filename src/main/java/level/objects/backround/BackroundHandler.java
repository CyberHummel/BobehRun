package main.java.level.objects.backround;

import main.java.level.LevelHandler;

import java.awt.*;

public class BackroundHandler {//alles selber geschrieben
    public LevelHandler lH;
    cloud[] clouds;
    AbstractBackround[] abstractBackrounds;

    public BackroundHandler(LevelHandler lH) {
        this.lH = lH;
    }

    public void Build(int amountClouds, int amountAbstractBackrounds) {
        clouds = new cloud[amountClouds];
        clouds[0] = new cloud(100, 500, 1, 100);
        clouds[1] = new cloud(800, 300, 1, 800);
        clouds[2] = new cloud(1200, 400, 1, 1200);
        clouds[3] = new cloud(1700, 300, 1, 1700);

        clouds[4] = new cloud(400, 200, 0, 400);
        clouds[5] = new cloud(1000, 100, 0, 400);
        clouds[6] = new cloud(1500, 140, 0, 400);

        abstractBackrounds = new AbstractBackround[amountAbstractBackrounds];
        abstractBackrounds[0] = new AbstractBackround(900, 700, 385, 301, 0);
        abstractBackrounds[1] = new AbstractBackround(0, 400, 800, 853, 1);
    }

    public void updateClouds(double speed) {
        for (int i = 0; i < clouds.length; i++) {
            if (clouds[i].x <= -clouds[i].Size[0]) {
                clouds[i].x = clouds[i].startX + 700;
            } else {
                clouds[i].x += speed;
            }
        }
    }

    public void updateAbstractBackrounds(double speed) {
        for (int i = 0; i < abstractBackrounds.length; i++) {
            if (abstractBackrounds[i].x <= -abstractBackrounds[i].sizeX) {
                abstractBackrounds[i].x = abstractBackrounds[i].startX + 700;
            } else {
                abstractBackrounds[i].x += speed;
            }
        }
    }

    public void Render(Graphics g) {
        for (int y = 0; y < abstractBackrounds.length; y++) {
            abstractBackrounds[y].Render(g);
        }
        for (int i = 0; i < clouds.length; i++) {
            clouds[i].Render(g);
        }
    }
}
