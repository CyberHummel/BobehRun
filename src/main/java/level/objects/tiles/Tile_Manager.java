package main.java.level.objects.tiles;

import main.java.level.LevelHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;


public class Tile_Manager { //alles selber bis auf readlevelData()

    LevelHandler lH;
    public Tile[] tiles;

    int tileSize;

    int maxCols;
    int maxRows;
    public BufferedImage tileAirImage;
    public BufferedImage tileGrassImage;
    public BufferedImage tileDirtImage;
    public BufferedImage tileGrassLeftImage;
    public BufferedImage tileGrassRightImage;
    public BufferedImage tileGrassTopLeftImage;
    public BufferedImage tileGrassTopRightImage;


    int[][] mapTileNum;

    public Tile_Manager(LevelHandler lH, int maxCols, int maxRows, int tileSize) {
        this.lH = lH;
        this.tileSize = tileSize;
        this.maxCols = maxCols;
        this.maxRows = maxRows;

        tiles = new Tile[maxCols * maxRows];

        mapTileNum = new int[maxCols][maxRows];


    }

    public void getImages() {
        try {
            tileAirImage = ImageIO.read(new BufferedInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/Air.png"))));

            tileGrassImage = ImageIO.read(new BufferedInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/Grass_Top.png"))));

            tileDirtImage = ImageIO.read(new BufferedInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/Dirt.png"))));


            tileGrassLeftImage = ImageIO.read(new BufferedInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/Grass_Left.png"))));

            tileGrassRightImage = ImageIO.read(new BufferedInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/Grass_Right.png"))));

            tileGrassTopLeftImage = ImageIO.read(new BufferedInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/Grass_TopLeft.png"))));

            tileGrassTopRightImage = ImageIO.read(new BufferedInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/main/ressources/textures/Grass_TopRight.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readLevelData(String path) {
        System.out.println("Reading Level Data from:" + path);
        try {
            File file = new File(Objects.requireNonNull(getClass().getResource(path)).getPath());
            BufferedReader br = new BufferedReader(new FileReader(file));

            int col = 0;
            int row = 0;

            while (col < maxCols && row < maxRows) {
                String line = br.readLine();

                while (col < maxCols) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;

                    col++;

                }
                if (col == maxCols) {

                    col = 0;
                    row++;
                }
            }
            System.out.println("Loading Finished!");
            br.close();
            System.out.println(Arrays.deepToString(mapTileNum));

        } catch (Exception e) {
            System.out.println("ERROR could not read file");

        }
    }

    public void buildLevel() {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        int currentTile = 0;

        while (col < maxCols && row < maxRows) {

            if (currentTile < maxCols * maxRows) {
                int tileCurrentNum = mapTileNum[col][row];
                tiles[currentTile] = new Tile(x, y, tileSize, tileSize, lH);
                tiles[currentTile].x = x;
                tiles[currentTile].y = y;
                if (tileCurrentNum == 0) {
                    tiles[currentTile].image = tileAirImage;
                    tiles[currentTile].collission = false;

                } else if (tileCurrentNum == 1) {
                    tiles[currentTile].image = tileGrassImage;
                    tiles[currentTile].collission = true;
                } else if (tileCurrentNum == 2) {
                    tiles[currentTile].image = tileDirtImage;
                    tiles[currentTile].collission = true;
                } else if (tileCurrentNum == 3) {
                    tiles[currentTile].image = tileGrassLeftImage;
                    tiles[currentTile].collission = true;
                } else if (tileCurrentNum == 4) {
                    tiles[currentTile].image = tileGrassRightImage;
                    tiles[currentTile].collission = true;
                } else if (tileCurrentNum == 5) {
                    tiles[currentTile].image = tileGrassTopLeftImage;
                    tiles[currentTile].collission = true;
                } else if (tileCurrentNum == 6) {
                    tiles[currentTile].image = tileGrassTopRightImage;
                    tiles[currentTile].collission = true;
                }

                currentTile++;

                col++;

                x += tileSize;

                if (col == maxCols) {
                    col = 0;
                    x = 0;
                    row++;
                    y += tileSize;
                }
            }
        }
    }

    public void Render(Graphics g) {
        for (int i = 0; i < tiles.length; i++) {
            tiles[i].Render(g);
        }
    }


}
