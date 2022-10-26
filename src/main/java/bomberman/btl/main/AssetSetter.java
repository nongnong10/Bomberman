package bomberman.btl.main;

import bomberman.btl.entity.enemy.Ballom;
import bomberman.btl.object.Door;
import bomberman.btl.object.item.Wallpass;

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
        gamePanel.enemies[0].worldX = 4* gamePanel.tileSize;
        gamePanel.enemies[0].worldY = 1* gamePanel.tileSize;
    }
}
