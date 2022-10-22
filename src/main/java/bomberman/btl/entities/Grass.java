package entities;

import javafx.scene.image.Image;

public class Grass extends Entity{
    public Grass(int x, int y){
        super(x, y);
        this.image = imgCollection.grass.getFxImage();
    }

    public Grass(int x, int y, Image image){
        super(x, y, image);
    }
}
