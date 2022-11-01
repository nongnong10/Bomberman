package bomberman.btl.object;

import bomberman.btl.entity.Entity;
import bomberman.btl.main.GamePanel;

import java.awt.*;

public class Item extends Entity {
    public Item(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        collision = false;
        solidArea = new Rectangle(3, 6, 39, 36);
    }
    public Item(GamePanel gamePanel, int col, int row) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.worldX = col * gamePanel.tileSize;
        this.worldY = row * gamePanel.tileSize;
        collision = false;
        solidArea = new Rectangle(3, 6, 39, 36);
    }
}
