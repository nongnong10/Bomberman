package bomberman.btl.entity.weapon;

import bomberman.btl.entity.Entity;
import bomberman.btl.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bomb extends Projectile {
    //Thoi gian cho no
    public static int lifeTime;

    //Chua no
    public boolean alive;

    public Bomb(GamePanel gamePanel, int x, int y, Entity user) {
        super(gamePanel, x, y, user);
        name = "bomb";
        alive = true;
        direction = "down";
        lifeTime = 0;
        collision = true;
        solidArea = new Rectangle(3, 6, 42, 39);
        getImage();
    }

    public void getImage() {
        down1 = setupImage("/bomb/bomb");
        down2 = setupImage("/bomb/bomb_1");
        down3 = setupImage("/bomb/bomb_2");
    }

    @Override
    public void setAction() {
        lifeTime++;
        if (lifeTime >= 120) {
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
        if (spriteCounter > 10) {
            if (spriteNum == 1) spriteNum = 2;
            else if (spriteNum == 2) spriteNum = 3;
            else if (spriteNum == 3) spriteNum = 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;
        if (spriteNum == 1) {
            image = down1;
        }
        if (spriteNum == 2) {
            image = down2;
        }
        if (spriteNum == 3) {
            image = down3;
        }
        graphics2D.drawImage(image, worldX, worldY, null);
    }
}
