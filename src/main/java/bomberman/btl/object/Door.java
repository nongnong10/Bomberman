package bomberman.btl.object;

import bomberman.btl.entity.Entity;
import bomberman.btl.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Door extends Item {
    public Door(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        this.name = "door";
        down1 = setupImage("/object/portal");
        solidArea = new Rectangle(3, 6, 39, 36);
    }
}
