package bomberman.btl.entities;

import bomberman.btl.BombermanMain;
import bomberman.btl.graphics.ImgCollection;
import bomberman.btl.graphics.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
    public int x;
    public int y;
    public int row;
    public int col;
    public Image image;

    public SpriteSheet spriteSheet = new SpriteSheet("/img/classic.png", 256);

    public ImgCollection imgCollection = new ImgCollection(spriteSheet);

    public Entity(int row, int col) {
        this.row = row;
        this.col = col;
        this.x = col * BombermanMain.SCALED_SIZE - BombermanMain.SCALED_SIZE;
        this.y = row * BombermanMain.SCALED_SIZE - BombermanMain.SCALED_SIZE;
    }

    public Entity(int row, int col, Image image) {
        this.row = row;
        this.col = col;
        this.x = col * BombermanMain.SCALED_SIZE - BombermanMain.SCALED_SIZE;
        this.y = row * BombermanMain.SCALED_SIZE - BombermanMain.SCALED_SIZE;
        this.image = image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, x, y);
    }
}
