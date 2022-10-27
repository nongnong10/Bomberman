package bomberman.btl.entity.weapon;

import bomberman.btl.entity.Entity;
import bomberman.btl.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Flame extends Projectile{
    public static int lifeTime;

    public BufferedImage image1, image2, image3;
    public BufferedImage leftLast1, leftLast2, leftLast3;
    public BufferedImage rightLast1, rightLast2, rightLast3;
    public BufferedImage upLast1, upLast2, upLast3;
    public BufferedImage downLast1, downLast2, downLast3;

    //Chua no
    public boolean alive;

    public Flame(GamePanel gamePanel, int x, int y, Entity user) {
        super(gamePanel, x, y, user);
        name = "flame";
        alive = true;
        direction = "down";
        lifeTime = 0;
        collision = true;
        solidArea = new Rectangle(3, 6, 42, 39);
        getImage();
    }

    public void getImage() {
        image1 = setupImage("/bomb/bomb_exploded");
        image2 = setupImage("/bomb/bomb_exploded1");
        image3 = setupImage("/bomb/bomb_exploded2");
        left1 = setupImage("/bomb/explosion_horizontal");
        left2 = setupImage("/bomb/explosion_horizontal1");
        left3 = setupImage("/bomb/explosion_horizontal2");
        leftLast1 = setupImage("/bomb/explosion_horizontal_left_last");
        leftLast2 = setupImage("/bomb/explosion_horizontal_left_last1");
        leftLast3 = setupImage("/bomb/explosion_horizontal_left_last2");
        rightLast1 = setupImage("/bomb/explosion_horizontal_right_last");
        rightLast2 = setupImage("/bomb/explosion_horizontal_right_last1");
        rightLast3 = setupImage("/bomb/explosion_horizontal_right_last2");
        up1 = setupImage("/bomb/explosion_vertical");
        up2 = setupImage("/bomb/explosion_vertical1");
        up3 = setupImage("/bomb/explosion_vertical2");
        upLast1 = setupImage("/bomb/explosion_vertical_top_last");
        upLast2 = setupImage("/bomb/explosion_vertical_top_last1");
        upLast3 = setupImage("/bomb/explosion_vertical_top_last2");
        downLast1 = setupImage("/bomb/explosion_vertical_down_last");
        downLast2 = setupImage("/bomb/explosion_vertical_down_last1");
        downLast3 = setupImage("/bomb/explosion_vertical_down_last2");
    }

    @Override
    public void setAction() {
        lifeTime++;
        if (lifeTime >= 60) {
            alive = false;
            lifeTime = 0;
        }
    }

    @Override
    public void setMove() {
        collisionOn = false;
        gamePanel.collisionChecker.checkTileEntity(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkPlayer(this);
    }

    @Override
    public void update() {
        setAction();

        setMove();

        spriteCounter++;
        if (spriteCounter > 15) {
            if (spriteNum == 1) spriteNum = 2;
            else if (spriteNum == 2) spriteNum = 3;
            else if (spriteNum == 3) spriteNum = 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;
        if (spriteNum == 1) {
            graphics2D.drawImage(image1, worldX, worldY, null);
            //graphics2D.drawImage(left1, worldX - gamePanel.tileSize, worldY, null);
            graphics2D.drawImage(leftLast1, worldX - gamePanel.tileSize, worldY, null);
            graphics2D.drawImage(rightLast1, worldX + gamePanel.tileSize, worldY, null);
            graphics2D.drawImage(upLast1, worldX, worldY - gamePanel.tileSize, null);
            graphics2D.drawImage(downLast1, worldX, worldY + gamePanel.tileSize, null);
        }
        if (spriteNum == 2) {
            graphics2D.drawImage(image2, worldX, worldY, null);
            //graphics2D.drawImage(left2, worldX - gamePanel.tileSize, worldY, null);
            graphics2D.drawImage(leftLast2, worldX - gamePanel.tileSize, worldY, null);
            graphics2D.drawImage(rightLast2, worldX + gamePanel.tileSize, worldY, null);
            graphics2D.drawImage(upLast2, worldX, worldY - gamePanel.tileSize, null);
            graphics2D.drawImage(downLast2, worldX, worldY + gamePanel.tileSize, null);
        }
        if (spriteNum == 3) {
            graphics2D.drawImage(image3, worldX, worldY, null);
            //graphics2D.drawImage(left3, worldX - gamePanel.tileSize, worldY, null);
            graphics2D.drawImage(leftLast3, worldX - gamePanel.tileSize, worldY, null);
            graphics2D.drawImage(rightLast3, worldX + gamePanel.tileSize, worldY, null);
            graphics2D.drawImage(upLast3, worldX, worldY - gamePanel.tileSize, null);
            graphics2D.drawImage(downLast3, worldX, worldY + gamePanel.tileSize, null);
        }
    }
}
