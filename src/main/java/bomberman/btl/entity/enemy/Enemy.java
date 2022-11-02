package bomberman.btl.entity.enemy;

import bomberman.btl.entity.Entity;
import bomberman.btl.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends Entity {
    public BufferedImage dead1 = setupImage("/enemy/mob_dead1");
    public BufferedImage dead2 = setupImage("/enemy/mob_dead2");
    public BufferedImage dead3 = setupImage("/enemy/mob_dead3");
    public int dyingcounter = 0;

    public Enemy(GamePanel gamePanel) {
        super(gamePanel);
        this.name = "enemy";
        alive = true;
        dying = false;
    }

    public Enemy(GamePanel gamePanel, int col, int row) {
        super(gamePanel);
        this.name = "enemy";
        alive = true;
        dying = false;
        worldX = col * gamePanel.tileSize;
        worldY = row * gamePanel.tileSize;
    }

    @Override
    public void setMove() {
        collisionOn = false;
        gamePanel.collisionChecker.checkTileEntity(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.bombs);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.interactiveTiles);
        boolean attackPlayer = gamePanel.collisionChecker.checkPlayer(this);
        if (attackPlayer) {
            gamePanel.player.dying = true;
        }
//        boolean contactBomb = gamePanel.collisionChecker.checkBomb(this);
//        if (contactBomb){
//            dying = true;
//        }

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

    @Override
    public void draw(Graphics2D graphics2D) {
        try {
            if (dying == true) {
                dyingAnimation(graphics2D);
            }
            else{
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
            graphics2D.drawImage(dead1, worldX, worldY, null);
        }
        if (dyingcounter > i && dyingcounter <= 2 * i) {
            graphics2D.drawImage(dead2, worldX, worldY, null);
        }
        if (dyingcounter > 2 * i && dyingcounter <= 3 * i) {
            graphics2D.drawImage(dead3, worldX, worldY, null);
        }
        if (dyingcounter > 3*i){
            dying = false;
            alive = false;
        }
    }
}
