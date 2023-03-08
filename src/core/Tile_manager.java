package core;

import level.LevelHandler;
import level.tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Tile_manager {
    LevelHandler lH;
    public Tile[] tile;



    int[][] mapTileNum;
    int TileRowNum = 16;
    int TileColNum = 12;

    int tileSize = 48;

    public Tile_manager(LevelHandler lH){
        this.lH = lH;
        tile = new Tile[10];
        getTileImage();
        mapTileNum = new int[TileRowNum][TileColNum];
        loadLevel();
    }

    public void getTileImage(){         //abgwandelt von https://www.youtube.com/watch?v=ugzxCcpoSdE&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=4
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/textures/Air.png"));
            tile[0].collission = false;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/textures/Grass.png"));
            tile[1].collission = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/textures/Dirt.png"));
            tile[2].collission = true;

        }catch (IOException e){
            e.printStackTrace();
        }

    }


    public void loadLevel(){ //abgewandelt von https://www.youtube.com/watch?v=ugzxCcpoSdE&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=4

        try{
            System.out.println("Level Loading");
            InputStream is = getClass().getResourceAsStream("/level/tiles/leveldata/LevelBasic.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < TileRowNum && row < TileColNum){

                String line = br.readLine();

                while (col < TileRowNum){
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == TileRowNum){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){

        }
    }
    public void draw(Graphics2D g2, Window w){ //abgewandelt von https://www.youtube.com/watch?v=ugzxCcpoSdE&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=4

        int col = 0;
        int row = 0;

        int x = 0;
        int y = 0;

        while(col < TileRowNum && row < TileColNum){
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, tileSize, tileSize, null);
            tile[tileNum].Hitbox.height = tileSize;
            tile[tileNum].Hitbox.width = tileSize;
            tile[tileNum].Hitbox.x = x;
            tile[tileNum].Hitbox.y = y;

            col++;
            x += tileSize;

            if(col == TileRowNum){
                col = 0;
                x = 0;
                row ++;
                y += tileSize;
            }
        }

    }
}
