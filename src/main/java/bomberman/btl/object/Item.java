package bomberman.btl.object;

import bomberman.btl.entity.Entity;
import bomberman.btl.main.GamePanel;

public class Item extends Entity {
    public Item(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
    }
    public Item(GamePanel gamePanel, int col, int row) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.worldX = col * gamePanel.tileSize;
        this.worldY = row * gamePanel.tileSize;
    }
}
