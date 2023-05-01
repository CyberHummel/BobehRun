package main.java.level;

import main.java.core.hud.HUD;
import main.java.core.sound.SoundPlayer;
import main.java.level.objects.backround.BackroundHandler;
import main.java.level.objects.items.Item_Manager;
import main.java.level.objects.npc.NpcHandler;
import main.java.level.objects.tiles.Tile_Manager;
import main.java.core.Window;
import main.java.level.objects.Player;

import java.awt.*;

//gesamte Classe seber
public class LevelHandler extends Thread {
    Graphics g;
    public NpcHandler npcH;
    public SoundPlayer sP = new SoundPlayer();
    boolean inited = false;
    public double Gravity = 4;
    public Player player;
    public Tile_Manager tileM = new Tile_Manager(this, 128, 22, 48);
    public Item_Manager itemM = new Item_Manager();
    public BackroundHandler bM = new BackroundHandler(this);
    public HUD hud;
    public Window w;

    public void Run(Graphics g) {
        if (!inited) {
            INITG(g);
            inited = true;
        }
        Render(g);
        this.g = g;
    }

    public LevelHandler(Window w) {
        this.w = w;
        player = new Player(w, 100, 100, 84, 84, this);
        hud = new HUD(this, w);
        System.out.println(player);

        tileM.getImages();
        tileM.readLevelData("/main/ressources/leveldata/LevelBasic.txt");
        tileM.buildLevel();

        itemM.loadItems(this);

        System.out.println(g);
        bM.Build(7, 2);

        sP.setFile(0);
        sP.Playsound();
        sP.PlayBackround();
    }

    public void INITG(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        npcH = new NpcHandler(1, this, player);
        npcH.SpawnNpcs(g2);
        npcH.tickOn();
        System.out.println(g2);
    }

    public void updateObstacles(int speed, double cloudSpeed, double abstractBackroundSpeed) {
        for (int i = 0; i < tileM.tiles.length; i++) {
            tileM.tiles[i].updateTile(speed);
        }
        for (int i = 0; i < npcH.enemies.length; i++) {
            npcH.enemies[i].x += speed;
        }
        itemM.updateSpeed(speed);
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
        if (player.health <= 0) {
            hud.DeathScreen(g);
            if (player.alive) {
                player.alive = false;
            }
        }
        if (w.mM.active) {
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

