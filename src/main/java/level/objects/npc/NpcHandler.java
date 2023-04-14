package main.java.level.objects.npc;

import main.java.level.LevelHandler;
import main.java.level.objects.Player;

import java.awt.*;

public class NpcHandler {

    public Enemy[] enemies;
    protected LevelHandler lH;

    Player p;
    public NpcHandler(int amountEnemies, LevelHandler lH, Player p){
        enemies = new Enemy[amountEnemies];
        this.lH = lH;
        this.p = p;
    }

    public void SpawnNpcs(){
        enemies[0] = new Enemy(400,400, 96, 48, "/main/ressources/textures/Wildschwein.png", lH, 200);
    }

    public void Render(Graphics g){
        for(int i = 0; i < enemies.length; i++){
            enemies[i].Render(g);
        }
    }
    public void tickOn(){
        for(int i = 0; i < enemies.length; i ++){
            enemies[i].start();
            System.out.println("start!");
        }
    }

    public void tick(){
        for(int i = 0; i < enemies.length; i ++){
            enemies[i].tick();
            enemies[i].MoveToPlayer(200, lH.player);
        }
    }


    public void Colission(){
        for(int i = 0; i < enemies.length; i ++){
            enemies[i].Colission();
        }
    }
}
