package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];


    
    int spNum = 2;
    int spCounter = 0;

    public TileManager(GamePanel gp){
        this.gp = gp;
        
        tile = new Tile[100];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/worldmap01.txt");
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass_tile.png"));
            
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/brick_tile.png"));
            tile[1].collision = true;

            // WATER
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water_tile1.png"));
            tile[2].collision = true;

            tile[80] = new Tile();
            tile[80].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water_tile2.png"));
            tile[80].collision = true;

            tile[81] = new Tile();
            tile[81].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water_tile3.png"));
            tile[81].collision = true;

            tile[82] = new Tile();
            tile[82].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water_tile4.png"));
            tile[82].collision = true;

            tile[83] = new Tile();
            tile[83].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water_tile5.png"));
            tile[83].collision = true;

            tile[84] = new Tile();
            tile[84].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water_tile6.png"));
            tile[84].collision = true;

            tile[85] = new Tile();
            tile[85].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water_tile7.png"));
            tile[85].collision = true;
            //

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/dirt_tile.png"));
            
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tree_tile.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/sand_tile.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // just a format to read string from file

            int col = 0;
            int row = 0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while (col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){}
    }

    public void draw(Graphics2D g2){
        
        int tileNum = 0;

        // map tiles
        int worldCol = 0;
        int worldRow = 0;
        // The position on the map
        int worldX;
        int worldY;
        // The position on the screen
        int screenX;
        int screenY;

        spCounter++;
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            tileNum = mapTileNum[worldCol][worldRow];
            
            // finding where the tile should be drawn

            // location of the tile on the map
            worldX = worldCol * gp.tileSize;
            worldY = worldRow * gp.tileSize;
            // location of the tile on the screen
            screenX = worldX - gp.player.worldX + gp.player.screenX;
            screenY = worldY - gp.player.worldY + gp.player.screenY;

            // water animation
            if(tileNum == 2){
                if(spCounter > 40){
                    if(spNum == 2){
                        spNum = 80;
                    }
                    else if(spNum == 80){
                        spNum = 81;
                    }
                    else if(spNum == 81){
                        spNum = 82;
                    }
                    else if(spNum == 82){
                        spNum = 83;
                    }
                    else if(spNum == 83){
                        spNum = 84;
                    }
                    else if(spNum == 84){
                        spNum = 85;
                    }
                    else if(spNum == 85){
                        spNum = 2;
                    }
                    spCounter = 0;
                }
                if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                        g2.drawImage(tile[spNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
                
            }
            else{
                if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            }
            worldCol++;
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }

        
    }




}
