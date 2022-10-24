package bomberman.btl.entity;

import bomberman.btl.GamePanel;
import bomberman.btl.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyInput keyInput;

    public Player(GamePanel gamePanel, KeyInput keyInput) {
        this.gamePanel = gamePanel;
        this.keyInput = keyInput;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        this.x = 32;
        this.y = 32;
        speed = 4;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/img/player_up.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/img/player_up_1.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/img/player_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/img/player_down.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/img/player_down_1.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/img/player_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/img/player_left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/img/player_left_1.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/img/player_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/img/player_right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/img/player_right_1.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/img/player_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyInput.upPressed || keyInput.downPressed || keyInput.leftPressed || keyInput.rightPressed) {
            if (keyInput.upPressed) {
                direction = "up";
                y -= speed;
            } else if (keyInput.downPressed) {
                direction = "down";
                y += speed;
            } else if (keyInput.leftPressed) {
                direction = "left";
                x -= speed;
            } else if (keyInput.rightPressed) {
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) spriteNum = 2;
                else if (spriteNum == 2) spriteNum = 3;
                else if (spriteNum == 3) spriteNum = 1;
                spriteCounter = 0;
            }
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
            graphics2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
}
