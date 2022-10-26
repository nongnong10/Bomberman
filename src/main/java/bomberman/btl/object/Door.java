package bomberman.btl.object;

import bomberman.btl.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Door extends SuperObject {
    GamePanel gamePanel;
    public Door(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.name = "door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/img/object/portal.png"));
            image = utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
