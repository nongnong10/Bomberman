package bomberman.btl;

import bomberman.btl.graphics.ImgCollection;
import bomberman.btl.graphics.Sprite;
import bomberman.btl.graphics.SpriteSheet;
import entities.Entity;
import entities.Grass;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BombermanMain extends Application {
    public static final int DEFAULT_SIZE = 16;
    public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    public SpriteSheet spriteSheet = new SpriteSheet("/img/classic.png", 256);

    public ImgCollection imgCollection = new ImgCollection(spriteSheet);

    public Canvas canvas;
    public GraphicsContext graphicsContext;

    public static void main(String[] args) {
        launch(args);
    }

    public void createMap() {
        Sprite grass = new Sprite(DEFAULT_SIZE, 1, 7, spriteSheet, DEFAULT_SIZE, DEFAULT_SIZE);
        graphicsContext.drawImage(grass.getFxImage(), 32, 32);
    }


    public void start(Stage stage) {
        canvas = new Canvas();
        canvas.setWidth(WIDTH * SCALED_SIZE);
        canvas.setHeight(HEIGHT * SCALED_SIZE);
        graphicsContext = canvas.getGraphicsContext2D();

        Group root = new Group();
        ObservableList list = root.getChildren();
        list.add(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        createMap();
        graphicsContext.drawImage(imgCollection.grassImg, 0, 0);
    }
}
