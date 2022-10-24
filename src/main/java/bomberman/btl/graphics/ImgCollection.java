package bomberman.btl.graphics;

import bomberman.btl.main.BombermanMain;
import javafx.scene.image.Image;

public class ImgCollection {
    public static final int DEFAULT_SIZE = BombermanMain.DEFAULT_SIZE;

    private static SpriteSheet spriteSheet = new SpriteSheet("/img/classic.png", 256);

    /**
     * Grass.
     */
    public static Sprite grass = new Sprite(DEFAULT_SIZE, 1, 7, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);
    public Image grassImg = grass.getFxImage();

    /**
     * Unbreakable Wall.
     */
    public static Sprite unbreakalbeWall = new Sprite(DEFAULT_SIZE, 1, 6, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);
    public Image unbreakalbeWallImg = unbreakalbeWall.getFxImage();

    /**
     * Breakable Wall.
     */
    public static Sprite breakalbeWall = new Sprite(DEFAULT_SIZE, 1, 8, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);
    public Image breakalbeWallImg = breakalbeWall.getFxImage();

    /**
     * Player.
     */
    public static Sprite bomber = new Sprite(DEFAULT_SIZE, 1, 3, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);
    public Image bomberImg = bomber.getFxImage();

    public static Sprite player_up_1 = new Sprite(DEFAULT_SIZE, 1, 1, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_up_2 = new Sprite(DEFAULT_SIZE, 2, 1, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_up_3 = new Sprite(DEFAULT_SIZE, 3, 1, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);

    public static Sprite player_down_1 = new Sprite(DEFAULT_SIZE, 1, 3, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_down_2 = new Sprite(DEFAULT_SIZE, 2, 3, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_down_3 = new Sprite(DEFAULT_SIZE, 3, 3, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);

    public static Sprite player_left_1 = new Sprite(DEFAULT_SIZE, 1, 4, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_left_2 = new Sprite(DEFAULT_SIZE, 2, 4, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_left_3 = new Sprite(DEFAULT_SIZE, 3, 4, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);

    public static Sprite player_right_1 = new Sprite(DEFAULT_SIZE, 1, 2, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_right_2 = new Sprite(DEFAULT_SIZE, 2, 2, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);
    public static Sprite player_right_3 = new Sprite(DEFAULT_SIZE, 3, 2, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);

    public ImgCollection(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
    }
}
