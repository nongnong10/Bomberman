package bomberman.btl.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Door extends SuperObject {
    public Door() {
        this.name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/img/object/portal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
