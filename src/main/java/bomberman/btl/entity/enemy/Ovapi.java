package bomberman.btl.entity.enemy;

import bomberman.btl.main.GamePanel;

import java.awt.*;
import java.util.Random;

public class Ovapi extends Enemy {
    public int time = 0;

    public Ovapi(GamePanel gamePanel) {
        super(gamePanel);
        direction = "down";
        name = "ovapi";
        speed = 1;
        solidArea = new Rectangle(3, 3, 42, 45);
        getImage();
    }

    public Ovapi(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        direction = "down";
        name = "ovapi";
        speed = 1;
        solidArea = new Rectangle(3, 3, 42, 45);
        getImage();
    }

    public void getImage() {
        dead = setupImage("/enemy/ovapi/ovapi_dead");
        up1 = setupImage("/enemy/ovapi/ovapi_left1");
        up2 = setupImage("/enemy/ovapi/ovapi_left2");
        up3 = setupImage("/enemy/ovapi/ovapi_left3");
        down1 = setupImage("/enemy/ovapi/ovapi_left1");
        down2 = setupImage("/enemy/ovapi/ovapi_left2");
        down3 = setupImage("/enemy/ovapi/ovapi_left3");
        left1 = setupImage("/enemy/ovapi/ovapi_left1");
        left2 = setupImage("/enemy/ovapi/ovapi_left2");
        left3 = setupImage("/enemy/ovapi/ovapi_left3");
        right1 = setupImage("/enemy/ovapi/ovapi_right1");
        right2 = setupImage("/enemy/ovapi/ovapi_right2");
        right3 = setupImage("/enemy/ovapi/ovapi_right3");
    }

    @Override
    public void setAction() {
        if (onPath) {
            if (gamePanel.player != null) {
                int goalCol = (gamePanel.player.worldX + gamePanel.player.solidArea.x) / gamePanel.tileSize;
                int goalRow = (gamePanel.player.worldY + gamePanel.player.solidArea.y) / gamePanel.tileSize;
                searchPath(goalCol, goalRow);
            }
        } else {
            actionLockCounter++;
            //120 frame = 2s
            if (actionLockCounter >= 60) {
                Random random = new Random();
                int i = random.nextInt(100) + 1; //random from 1 to 100;
                if (i <= 25) {
                    direction = "up";
                } else if (i > 25 && i <= 50) {
                    direction = "down";
                } else if (i > 50 && i <= 75) {
                    direction = "left";
                } else {
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }
    }

    @Override
    public void update() {
        super.update();
        time++;
        if (gamePanel.player != null) {
            int xDistance = Math.abs(worldX - gamePanel.player.worldX);
            int yDistance = Math.abs(worldY - gamePanel.player.worldY);
            int tileDistance = (xDistance + yDistance) / gamePanel.tileSize;

            if (tileDistance < 7) {
                if (time <= 30) {
                    onPath = true;
                    speed = 4;
                } else {
                    time %= 60;
                    onPath = false;
                    speed = 1;
                }
            } else {
                onPath = false;
            }

        }

        int i = new Random().nextInt(100) + 1;
        if (i > 60) {
            isInvisible = true;
        } else {
            isInvisible = false;
        }
    }
}
