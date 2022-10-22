package bomberman.btl.entities.objects;

import javafx.scene.image.Image;

public class UnbreakableWall extends Wall {
    public UnbreakableWall(int x, int y) {
        super(x, y);
        this.image = imgCollection.unbreakalbeWallImg;
    }

    public UnbreakableWall(int x, int y, Image image) {
        super(x, y, image);
    }
}
