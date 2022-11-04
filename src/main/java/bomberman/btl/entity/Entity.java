package bomberman.btl.entity;

import bomberman.btl.entity.weapon.Fireball;
import bomberman.btl.entity.weapon.Projectile;
import bomberman.btl.graphics.UtilityTool;
import bomberman.btl.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    public int worldX;
    public int worldY;
    public int speed;

    public GamePanel gamePanel;

    public boolean alive;
    public boolean dying;

    public BufferedImage up1, up2, up3;
    public BufferedImage down1, down2, down3;
    public BufferedImage left1, left2, left3;
    public BufferedImage right1, right2, right3;

    //Object
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public UtilityTool utilityTool = new UtilityTool();

    public String direction = "down";
    public Fireball fireball;

    //Path finder
    public boolean onPath = false;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    //Collision.
    public Rectangle solidArea;
    public Rectangle solidAreaTop, solidAreaBottom, solidAreaLeft, solidAreaRight;

    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    //Interval for Enemy
    public int actionLockCounter = 0;
    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setAction() {

    }

    public void setMove() {

    }

    public void update() {
        setAction();

        setMove();

        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) spriteNum = 2;
            else if (spriteNum == 2) spriteNum = 3;
            else if (spriteNum == 3) spriteNum = 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D graphics2D) {
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
            graphics2D.drawImage(image, worldX, worldY, null);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    public BufferedImage setupImage(String imgPath) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/img" + imgPath + ".png"));
            image = utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}