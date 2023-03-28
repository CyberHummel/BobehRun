package main.java.level.objects.npc;

import main.java.level.LevelHandler;

import java.awt.*;

public class NpcHandler {

    public Enemy[] enemies;
    protected LevelHandler lH;
    public NpcHandler(int amountEnemies, LevelHandler lH){
        enemies = new Enemy[amountEnemies];
        this.lH = lH;
    }

    public void SpawnNpcs(){
        enemies[0] = new Enemy(400,400, 96, 48, "/main/ressources/textures/Wildschwein.png", lH);
    }

    public void Render(Graphics g){
        for(int i = 0; i < enemies.length; i++){
            enemies[i].Render(g);
        }
    }

    public void Tick(){
        for(int i = 0; i < enemies.length; i ++){
            enemies[i].tick();
            enemies[i].MoveToPlayer(200,lH.player);
        }
    }

    public void Colission(){
        for(int i = 0; i < enemies.length; i ++){
            enemies[i].Colission();
        }
    }
}
