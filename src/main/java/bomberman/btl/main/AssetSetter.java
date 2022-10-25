package bomberman.btl.main;

import bomberman.btl.object.Door;

public class AssetSetter {
    public GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.objects[0] = new Door();
        gamePanel.objects[0].worldX = 19 * gamePanel.tileSize;
        gamePanel.objects[0].worldY = 9 * gamePanel.tileSize;
    }
}
