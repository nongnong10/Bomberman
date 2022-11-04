package bomberman.btl.entity.weapon;

import bomberman.btl.entity.Entity;
import bomberman.btl.main.GamePanel;

import java.awt.*;

public class Fireball extends Projectile {
    public Fireball(GamePanel gamePanel) {
        super(gamePanel);
        name = "fireball";
        alive = false;
        solidArea = new Rectangle(3, 3, 42, 42);
        getImage();
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.collisionOn = false;
        this.alive = alive;
        this.user = user;
        this.speed = 3;
        this.lifeTime = 0;
    }

    public void getImage() {
        up1 = setupImage("/fireball/fireball_up");
        up2 = setupImage("/fireball/fireball_up");
        up3 = setupImage("/fireball/fireball_up");
        down1 = setupImage("/fireball/fireball_down");
        down2 = setupImage("/fireball/fireball_down");
        down3 = setupImage("/fireball/fireball_down");
        left1 = setupImage("/fireball/fireball_left");
        left2 = setupImage("/fireball/fireball_left");
        left3 = setupImage("/fireball/fireball_left");
        right1 = setupImage("/fireball/fireball_right");
        right2 = setupImage("/fireball/fireball_right");
        right3 = setupImage("/fireball/fireball_right");
    }

    public void update() {
        if (user == gamePanel.player) {
            int enemyInd = gamePanel.collisionChecker.checkEntity(this, gamePanel.enemies);
            if (enemyInd != 999) {
                gamePanel.player.damageEnemy(enemyInd);
                alive = false;
            }
            collisionOn = false;
            boolean contactTile = gamePanel.collisionChecker.checkTileEntity(this);
            if (contactTile) {
                alive = false;
            }
            int tileInd = gamePanel.collisionChecker.checkEntity(this, gamePanel.interactiveTiles);
            if (tileInd != 999) {
                alive = false;
            }
        } else {

        }

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

        lifeTime++;
        if (lifeTime >= 120) {
            alive = false;
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 1;
            }
        }
    }
}
