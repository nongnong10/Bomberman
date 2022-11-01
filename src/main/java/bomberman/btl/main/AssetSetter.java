package bomberman.btl.main;

import bomberman.btl.entity.enemy.Ballom;
import bomberman.btl.object.Door;
import bomberman.btl.object.item.Wallpass;
import bomberman.btl.tile.Brick;

public class AssetSetter {
    public GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setAll(){
        int numObj = -1;
        int numEnemy = -1;
        int numBrick = -1;
        for (int i = 0; i < gamePanel.maxScreenRow; ++i) {
            for (int j = 0; j < gamePanel.maxScreenCol; ++j) {
                char tmp = gamePanel.tileManager.mapTile[i][j];
                if (tmp == 'D') {
                    gamePanel.objects[++numObj] = new Door(this.gamePanel, j, i);
                }
                if (tmp == 'W') {
                    gamePanel.objects[++numObj] = new Wallpass(this.gamePanel, j, i);
                }
                if (tmp == '1') {
                    gamePanel.enemies[++numEnemy] = new Ballom(this.gamePanel, j, i);
                }
                if (tmp == '*' || tmp == 'D' || tmp == 'W') {
                    gamePanel.interactiveTiles[++numBrick] = new Brick(this.gamePanel, j, i);
                }
            }
        }
    }

    public void setObject() {
        int numObj = -1;
        for (int i = 0; i < gamePanel.maxScreenRow; ++i) {
            for (int j = 0; j < gamePanel.maxScreenCol; ++j) {
                if (gamePanel.tileManager.mapTile[i][j] == 'D') {
                    gamePanel.objects[++numObj] = new Door(this.gamePanel, j, i);
                }
                if (gamePanel.tileManager.mapTile[i][j] == 'W') {
                    gamePanel.objects[++numObj] = new Wallpass(this.gamePanel, j, i);
                }
            }
        }
    }

    public void setEnemy() {
        int numEnemy = -1;
        for (int i = 0; i < gamePanel.maxScreenRow; ++i) {
            for (int j = 0; j < gamePanel.maxScreenCol; ++j) {
                if (gamePanel.tileManager.mapTile[i][j] == '1') {
                    gamePanel.enemies[++numEnemy] = new Ballom(this.gamePanel, j, i);
                }
            }
        }
    }

    public void setInteractiveTiles() {
        int numBrick = -1;
        for (int i = 0; i < gamePanel.maxScreenRow; ++i) {
            for (int j = 0; j < gamePanel.maxScreenCol; ++j) {
                char tmp = gamePanel.tileManager.mapTile[i][j];
                if (tmp == '*' || tmp == 'D' || tmp == 'W') {
                    gamePanel.interactiveTiles[++numBrick] = new Brick(this.gamePanel, j, i);
                }
            }
        }
    }
}
