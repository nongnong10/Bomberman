package bomberman.btl.object.item;

import bomberman.btl.entity.Entity;
import bomberman.btl.main.GamePanel;
import bomberman.btl.object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Wallpass extends Entity {
    public Wallpass(GamePanel gamePanel) {
        super(gamePanel);
        super.name = "wallpass";
        down1 = setupImage("/item/powerup_wallpass");
        //co va cham
        collision = false;
        solidArea = new Rectangle(0,0,gamePanel.tileSize, gamePanel.tileSize);
    }
}
