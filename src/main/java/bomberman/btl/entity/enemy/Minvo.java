package bomberman.btl.entity.enemy;

import bomberman.btl.entity.weapon.Fireball;
import bomberman.btl.main.GamePanel;

import java.awt.*;
import java.util.Random;

public class Minvo extends Enemy {
    public Minvo(GamePanel gamePanel) {
        super(gamePanel);
        direction = "down";
        name = "minvo";
        speed = 1;
        solidArea = new Rectangle(3, 3, 42, 45);
        fireball = new Fireball(this.gamePanel);
        getImage();
    }

    public Minvo(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        direction = "down";
        name = "minvo";
        speed = 1;
        solidArea = new Rectangle(3, 3, 42, 45);
        fireball = new Fireball(this.gamePanel);
        getImage();
    }

    public void getImage() {
        dead = setupImage("/enemy/minvo/minvo_dead");
        up1 = setupImage("/enemy/minvo/minvo_left1");
        up2 = setupImage("/enemy/minvo/minvo_left2");
        up3 = setupImage("/enemy/minvo/minvo_left3");
        down1 = setupImage("/enemy/minvo/minvo_left1");
        down2 = setupImage("/enemy/minvo/minvo_left2");
        down3 = setupImage("/enemy/minvo/minvo_left3");
        left1 = setupImage("/enemy/minvo/minvo_left1");
        left2 = setupImage("/enemy/minvo/minvo_left2");
        left3 = setupImage("/enemy/minvo/minvo_left3");
        right1 = setupImage("/enemy/minvo/minvo_right1");
        right2 = setupImage("/enemy/minvo/minvo_right2");
        right3 = setupImage("/enemy/minvo/minvo_right3");
    }

    @Override
    public void setAction() {
        if (onPath) {
            if (gamePanel.player != null){
                int goalCol = (gamePanel.player.worldX + gamePanel.player.solidArea.x) / gamePanel.tileSize;
                int goalRow = (gamePanel.player.worldY + gamePanel.player.solidArea.y) / gamePanel.tileSize;
                searchPath(goalCol, goalRow);
                int i = new Random().nextInt(100)+1;
                if (fireball.alive == false && shotAvailableCounter == 30){
                    fireball.set(worldX, worldY, direction, true, this);
                    gamePanel.fireballs.add(fireball);
                    shotAvailableCounter = 0;
                }
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

    public void update() {
        super.update();

        if (gamePanel.player != null){
            int xDistance = Math.abs(worldX - gamePanel.player.worldX);
            int yDistance = Math.abs(worldY - gamePanel.player.worldY);
            int tileDistance = (xDistance + yDistance) / gamePanel.tileSize;

            if (onPath == false && tileDistance < 5) {
                onPath = true;
            }
            if (onPath == true && tileDistance > 10) {
                onPath = false;
            }
        }
    }

    public void shootFireBall(){

    }
}
