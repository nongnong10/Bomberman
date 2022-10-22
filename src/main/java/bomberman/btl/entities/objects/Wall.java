package bomberman.btl.entities.objects;

import bomberman.btl.entities.Entity;
import javafx.scene.image.Image;

public class Wall extends Entity {
    public Wall(int x, int y) {
        super(x, y);
    }

    public Wall(int x, int y, Image image) {
        super(x, y, image);
    }
}
