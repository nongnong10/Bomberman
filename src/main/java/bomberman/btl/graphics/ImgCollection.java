package bomberman.btl.graphics;

import bomberman.btl.BombermanMain;
import javafx.scene.image.Image;

public class ImgCollection {
    public static final int DEFAULT_SIZE = BombermanMain.DEFAULT_SIZE;

    private static SpriteSheet spriteSheet = new SpriteSheet("/img/classic.png", 256);

    /**
     * Grass.
     */
    public static Sprite grass = new Sprite(DEFAULT_SIZE, 1, 7, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);
    public Image grassImg = grass.getFxImage();

    public ImgCollection(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
    }
}
