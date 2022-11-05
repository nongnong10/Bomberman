package bomberman.btl.entity.enemy;

import bomberman.btl.main.GamePanel;

import java.awt.*;
import java.util.Random;

public class Ballom extends Enemy {
    public Ballom(GamePanel gamePanel) {
        super(gamePanel);
        direction = "down";
        name = "ballom";
        speed = 1;
        solidArea = new Rectangle(3, 0, 42, 42);
        getImage();
    }

    public Ballom(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        direction = "down";
        name = "ballom";
        speed = 1;
        solidArea = new Rectangle(3, 0, 42, 42);
        getImage();
    }

    public void getImage() {
        dead = setupImage("/enemy/ballom/balloom_dead");
        up1 = setupImage("/enemy/ballom/balloom_left1");
        up2 = setupImage("/enemy/ballom/balloom_left2");
        up3 = setupImage("/enemy/ballom/balloom_left3");
        down1 = setupImage("/enemy/ballom/balloom_left1");
        down2 = setupImage("/enemy/ballom/balloom_left2");
        down3 = setupImage("/enemy/ballom/balloom_left3");
        left1 = setupImage("/enemy/ballom/balloom_left1");
        left2 = setupImage("/enemy/ballom/balloom_left2");
        left3 = setupImage("/enemy/ballom/balloom_left3");
        right1 = setupImage("/enemy/ballom/balloom_right1");
        right2 = setupImage("/enemy/ballom/balloom_right2");
        right3 = setupImage("/enemy/ballom/balloom_right3");
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
