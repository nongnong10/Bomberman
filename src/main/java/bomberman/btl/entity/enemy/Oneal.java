package bomberman.btl.entity.enemy;

import bomberman.btl.main.GamePanel;

import java.awt.*;
import java.util.Random;

public class Oneal extends Enemy{
    public Oneal(GamePanel gamePanel) {
        super(gamePanel);
        direction = "down";
        name = "oneal";
        speed = 1;
        solidArea = new Rectangle(3, 3, 42, 45);
        getImage();
    }

    public Oneal(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        direction = "down";
        name = "oneal";
        speed = 1;
        solidArea = new Rectangle(3, 3, 42, 45);
        getImage();
    }

    public void getImage() {
        dead = setupImage("/enemy/oneal/oneal_dead");
        up1 = setupImage("/enemy/oneal/oneal_left1");
        up2 = setupImage("/enemy/oneal/oneal_left2");
        up3 = setupImage("/enemy/oneal/oneal_left3");
        down1 = setupImage("/enemy/oneal/oneal_left1");
        down2 = setupImage("/enemy/oneal/oneal_left2");
        down3 = setupImage("/enemy/oneal/oneal_left3");
        left1 = setupImage("/enemy/oneal/oneal_left1");
        left2 = setupImage("/enemy/oneal/oneal_left2");
        left3 = setupImage("/enemy/oneal/oneal_left3");
        right1 = setupImage("/enemy/oneal/oneal_right1");
        right2 = setupImage("/enemy/oneal/oneal_right2");
        right3 = setupImage("/enemy/oneal/oneal_right3");
    }

    @Override
    public void setAction() {
        actionLockCounter++;
        //120 frame = 2s
        if (actionLockCounter >= 60){
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
