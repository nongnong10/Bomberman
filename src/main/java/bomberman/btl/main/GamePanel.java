package bomberman.btl.main;

import bomberman.btl.input.KeyInput;
import bomberman.btl.entity.Player;
import bomberman.btl.object.SuperObject;
import bomberman.btl.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //screen setting
    public final int originalTileSize = 16; //16*16 tile
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48*48 tile
    public final int maxScreenCol = 21;
    public final int maxScreenRow = 11;

    public final int statusCol = maxScreenCol;
    public final int statusRow = 1;
    public final int screenWidth = maxScreenCol * tileSize; //21 * 48 = 1008
    public final int screenHeight = maxScreenRow * tileSize + statusRow * tileSize; //11 * 48 = 528
    public KeyInput keyInput = new KeyInput();
    //set player position
    public Player player = new Player(this, keyInput);

    //quan ly map
    public TileManager tileManager = new TileManager(this);

    //collision checker
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    //object
    public SuperObject[] objects = new SuperObject[50];

    //set object
    public AssetSetter assetSetter = new AssetSetter(this);

    //UI
    public UI ui = new UI(this);

    public int FPS = 60;

    //Once start it, it keep program running until you stop it
    Thread gameThread;

    public GamePanel() {
        //set the size of class (JPanel)
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyInput);
        //focus on key input
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
    }

    public void startGameThread() {
        //pass class GamePanel to gameThread
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //Thoi gian giua moi lan Draw
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        //Tile
        tileManager.draw(graphics2D);

        //Objects
        for (int i = 0; i < objects.length; ++i) {
            if (objects[i] != null) {
                objects[i].draw(graphics2D, this);
            }
        }

        //Player
        player.draw(graphics2D);

        //UI
        ui.draw(graphics2D);

        graphics2D.dispose();
    }
}
