package bomberman.btl.entities.objects;

import bomberman.btl.entities.Entity;
import javafx.scene.image.Image;

public class Grass extends Entity {
    public Grass(int x, int y){
        super(x, y);
        this.image = imgCollection.grassImg;
    }

    public Grass(int x, int y, Image image){
        super(x, y, image);
    }
}
