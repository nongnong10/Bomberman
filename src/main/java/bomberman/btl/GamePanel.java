package bomberman.btl;

import bomberman.btl.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //screen setting
    public final int originalTileSize = 16; //16*16 tile
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48*48 tile

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize; //16 * 48
    public final int screenHeight = maxScreenRow * tileSize; //12 * 48
    public KeyInput keyInput = new KeyInput();
    //set player position
    Player player = new Player(this, keyInput);

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

    public void startGameThread() {
        //pass class GamePanel to gameThread
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //Thoi gian giua moi lan Draw
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            }
            catch (InterruptedException e){
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
        player.draw(graphics2D);
        graphics2D.dispose();
    }
}
