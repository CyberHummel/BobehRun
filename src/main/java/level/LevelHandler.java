package main.java.level;

import main.java.core.hud.HUD;
import main.java.level.objects.backround.BackroundHandler;
import main.java.level.objects.items.Item_Manager;
import main.java.level.objects.npc.NpcHandler;
import main.java.level.objects.tiles.Tile_Manager;
import main.java.core.Window;
import main.java.level.objects.Player;
import java.awt.*;

//gesamte Classe seber
public class LevelHandler extends Thread{

    public void Run(Graphics g){
        Render(g);
    }
    public double Gravity = 4;

    public Player player;
    public Tile_Manager tileM = new Tile_Manager(this, 32, 12, 48);

    public Item_Manager itemM = new Item_Manager();

    public BackroundHandler bM = new BackroundHandler(this);

    public HUD hud;

    public NpcHandler npcH;
    public Window w;

    public LevelHandler(Window w) {
        this.w = w;
        player = new Player(w, 100, 100, 84, 84, this);
        npcH = new NpcHandler(1,this, player);
        hud =  new HUD(this, w);
        System.out.println(player);

        tileM.getImages();
        tileM.readLevelData("/main/ressources/leveldata/LevelBasic.txt");
        tileM.buildLevel();

        itemM.loadItems(this);
        npcH.SpawnNpcs();

        bM.Build(2, 1);
        npcH.tickOn();

    }

    public void updateObstacles(int speed, double cloudSpeed, double abstractBackroundSpeed) {
        for (int i = 0; i < tileM.tiles.length; i++) {
            tileM.tiles[i].updateTile(speed);
        }
        bM.updateClouds(cloudSpeed);
        bM.updateAbstractBackrounds(abstractBackroundSpeed);
    }


    public void Render(Graphics g) {
        tileM.RenderAir(g);
        bM.Render(g);
        tileM.RenderNonAir(g);
        player.Render(g);
        npcH.Render(g);

        itemM.Render(g);



        hud.Render(g);
        if(player.health <= 0){
            hud.DeathScreen(g);
            if(player.alive){

                player.alive = false;
            }
        }

        if(w.mM.active){
            w.mM.Render(g);
        }
    }

    public void tick() {


        player.Collision();
        player.ItemCollission();
        player.tick();
        npcH.Colission();
        npcH.tick();


    }

}

