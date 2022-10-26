package bomberman.btl.tile;

import bomberman.btl.graphics.UtilityTool;
import bomberman.btl.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class TileManager {
    public GamePanel gamePanel;
    public Tile[] tile;

    public char[][] mapTile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[50];
        mapTile = new char[gamePanel.maxScreenRow][gamePanel.maxScreenCol];
        mapTileNum = new int[gamePanel.maxScreenRow][gamePanel.maxScreenCol];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        setupTile(0, "grass", false);
        setupTile(1, "wall", true);
        setupTile(2, "brick", true);
    }

    public void setupTile(int index, String imgPath, boolean collision) {
        UtilityTool utilityTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/img/tile/" + imgPath + ".png"));
            tile[index].image = utilityTool.scaleImage(tile[index].image, gamePanel.tileSize, gamePanel.tileSize);
            tile[index].collision = collision;
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            File myObj = new File("C:\\Users\\HH\\Desktop\\Study\\Code\\btl\\src\\main\\resources\\map\\map3.txt");
            Scanner myReader = new Scanner(myObj);
            int row = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                for (int col = 0; col < gamePanel.maxScreenCol; ++col) {
                    mapTile[row][col] = data.charAt(col);
                    switch (mapTile[row][col]) {
                        case '#':
                            mapTileNum[row][col] = 1;
                            break;
                        case '.':
                            mapTileNum[row][col] = 0;
                            break;
                        case '*':
                            mapTileNum[row][col] = 2;
                            break;
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

    }
}
