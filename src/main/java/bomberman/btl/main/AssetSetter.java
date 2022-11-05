package bomberman.btl.main;

import bomberman.btl.entity.enemy.*;
import bomberman.btl.object.Door;
import bomberman.btl.object.item.BombItem;
import bomberman.btl.object.item.FlameItem;
import bomberman.btl.object.item.SpeedItem;
import bomberman.btl.object.item.Wallpass;
import bomberman.btl.tile.Brick;

public class AssetSetter {
    public GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setAll() {
        int numObj = -1;
        int numEnemy = -1;
        int numBrick = -1;
        //RESET
        for (int i = 0; i < gamePanel.maxObject; ++i) {
            gamePanel.objects[i] = null;
        }
        for (int i = 0; i < gamePanel.maxEnemy; ++i) {
            gamePanel.enemies[i] = null;
        }
        for (int i = 0; i < gamePanel.maxInteractiveTile; ++i) {
            gamePanel.interactiveTiles[i] = null;
        }
        for (int i = 0; i < gamePanel.maxBomb; ++i) {
            gamePanel.bombs[i] = null;
        }

        //CREATE NEW
        for (int i = 0; i < gamePanel.maxScreenRow; ++i) {
            for (int j = 0; j < gamePanel.maxScreenCol; ++j) {
                char tmp = gamePanel.tileManager.mapTile[i][j];

                //POWER UP
                if (tmp == 'w') {
                    gamePanel.objects[++numObj] = new Wallpass(this.gamePanel, j, i);
                }
                if (tmp == 'b') {
                    gamePanel.objects[++numObj] = new BombItem(this.gamePanel, j, i);
                }
                if (tmp == 'f') {
                    gamePanel.objects[++numObj] = new FlameItem(this.gamePanel, j, i);
                }
                if (tmp == 's') {
                    gamePanel.objects[++numObj] = new SpeedItem(this.gamePanel, j, i);
                }

                if (tmp == 'D') {
                    gamePanel.objects[++numObj] = new Door(this.gamePanel, j, i);
                }

                //ENEMIES
                if (tmp == '1') {
                    gamePanel.enemies[++numEnemy] = new Ballom(this.gamePanel, j, i);
                }
                if (tmp == '2') {
                    gamePanel.enemies[++numEnemy] = new Oneal(this.gamePanel, j, i);
                }
                if (tmp == '3') {
                    gamePanel.enemies[++numEnemy] = new Doll(this.gamePanel, j, i);
                }
                if (tmp == '4') {
                    gamePanel.enemies[++numEnemy] = new Minvo(this.gamePanel, j, i);
                }
                if (tmp == '5') {
                    gamePanel.enemies[++numEnemy] = new Ovapi(this.gamePanel, j, i);
                }
                if (tmp == '6') {
                    gamePanel.enemies[++numEnemy] = new Kondoria(this.gamePanel, j, i);
                }

                //BRICK
                if (tmp == '*' || tmp == 'D' || tmp == 'w' || tmp == 'b' || tmp == 'f' || tmp == 's') {
                    gamePanel.interactiveTiles[++numBrick] = new Brick(this.gamePanel, j, i);
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
