package bomberman.btl.entity;

import bomberman.btl.graphics.UtilityTool;
import bomberman.btl.main.GamePanel;
import bomberman.btl.input.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.System.exit;

public class Player extends Entity {
    //Trang thai Power up
    public static long timer = 0;
    public boolean wallpass = false;

    public int numBomb = 1;
    GamePanel gamePanel;
    KeyInput keyInput;

    public Player(GamePanel gamePanel, KeyInput keyInput) {
        this.gamePanel = gamePanel;
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

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        this.worldX = gamePanel.tileSize;
        this.worldY = gamePanel.tileSize;
        speed = 4;
    }

    public void getPlayerImage() {
        up1 = setupImage("player_up");
        up2 = setupImage("player_up_1");
        up3 = setupImage("player_up_2");
        down1 = setupImage("player_down");
        down2 = setupImage("player_down_1");
        down3 = setupImage("player_down_2");
        left1 = setupImage("player_left");
        left2 = setupImage("player_left_1");
        left3 = setupImage("player_left_2");
        right1 = setupImage("player_right");
        right2 = setupImage("player_right_1");
        right3 = setupImage("player_right_2");
    }

    public BufferedImage setupImage(String imgPath) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/img/player/" + imgPath + ".png"));
            image = utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);

        } catch (IOException e){
            e.printStackTrace();
        }
        return image;
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
            gamePanel.collisionChecker.checkTile(this);
            if (wallpass) {
                collisionOn = false;
                timer++;
            }

            //fix bug in wall
//            int nrow = this.worldY / gamePanel.tileSize;
//            int ncol = this.worldX / gamePanel.tileSize;
//            char tmp = gamePanel.tileManager.mapTile[nrow][ncol];
//            if (tmp == '#' || tmp == '*'){
//                collisionOn = false;
//            }

            //check object collision
            int objInd = gamePanel.collisionChecker.checkObject(this, true);
            pickupObject(objInd);

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
                System.out.println(now - timer);
                if (now - timer >= 5000) {
                    wallpass = false;
                    timer = 0;
                }
            }

        }
    }

    public void pickupObject(int index) {
        if (index != 999) {
            String objectName = gamePanel.objects[index].name;
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

    public void draw(Graphics2D graphics2D) {
//        graphics2D.setColor(Color.white);
//        graphics2D.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
        try {
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
            graphics2D.drawImage(image, worldX, worldY, gamePanel.tileSize, gamePanel.tileSize, null);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
}
