package bomberman.btl.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Door extends SuperObject {
    public Door() {
        this.name = "door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/img/object/portal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
