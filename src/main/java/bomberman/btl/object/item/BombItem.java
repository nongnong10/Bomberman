package bomberman.btl.object.item;

import bomberman.btl.main.GamePanel;
import bomberman.btl.object.Item;


public class BombItem extends Item {
    public BombItem(GamePanel gamePanel){
        super(gamePanel);
        super.name = "bombItem";
        down1 = setupImage("/item/powerup_bombs");
    }
    public BombItem(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        super.name = "bombItem";
        down1 = setupImage("/item/powerup_bombs");
    }
}
