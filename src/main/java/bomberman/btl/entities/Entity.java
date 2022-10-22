package entities;

import bomberman.btl.BombermanMain;
import bomberman.btl.graphics.ImgCollection;
import bomberman.btl.graphics.Sprite;
import bomberman.btl.graphics.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
    public int x;
    public int y;
    public Image image;
    public SpriteSheet tmp = new SpriteSheet("/img/classic.png", 256);

    public ImgCollection imgCollection = new ImgCollection(tmp);

    public Entity(int row, int col) {
        this.x = col * BombermanMain.SCALED_SIZE;
        this.y = row * BombermanMain.SCALED_SIZE;
    }

    public Entity(int row, int col, Image image) {
        this.x = col * BombermanMain.SCALED_SIZE;
        this.y = row * BombermanMain.SCALED_SIZE;
        this.image = image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, x, y);
    }
}
