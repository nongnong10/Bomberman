package bomberman.btl.entity;

import bomberman.btl.entity.weapon.Bomb;
import bomberman.btl.entity.weapon.Fireball;
import bomberman.btl.entity.weapon.Flame;
import bomberman.btl.entity.weapon.Projectile;
import bomberman.btl.main.GamePanel;
import bomberman.btl.input.KeyInput;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

public class Player extends Entity {
    //POWER UP
    public static long timer = 0;
    public boolean wallpass = false;

    public BufferedImage dead1, dead2, dead3;
    public int dyingcounter = 0;

    //BOMB INFO
    public int maxBomb = 10;
    public int numBomb = maxBomb;
    public int currentBomb = -1;
    public int scale = 1;

    //PLAYER INFO
    public int life = 3;
    public Queue<Integer> bombQueue = new LinkedList<>();

    public boolean soundCheck = false;
    KeyInput keyInput;

    public Player(GamePanel gamePanel, KeyInput keyInput) {
        super(gamePanel);
        this.keyInput = keyInput;

        //Set collision area.
        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 36; //12 pixel
        solidArea.height = 42; //14 pixel

        solidAreaTop = new Rectangle(9, 0, 18, 18);
        solidAreaBottom = new Rectangle(9, 24, 18, 18);
        solidAreaLeft = new Rectangle(0, 9, 18, 24);
        solidAreaRight = new Rectangle(9, 9, 18, 24);

        dying = false;
        alive = true;

        speed = 2;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        this.worldX = gamePanel.tileSize;
        this.worldY = gamePanel.tileSize;
        speed = 4;
        fireball = new Fireball(this.gamePanel);
    }

    public void getPlayerImage() {
        up1 = setupImage("/player/player_up");
        up2 = setupImage("/player/player_up_1");
        up3 = setupImage("/player/player_up_2");
        down1 = setupImage("/player/player_down");
        down2 = setupImage("/player/player_down_1");
        down3 = setupImage("/player/player_down_2");
        left1 = setupImage("/player/player_left");
        left2 = setupImage("/player/player_left_1");
        left3 = setupImage("/player/player_left_2");
        right1 = setupImage("/player/player_right");
        right2 = setupImage("/player/player_right_1");
        right3 = setupImage("/player/player_right_2");
        dead1 = setupImage("/player/player_dead1");
        dead2 = setupImage("/player/player_dead2");
        dead3 = setupImage("/player/player_dead3");
    }

    public void update() {
        if (keyInput.upPressed || keyInput.downPressed || keyInput.leftPressed || keyInput.rightPressed) {
            if (keyInput.upPressed) {
                direction = "up";
            } else if (keyInput.downPressed) {
                direction = "down";
            } else if (keyInput.leftPressed) {
                direction = "left";
            } else if (keyInput.rightPressed) {
                direction = "right";
            }

            //check tile collision
            collisionOn = false;
            gamePanel.collisionChecker.checkTilePlayer(this);
            if (wallpass) {
                collisionOn = false;
                timer++;
            }

            //check interactive tile collision
            int tileInd = gamePanel.collisionChecker.checkEntity(this, gamePanel.interactiveTiles);

            //check object collision
            int objInd = gamePanel.collisionChecker.checkObject(this, true);
            pickupObject(objInd);

            //gamePanel.eventHandler.checkEvent();

            //check enemy collision
            int enemyInd = gamePanel.collisionChecker.checkEntity(this, gamePanel.enemies);
            interactEnemy(enemyInd);

            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) spriteNum = 2;
                else if (spriteNum == 2) spriteNum = 3;
                else if (spriteNum == 3) spriteNum = 1;
                spriteCounter = 0;
            }

            if (wallpass) {
                long now = System.currentTimeMillis();
                if (now - timer >= 5000) {
                    wallpass = false;
                    timer = 0;
                }
            }
        }
        if (keyInput.bombPressed == true) {
            placeBomb();
            keyInput.bombPressed = false;
        }
        if (keyInput.shootPressed == true && fireball.alive == false) {
            fireball.set(worldX, worldY, direction, true, this);
            gamePanel.fireballs.add(fireball);
        }
    }

    public void setDefaultPlayer() {
        setDefaultPosition();
    }

    public void setDefaultPosition() {
        worldX = gamePanel.tileSize;
        worldY = gamePanel.tileSize;
        direction = "down";
    }

    public void restoreLife() {
        //life = maxLife;
    }

    public void pickupObject(int index) {
        if (index != 999) {
            String objectName = gamePanel.objects[index].name;
            switch (objectName) {
                case "wallpass":
                    gamePanel.playSoundEffect(7);
                    wallpass = true;
                    timer = System.currentTimeMillis();
                    break;
                case "speedItem":
                    gamePanel.playSoundEffect(7);
                    speed += 2;
                    break;
                case "flameItem":
                    gamePanel.playSoundEffect(7);
                    scale += 1;
                    break;
                case "bombItem":
                    gamePanel.playSoundEffect(7);
                    maxBomb++;
                    numBomb++;
                    break;

                case "door":
                    gamePanel.playSoundEffect(7);
                    gamePanel.level++;
                    System.out.println(gamePanel.level);
                    if (gamePanel.level <= gamePanel.maxLevel){
                        gamePanel.newLevel(gamePanel.level);
                    } else {
                        gamePanel.gameState = gamePanel.gameoverState;
                    }
                    break;
            }
            gamePanel.objects[index] = null;
        }
    }

    public void interactEnemy(int index) {
        if (index != 999) {
            gamePanel.player.dying = true;
        }
    }

    public void damageEnemy(int index) {
        if (index != 999) {
            gamePanel.enemies[index].dying = true;
        }
    }

    public void damageTile(int index) {
        if (index != 999) {
            gamePanel.interactiveTiles[index].dying = true;
        }
    }

    public void placeBomb() {
        if (numBomb > 0) {
            int nrow = worldY / gamePanel.tileSize;
            int ncol = worldX / gamePanel.tileSize;

            int xBomb, yBomb;
            if (worldX % gamePanel.tileSize >= (solidArea.x + solidArea.width) / 3 * 2) {
                xBomb = (ncol + 1) * gamePanel.tileSize;
            } else {
                xBomb = ncol * gamePanel.tileSize;
            }
            if (worldY % gamePanel.tileSize >= (solidArea.y + solidArea.height) / 3 * 2) {
                yBomb = (nrow + 1) * gamePanel.tileSize;
            } else {
                yBomb = nrow * gamePanel.tileSize;
            }
            currentBomb = (currentBomb + 1) % maxBomb;

            gamePanel.bombs[currentBomb] = new Bomb(gamePanel, xBomb, yBomb, this, scale);
            gamePanel.projectiles.add(gamePanel.bombs[currentBomb]);
            bombQueue.add(currentBomb);

            numBomb--;
        }
    }

    public void draw(Graphics2D graphics2D) {
        try {
            if (dying == true) {
                dyingAnimation(graphics2D);
            } else {
                BufferedImage image = null;
                switch (direction) {
                    case "up":
                        if (spriteNum == 1) {
                            image = up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                        }
                        if (spriteNum == 3) {
                            image = up3;
                        }
                        break;
                    case "down":
                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                        if (spriteNum == 3) {
                            image = down3;
                        }
                        break;
                    case "left":
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                        if (spriteNum == 3) {
                            image = left3;
                        }
                        break;
                    case "right":
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                        if (spriteNum == 3) {
                            image = right3;
                        }
                        break;
                }
                graphics2D.drawImage(image, worldX, worldY, null);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    public void dyingAnimation(Graphics2D graphics2D) {
        dyingcounter++;
        int i = 20;
        if (dyingcounter <= i) {
            if (soundCheck == false){
                gamePanel.playSoundEffect(3);
                soundCheck = true;
            }
            graphics2D.drawImage(dead1, worldX, worldY, null);
        }
        if (dyingcounter > i && dyingcounter <= 2 * i) {
            graphics2D.drawImage(dead2, worldX, worldY, null);
        }
        if (dyingcounter > 2 * i && dyingcounter <= 3 * i) {
            graphics2D.drawImage(dead3, worldX, worldY, null);
        }
        if (dyingcounter > 3 * i) {
            dying = false;
            alive = false;
//            gamePanel.player = null;
            gamePanel.gameState = gamePanel.gameoverState;
        }
    }
}