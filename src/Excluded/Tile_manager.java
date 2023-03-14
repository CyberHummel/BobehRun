package Excluded;

import main.java.level.LevelHandler;
import main.java.level.tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Tile_manager {
    LevelHandler lH;
    public Tile[] tile;
    public boolean levelLoading;




    int[][] mapTileNum;
    int TileRowNum = 16;
    int TileColNum = 12;

    int tileSize = 48;

    public Tile_manager(LevelHandler lH) throws IOException {
        this.lH = lH;
        tile = new Tile[10];
        getTileImage();
        mapTileNum = new int[TileRowNum][TileColNum];
        //setTile();
        loadLevel();

    }

    public void getTileImage(){
        try {

            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/textures/Air.png"));


            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/textures/Grass_Top.png"));


            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/textures/Dirt.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    public void loadLevel(){ //abgewandelt von https://www.youtube.com/watch?v=ugzxCcpoSdE&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=4

        try{
            System.out.println("Level Loading");
            InputStream is = getClass().getResourceAsStream("/leveldata/LevelBasic.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < TileColNum && row < TileRowNum){



                String line = br.readLine();
                System.out.println(line);

                while (col < TileColNum){
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);


                     mapTileNum[col][row] = num;

                    col++;

                }
                if(col == TileColNum){
                    col = 0;
                    row++;
                }
            }



            levelLoading = false;

            br.close();

        }catch (Exception e){

        }
    }


    public void setTile(){
        int col = 0;
        int row = 0;

        int x = 0;
        int y = 0;

        while(col < TileRowNum && row < TileColNum){
            levelLoading = false;
            int tileNum = mapTileNum[col][row];

            tile[tileNum].x = x;
            tile[tileNum].y = y;

            System.out.println(x);

            col++;
            x += tileSize;

            if(col == TileRowNum){
                col = 0;
                x = 0;
                row ++;
                y += tileSize;
            }
            else{
                levelLoading = false;
            }
        }
    }
    public void Render(Graphics g){//abgewandelt von https://www.youtube.com/watch?v=ugzxCcpoSdE&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=4

            Graphics2D g2 = (Graphics2D) g;
            int col = 0;
            int row = 0;

            int x = 0;
            int y = 0;

            while(col < TileColNum && row < TileRowNum){
                int tileNum = mapTileNum[col][row];
                System.out.println("TILENUM" + tileNum);
               tile[tileNum].x = x;
               tile[tileNum].y =y;
                //tile[tileNum].Render(g);
                g2.drawImage(tile[tileNum].image, tile[tileNum].x, tile[tileNum].y ,tileSize, tileSize,null);



                col++;
                x += tileSize;

                if(col == TileColNum && row < TileRowNum){
                    System.out.println("New COl");
                    col = 0;
                    x = 0;
                    row ++;
                    y += tileSize;
                }
            }
        }


    }


