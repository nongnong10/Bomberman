package bomberman.btl.main;

import bomberman.btl.entity.Entity;
import bomberman.btl.input.KeyInput;
import bomberman.btl.entity.Player;
import bomberman.btl.object.SuperObject;
import bomberman.btl.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    //screen setting
    public final int originalTileSize = 16; //16*16 tile
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48*48 tile
    public final int maxScreenCol = 21;
    public final int maxScreenRow = 11;
    public final int statusCol = maxScreenCol;
    public final int statusWidth = statusCol * tileSize;
    public final int statusRow = 1;
    public final int statusHeight = statusRow * tileSize;
    public final int screenWidth = maxScreenCol * tileSize; //21 * 48 = 1008
    public final int screenHeight = maxScreenRow * tileSize + statusRow * tileSize; //11 * 48 = 528
    public final int playState = 1;
    public final int pauseState = 2;
    public KeyInput keyInput = new KeyInput(this);
    //set player position
    public Player player = new Player(this, keyInput);
    //quan ly map
    public TileManager tileManager = new TileManager(this);
    //collision checker
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    //object
    public Entity[] objects = new Entity[20];
    public Entity[] enemies = new Entity[20];
    //set object
    public AssetSetter assetSetter = new AssetSetter(this);
    //UI
    public UI ui = new UI(this);
    //Game state
    public int gameState;
    public int FPS = 60;
    ArrayList<Entity> entities = new ArrayList<>();
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
        assetSetter.setEnemy();
        gameState = playState;
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
        if (gameState == playState) {
            player.update();
            for (int i = 0; i < enemies.length; ++i) {
                if (enemies[i] != null) {
                    enemies[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            //pause = not update
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        //Tile
        tileManager.draw(graphics2D);
//        for (Entity i: entities){
//            System.out.println(i.name);
//        }
//        System.out.println(entities.size());

        //Add entity to List
        entities.add(player);

        for (int i = 0; i < objects.length; ++i) {
            if (objects[i] != null) {
                entities.add(objects[i]);
            }
        }

        //Add enemies
        for (int i = 0; i < enemies.length; ++i) {
            if (enemies[i] != null) {
                entities.add(enemies[i]);
            }
        }

        //Sort
        Collections.sort(entities, new Comparator<Entity>() {
            @Override
            public int compare(Entity o1, Entity o2) {
                int res = 0;
                if (o1.name == o2.name) {
                    res = Integer.compare(o1.worldY, o2.worldY);
                } else {
                    if (o1.name == "enemy"){
                        res = 1;
                    } else{
                        res = -1;
                    }
                }
                return res;
            }
        });
        //Draw entities
        for (int i = 0; i < entities.size(); ++i) {
            //System.out.println(i + " : " + entities.get(i).name);
            entities.get(i).draw(graphics2D);
        }
        //Remove
        entities.clear();

        //UI
        ui.draw(graphics2D);

        graphics2D.dispose();
    }
}
