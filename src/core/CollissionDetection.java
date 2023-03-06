package core;

import level.tiles.Tile;
import objects.Player;

//gesamte Classe seber
public class CollissionDetection {

    public CollissionDetection(){

    }

    public void collissionDetectTile(Player player, Tile t){
        if(player.collisionOn){
            if(t.collission){
                if(player.hitBox.intersects(t.Hitbox)){
                    player.falling = false;
                    player.vely = 0;
                }
            }

        }
    }
}
