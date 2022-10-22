package bomberman.btl;

import javafx.scene.canvas.GraphicsContext;

public class Board {
    public static int WIDTH;
    public static int HEIGHT;
    public static char[][] map;

    public GraphicsContext graphicsContext;

    public Board(int width, int height, GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
        WIDTH = width;
        HEIGHT = height;
        map = new char[HEIGHT][WIDTH];
    }
}
