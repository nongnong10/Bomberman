package bomberman.btl.object.item;

import bomberman.btl.object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Wallpass extends SuperObject {
    public Wallpass() {
        this.name = "wallpass";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/img/item/powerup_wallpass.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //co va cham
        collision = false;
    }
}
