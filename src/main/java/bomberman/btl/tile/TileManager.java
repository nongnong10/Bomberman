package bomberman.btl.tile;

import bomberman.btl.graphics.UtilityTool;
import bomberman.btl.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class TileManager {
    public GamePanel gamePanel;
    public Tile[] tile;

    public char[][] mapTile;
    public int[][] mapTileNum;

    public BufferedImage dead1, dead2, dead3;

    public boolean drawPath = false;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[50];
        mapTile = new char[gamePanel.maxScreenRow][gamePanel.maxScreenCol];
        mapTileNum = new int[gamePanel.maxScreenRow][gamePanel.maxScreenCol];
        getTileImage();
        loadMap(1);
    }

    public void getTileImage() {
        setupTile(0, "grass", false, false);
        setupTile(1, "wall", true, false);
    }

    public void setupTile(int index, String imgPath, boolean collision, boolean breakable) {
        UtilityTool utilityTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/img/tile/" + imgPath + ".png"));
            tile[index].image = utilityTool.scaleImage(tile[index].image, gamePanel.tileSize, gamePanel.tileSize);
            tile[index].collision = collision;
            tile[index].breakable = breakable;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(int level) {
        try {
            String l = "C:\\Users\\HH\\Desktop\\Study\\Code\\btl\\src\\main\\resources\\map\\map" + level + ".txt";
            File myObj = new File(l);
            Scanner myReader = new Scanner(myObj);
            int row = 0;
            while (row < gamePanel.maxScreenRow) {
                String data = myReader.nextLine();
                for (int col = 0; col < gamePanel.maxScreenCol; ++col) {
                    mapTile[row][col] = data.charAt(col);
                    if (mapTile[row][col] == '#') {
                        mapTileNum[row][col] = 1;
                    } else {
                        mapTileNum[row][col] = 0;
                    }
                }
                row++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D) {
        for (int row = 0; row < gamePanel.maxScreenRow; ++row) {
            for (int col = 0; col < gamePanel.maxScreenCol; ++col) {
                int x = col * gamePanel.tileSize;
                int y = row * gamePanel.tileSize;
                graphics2D.drawImage(tile[mapTileNum[row][col]].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            }
        }

        if (drawPath == true) {
            graphics2D.setColor(new Color(255, 0, 0, 70));
            for (int i = 0; i < gamePanel.pathFinder.pathList.size(); ++i) {
                int worldX = gamePanel.pathFinder.pathList.get(i).col * gamePanel.tileSize;
                int worldY = gamePanel.pathFinder.pathList.get(i).row * gamePanel.tileSize;
//                int screenX = worldX - gamePanel.player.worldX;
//                int screenY = worldY - gamePanel.player.worldY;
                graphics2D.fillRect(worldX, worldY, gamePanel.tileSize, gamePanel.tileSize);
            }
        }

    }
}
