package bomberman.btl.entities;

import javafx.scene.image.Image;

public class Bomber extends Entity {
    public Bomber(int x, int y) {
        super(x, y);
        this.image = imgCollection.bomberImg;
    }

    public Bomber(int x, int y, Image image) {
        super(x, y, image);
    }
}
