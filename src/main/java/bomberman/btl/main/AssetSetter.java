package bomberman.btl.main;

import bomberman.btl.object.Door;
import bomberman.btl.object.item.Wallpass;

public class AssetSetter {
    public GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.objects[0] = new Door();
        gamePanel.objects[0].worldX = 19 * gamePanel.tileSize;
        gamePanel.objects[0].worldY = 9 * gamePanel.tileSize;

        gamePanel.objects[1] = new Wallpass();
        gamePanel.objects[1].worldX = 1 * gamePanel.tileSize;
        gamePanel.objects[1].worldY = 5 * gamePanel.tileSize;
    }
}