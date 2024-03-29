package bomberman.btl.main;

import bomberman.btl.ai.PathFinder;
import bomberman.btl.entity.Entity;
import bomberman.btl.entity.enemy.Ballom;
import bomberman.btl.entity.weapon.Bomb;
import bomberman.btl.entity.weapon.Fireball;
import bomberman.btl.entity.weapon.Flame;
import bomberman.btl.entity.weapon.Projectile;
import bomberman.btl.input.KeyInput;
import bomberman.btl.entity.Player;
import bomberman.btl.sound.Sound;
import bomberman.btl.tile.InteractiveTile;
import bomberman.btl.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    public static int hasBomb = -1;
    //screen setting
    public final int originalTileSize = 16; //16*16 tile
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48*48 tile
    //STATUS
    public final int maxScreenCol = 21;
    public final int maxScreenRow = 11;
    public final int statusCol = maxScreenCol;
    public final int statusWidth = statusCol * tileSize;
    public final int statusRow = 1;
    public final int screenWidth = maxScreenCol * tileSize; //21 * 48 = 1008
    //SCREEN
    public final int statusHeight = statusRow * tileSize;
    public final int screenHeight = maxScreenRow * tileSize + statusRow * tileSize; //11 * 48 = 52;

    //GAME STATE
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int finishState = 3;
    public final int gameoverState = 4;
    public int gameState;
    public int maxLevel = 5;

    //LEVEL
    public int level = 1;

    public KeyInput keyInput = new KeyInput(this);
    //set player position
    public Player player = new Player(this, keyInput);
    //quan ly map
    public TileManager tileManager = new TileManager(this);
    //collision checker
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    //Entities
    public int maxObject = 50;
    public int maxEnemy = 20;
    public int maxInteractiveTile = 50;
    public int maxBomb = 20;
    public Entity[] objects = new Entity[maxObject];
    public Entity[] enemies = new Entity[maxEnemy];
    public InteractiveTile[] interactiveTiles = new InteractiveTile[maxInteractiveTile];
    public Bomb[] bombs = new Bomb[maxBomb];
    public int[][] flameTile = new int[maxScreenRow][maxScreenCol];
    public ArrayList<Entity> entities = new ArrayList<>();
    public ArrayList<Entity> objectList = new ArrayList<>();
    public ArrayList<Projectile> projectiles = new ArrayList<>();
    public ArrayList<Flame> flame = new ArrayList<>();
    public ArrayList<Fireball> fireballs = new ArrayList<>();

    //SET OBJECTS AND ENTITES
    public AssetSetter assetSetter = new AssetSetter(this);

    public PathFinder pathFinder = new PathFinder(this);

    //UI
    public UI ui = new UI(this);
    public int FPS = 60;

    //Sound
    public Sound sound = new Sound();

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyInput);
        this.setFocusable(true);
    }

    public void retry() {
        player = new Player(this, keyInput);
        player.setDefaultPlayer();
        assetSetter.setAll();
    }

    public void newLevel(int lv) {
        player.setDefaultPlayer();
        tileManager.loadMap(lv);
        assetSetter.setAll();
    }

    public void setupGame() {
        assetSetter.setAll();
        playMusic(1);
        gameState = titleState;
    }

    public void startGameThread() {
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
            if (player != null) {
                if (player.alive == true && player.dying == false) {
                    player.update();
                }
                if (player.alive == false) {
                    player = null;
                }
            }

            for (int i = 0; i < enemies.length; ++i) {
                if (enemies[i] != null) {
                    if (enemies[i].alive == true && enemies[i].dying == false) {
                        enemies[i].update();
                    }
                    if (enemies[i].alive == false) {
                        if (enemies[i].name == "kondoria") {
                            enemies[i] = new Ballom(this, enemies[i].worldX / tileSize, enemies[i].worldY / tileSize);
                        } else {
                            enemies[i] = null;
                        }
                    }
                }
            }
            for (int i = 0; i < interactiveTiles.length; ++i) {
                if (interactiveTiles[i] != null) {
                    if (interactiveTiles[i].alive == true && interactiveTiles[i].dying == false) {
                        interactiveTiles[i].update();
                    }
                    if (interactiveTiles[i].alive == false) {
                        interactiveTiles[i] = null;
                    }
                }
            }
            for (int i = 0; i < fireballs.size(); ++i) {
                if (fireballs.get(i) != null) {
                    if (fireballs.get(i).alive == true) {
                        fireballs.get(i).update();
                    }
                    if (fireballs.get(i).alive == false) {
                        fireballs.remove(i);
                    }
                }
            }

        }
        if (gameState == pauseState) {
            //pause = not update
        }
        if (gameState == gameoverState) {

        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        if (gameState != titleState) {
            //Tile
            tileManager.draw(graphics2D);

            //Add entity to List
            if (player != null) {
                entities.add(player);
            }

            for (int i = 0; i < objects.length; ++i) {
                if (objects[i] != null) {
                    objectList.add(objects[i]);
                }
            }

            //Add enemies
            for (int i = 0; i < enemies.length; ++i) {
                if (enemies[i] != null) {
                    entities.add(enemies[i]);
                }
            }

            //Add bomb
            for (int i = 0; i < projectiles.size(); ++i) {
                if (projectiles.get(i) != null) {
                    if (projectiles.get(i).alive == true) {
                        projectiles.get(i).update();
                    }
                    if (projectiles.get(i).alive == false) {
                        if (projectiles.get(i).name == "bomb") {
                            bombs[player.bombQueue.poll()] = null;
                        }
                        if (projectiles.get(i).name == "flame") {
                            player.numBomb++;
                        }
                        projectiles.remove(i);
                    }
                }
            }

            for (int i = 0; i < objectList.size(); ++i) {
                if (objectList.get(i) != null) {
                    objectList.get(i).draw(graphics2D);
                }
            }
            //Interactive tiles
            for (int i = 0; i < interactiveTiles.length; ++i) {
                if (interactiveTiles[i] != null) {
                    interactiveTiles[i].draw(graphics2D);
                }
            }
            //Draw entities
            for (int i = 0; i < entities.size(); ++i) {
                entities.get(i).draw(graphics2D);
            }
            for (int i = 0; i < projectiles.size(); ++i) {
                //System.out.println(i + " : " + projectiles.get(i).name);
                if (projectiles.get(i) != null) {
                    projectiles.get(i).draw(graphics2D);
                }
            }
            for (int i = 0; i < flame.size(); ++i) {
                //System.out.println(i + " : " + projectiles.get(i).name);
                if (flame.get(i) != null) {
                    flame.get(i).draw(graphics2D);
                }
            }
            for (int i = 0; i < fireballs.size(); ++i) {
                //System.out.println(i + " : " + projectiles.get(i).name);
                if (fireballs.get(i) != null) {
                    fireballs.get(i).draw(graphics2D);
                }
            }
            //Remove
            entities.clear();
            objectList.clear();
        }

        //UI
        ui.draw(graphics2D);
        graphics2D.dispose();
    }

    public void playMusic(int ind) {
        sound.setFile(ind);
        sound.play();
        sound.loop();
    }

    public void playSoundEffect(int ind) {
        sound.setFile(ind);
        sound.play();
    }
}