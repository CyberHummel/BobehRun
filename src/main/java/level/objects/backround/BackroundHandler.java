package main.java.level.objects.backround;

import main.java.level.LevelHandler;

import java.awt.*;

public class BackroundHandler {//alles selber geschrieben
    public LevelHandler lH;
    cloud[] clouds;
    AbstractBackround[] abstractBackrounds;
    public BackroundHandler(LevelHandler lH){
        this.lH = lH;
    }

    public void Build(int amountClouds, int amountAbstractBackrounds){
        clouds = new cloud[amountClouds];
        clouds[0] = new cloud(100, 200, 0, 100);
        clouds[1] = new cloud(400, 200, 1, 400);
        abstractBackrounds = new AbstractBackround[amountAbstractBackrounds];
        abstractBackrounds[0] = new AbstractBackround(0, 200, 385, 301, 0);
    }

    public void updateClouds(double speed){
        for(int i = 0; i < clouds.length; i++){
            if(clouds[i].x <= -clouds[i].Size[0]){
                clouds[i].x = clouds[i].startX + 700;
            }else {
                clouds[i]. x += speed;
            }
        }
    }

    public void updateAbstractBackrounds(double speed){
        for(int i = 0; i < abstractBackrounds.length; i++){
            if(abstractBackrounds[i].x <= -abstractBackrounds[i].sizeX){
                abstractBackrounds[i].x = abstractBackrounds[i].startX + 700;
            }else {
                abstractBackrounds[i]. x += speed;
            }
        }
    }


    public void Render(Graphics g){
        for(int y = 0; y < abstractBackrounds.length; y++){
            abstractBackrounds[y].Render(g);
        }
        for(int i = 0; i < clouds.length; i ++){
            clouds[i].Render(g);
        }
    }
}
