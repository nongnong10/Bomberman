package bomberman.btl.entities.objects;

import javafx.scene.image.Image;

public class BreakableWall extends Wall{
    public BreakableWall(int x, int y){
        super(x, y);
        this.image = imgCollection.breakalbeWallImg;
    }

    public BreakableWall(int x, int y, Image image) {
        super(x, y, image);
    }
}
