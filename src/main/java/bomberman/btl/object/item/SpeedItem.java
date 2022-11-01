package bomberman.btl.object.item;

import bomberman.btl.main.GamePanel;
import bomberman.btl.object.Item;

public class SpeedItem extends Item {
    public SpeedItem(GamePanel gamePanel){
        super(gamePanel);
        super.name = "speedItem";
        down1 = setupImage("/item/powerup_flames");
    }
    public SpeedItem(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        super.name = "speedItem";
        down1 = setupImage("/item/powerup_speed");
    }
}
