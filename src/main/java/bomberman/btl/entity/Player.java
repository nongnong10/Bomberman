package bomberman.btl.entity;

import bomberman.btl.entity.weapon.Bomb;
import bomberman.btl.entity.weapon.Flame;
import bomberman.btl.entity.weapon.Projectile;
import bomberman.btl.main.GamePanel;
import bomberman.btl.input.KeyInput;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    //Trang thai Power up
    public static long timer = 0;
    public boolean wallpass = false;

    public int numBomb = 1;

    public int life = 3;
    public BufferedImage dead1, dead2, dead3;
    public int dyingcounter = 0;
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

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        this.worldX = gamePanel.tileSize;
        this.worldY = gamePanel.tileSize;
        speed = 4;
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
        if (keyInput.upPressed || keyInput.downPressed || keyInput.leftPressed || keyInput.rightPressed || keyInput.bombPressed) {
            if (keyInput.upPressed) {
                direction = "up";
            } else if (keyInput.downPressed) {
                direction = "down";
            } else if (keyInput.leftPressed) {
                direction = "left";
            } else if (keyInput.rightPressed) {
                direction = "right";
            } else if (keyInput.bombPressed) {
                placeBomb();
                return;
            }

            //check tile collision
            collisionOn = false;
            gamePanel.collisionChecker.checkTilePlayer(this);
            if (wallpass) {
                collisionOn = false;
                timer++;
            }

            //check object collision
            int objInd = gamePanel.collisionChecker.checkObject(this, true);
            pickupObject(objInd);

            //
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
        }
    }

    public void pickupObject(int index) {
        if (index != 999) {
            String objectName = gamePanel.objects[index].name;
            System.out.println(objectName);
            switch (objectName) {
                case "wallpass":
                    wallpass = true;
                    timer = System.currentTimeMillis();
                    break;
                case "door":
                    gamePanel.ui.gameFinished = true;
                    break;
            }
            gamePanel.objects[index] = null;
        }
    }

    public void interactEnemy(int index) {
        if (index != 999) {
            System.out.println("Hit a enemy!");
            gamePanel.player.dying = true;
        }
    }

    public void damageEnemy(int index) {
        if (index != 999) {
            gamePanel.enemies[index].dying = true;
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
            //System.out.println(numBomb + " : " + xBomb/ gamePanel.tileSize + " " + ncol/gamePanel.tileSize);
            gamePanel.bombs[numBomb] = new Bomb(gamePanel, xBomb, yBomb, this);
            gamePanel.projectiles.add(gamePanel.bombs[numBomb]);
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
        System.out.println(dyingcounter);
        if (dyingcounter <= i) {
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
        }
    }
}
