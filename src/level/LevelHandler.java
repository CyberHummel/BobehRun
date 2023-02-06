package level;

import Objects.Plattform;
import Objects.Item;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class LevelHandler {
    //Vars
    public double Gravity = 8;

    public LinkedList<Item> items = new LinkedList<Item>();

    public int floorHeight = 400;
    //vars
    private int seed;
    //runs when lvl is created
    public LevelHandler(){
        Random r = new Random();
        seed = r.nextInt();

       items.add(new Plattform(Item.Platform,100, 300 ,100, 4, Color.CYAN));
    }

    public void Render(Graphics g){
        for(Item i : items){
            i.Render(g);
        }
    }

    public void tick(){
        for(Item i : items){
            i.tick();
        }
    }
}
