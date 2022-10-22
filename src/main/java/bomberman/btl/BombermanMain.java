package bomberman.btl;

import bomberman.btl.entities.characters.player.Bomber;
import bomberman.btl.entities.objects.BreakableWall;
import bomberman.btl.entities.objects.Grass;
import bomberman.btl.entities.objects.UnbreakableWall;
import bomberman.btl.graphics.ImgCollection;
import bomberman.btl.graphics.Sprite;
import bomberman.btl.graphics.SpriteSheet;
import bomberman.btl.entities.*;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanMain extends Application {
    public static final int DEFAULT_SIZE = 16;
    public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
    public static final int WIDTH = 21;
    public static final int HEIGHT = 11;

    public static int x = 2;
    public static int y = 2;

    public SpriteSheet spriteSheet = new SpriteSheet("/img/classic.png", 256);

    public ImgCollection imgCollection = new ImgCollection(spriteSheet);

    public Group root = new Group();

    public ObservableList list = root.getChildren();

    public List<Entity> stillObject = new ArrayList<>();

    public List<Entity> entities = new ArrayList<>();

    public Bomber player = new Bomber(2, 2);

    public Canvas canvas;
    public GraphicsContext graphicsContext;

    public Scene scene;

    public Board board;

    public static void main(String[] args) {
        launch(args);
    }

    public void createMap() {
        try {
            File myObj = new File("C:\\Users\\HH\\Desktop\\Study\\Code\\btl\\src\\main\\resources\\map\\map3.txt");
            Scanner myReader = new Scanner(myObj);
            int row = 0;
            while (myReader.hasNextLine()) {
                row++;
                String data = myReader.nextLine();
                int stringLength = data.length();
                for (int col = 0; col < stringLength; ++col) {
                    Entity object;
                    //System.out.println((row-1) + " " + col + " : " + data.charAt(col));
                    board.map[row-1][col] = data.charAt(col);
                    if (data.charAt(col) == '#') {
                        object = new UnbreakableWall(row, col + 1);
                        stillObject.add(object);
                    } else if (data.charAt(col) == '.') {
                        object = new Grass(row, col + 1);
                        stillObject.add(object);
                    } else if (data.charAt(col) == '*') {
                        object = new BreakableWall(row, col + 1);
                        stillObject.add(object);
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public boolean checkMove(int row, int col, Board board) {
        if (row <= 1 || row >= HEIGHT || col <= 1 || col >= WIDTH) return false;
        char tmp = board.map[row-1][col-1];
        if (tmp == '#' || tmp == '*') return false;
        return true;
    }

    public void update() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:
                        System.out.println("UP");
                        up();
                        break;
                    case DOWN:
                        System.out.println("DOWN");
                        down();
                        break;
                    case LEFT:
                        System.out.println("LEFT");
                        left();
                        break;
                    case RIGHT:
                        System.out.println("RIGHT");
                        right();
                        break;
                }
            }

            public void up() {
                if (checkMove(x-1, y, board) == false)  return;
                player.update(1, x, y, board);
                x--;
            }

            public void down() {
                if (checkMove(x+1, y, board) == false)  return;
                player.update(2, x, y, board);
                if (x < HEIGHT) x++;
            }

            public void left() {
                if (checkMove(x, y-1, board) == false)  return;
                player.update(3, x, y, board);
                if (y > 1)  y--;
            }

            public void right() {
                if (checkMove(x, y+1, board) == false)  return;
                player.update(4, x, y, board);
                if (y < WIDTH) y++;
            }
        });
    }

    public void start(Stage stage) {
        canvas = new Canvas();
        canvas.setWidth(WIDTH * SCALED_SIZE);
        canvas.setHeight(HEIGHT * SCALED_SIZE);
        graphicsContext = canvas.getGraphicsContext2D();
        board = new Board(WIDTH, HEIGHT, graphicsContext);

        list.add(canvas);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new RenderImg();
        timer.start();
        createMap();
        entities.add(player);

        update();
    }

    public class RenderImg extends AnimationTimer {
        public void handle(long now) {
            render();
        }

        public void render() {
            graphicsContext.clearRect(0, 0, WIDTH, HEIGHT);
            for (Entity i : stillObject) {
                i.render(graphicsContext);
            }
            for (Entity i : entities) {
                i.render(graphicsContext);
            }
        }
    }
}
