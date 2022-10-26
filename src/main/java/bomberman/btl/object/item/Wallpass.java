package bomberman.btl.object.item;

import bomberman.btl.main.GamePanel;
import bomberman.btl.object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Wallpass extends SuperObject {
    GamePanel gamePanel;
    public Wallpass(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.name = "wallpass";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/img/item/powerup_wallpass.png"));
            image = utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //co va cham
        collision = false;
    }
}
