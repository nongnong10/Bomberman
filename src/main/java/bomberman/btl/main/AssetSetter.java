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

    public void setObject() {
        gamePanel.objects[0] = new Door(this.gamePanel);
        gamePanel.objects[0].worldX = 1 * gamePanel.tileSize;
        gamePanel.objects[0].worldY = 3 * gamePanel.tileSize;

        gamePanel.objects[1] = new Wallpass(this.gamePanel);
        gamePanel.objects[1].worldX = 1 * gamePanel.tileSize;
        gamePanel.objects[1].worldY = 5 * gamePanel.tileSize;
    }

    public void setEnemy() {
        gamePanel.enemies[0] = new Ballom(this.gamePanel);
        gamePanel.enemies[0].worldX = 9 * gamePanel.tileSize;
        gamePanel.enemies[0].worldY = 1 * gamePanel.tileSize;

//        gamePanel.enemies[1] = new Ballom(this.gamePanel);
//        gamePanel.enemies[1].worldX = 5 * gamePanel.tileSize;
//        gamePanel.enemies[1].worldY = 1 * gamePanel.tileSize;
//
//        gamePanel.enemies[2] = new Ballom(this.gamePanel);
//        gamePanel.enemies[2].worldX = 6 * gamePanel.tileSize;
//        gamePanel.enemies[2].worldY = 1 * gamePanel.tileSize;
//
//        gamePanel.enemies[3] = new Ballom(this.gamePanel);
//        gamePanel.enemies[3].worldX = 7 * gamePanel.tileSize;
//        gamePanel.enemies[3].worldY = 1 * gamePanel.tileSize;
    }

    public void setInteractiveTiles() {
        gamePanel.interactiveTiles[0] = new Brick(this.gamePanel,5,1);
        gamePanel.interactiveTiles[1] = new Brick(this.gamePanel,6,1);
    }
}
