package main.java.level.objects.backround;

import main.java.level.LevelHandler;

import java.awt.*;

public class BackroundHandler {
    public LevelHandler lH;
    public cloud[] clouds;
    public BackroundHandler(LevelHandler lH){
        this.lH = lH;
    }

    public void BuildClouds(int amount){
        clouds = new cloud[amount];
        clouds[0] = new cloud(100, 200, 0, 100);
    }

    public void updateClouds(double speed){
        for(int i = 0; i < clouds.length; i++){
            if(clouds[i].x <= -clouds[i].Size[0]){
                clouds[i].x = clouds[i].startX;
            }else {
                clouds[i]. x += speed;
            }
        }
    }


    public void Render(Graphics g){
        for(int i = 0; i < clouds.length; i ++){
            clouds[i].Render(g);
        }
    }

}
