package bomberman.btl.object.item;

import bomberman.btl.entity.Entity;
import bomberman.btl.main.GamePanel;
import bomberman.btl.object.Item;

import java.awt.*;

public class Wallpass extends Item {
    public Wallpass(GamePanel gamePanel){
        super(gamePanel);
        super.name = "wallpass";
        down1 = setupImage("/item/powerup_wallpass");
    }
    public Wallpass(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        super.name = "wallpass";
        down1 = setupImage("/item/powerup_wallpass");
    }
}
