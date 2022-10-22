package bomberman.btl.entities.characters;

import bomberman.btl.entities.Entity;
import javafx.scene.image.Image;


public class Character extends Entity {
    public Character(int x, int y, Image image) {
        super(x, y, image);
    }

    public Character(int x, int y) {
        super(x, y);
    }
}
