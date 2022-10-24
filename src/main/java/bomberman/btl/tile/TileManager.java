package bomberman.btl.tile;

import bomberman.btl.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;

    char[][] mapTile;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTile = new char[gamePanel.maxScreenRow][gamePanel.maxScreenCol];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/img/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/img/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/img/brick.png"));
        } catch (IOException e) {
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
                if (mapTile[row][col] == '#'){
                    graphics2D.drawImage(tile[1].image,x,y,gamePanel.tileSize, gamePanel.tileSize, null);
                }
                if (mapTile[row][col] == '.'){
                    graphics2D.drawImage(tile[0].image,x,y,gamePanel.tileSize, gamePanel.tileSize, null);
                }
                if (mapTile[row][col] == '*'){
                    graphics2D.drawImage(tile[2].image,x,y,gamePanel.tileSize, gamePanel.tileSize, null);
                }
            }
        }

    }
}
