package bomberman.btl.object.item;

import bomberman.btl.main.GamePanel;
import bomberman.btl.object.Item;

public class FlameItem extends Item {
    public FlameItem(GamePanel gamePanel){
        super(gamePanel);
        super.name = "flameItem";
        down1 = setupImage("/item/powerup_flames");
    }
    public FlameItem(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        super.name = "flameItem";
        down1 = setupImage("/item/powerup_flames");
    }
}
