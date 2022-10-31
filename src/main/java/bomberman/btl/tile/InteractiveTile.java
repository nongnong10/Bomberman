package bomberman.btl.tile;

import bomberman.btl.entity.Entity;
import bomberman.btl.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InteractiveTile extends Entity {
    public boolean destructible = false;
    public int dyingcounter = 0;
    public BufferedImage dead1, dead2, dead3;
    GamePanel gamePanel;

    public InteractiveTile(GamePanel gamePanel, int col, int row) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        direction = "down";
        alive = true;
        dying = false;
        collision = true;
        solidArea = new Rectangle(0, 0, gamePanel.tileSize, gamePanel.tileSize);
    }

    public void update() {
        setAction();

        setMove();
    }

    public void draw(Graphics2D graphics2D) {
        if (dying == true) {
            dyingAnimation(graphics2D);
        } else {
            graphics2D.drawImage(down1, worldX, worldY, null);
        }
    }

    public void dyingAnimation(Graphics2D graphics2D) {
        dyingcounter++;
        int i = 20;
        System.out.println(dyingcounter);
        if (dyingcounter <= i) {
            graphics2D.drawImage(dead1, worldX, worldY, null);
        }
        if (dyingcounter > i && dyingcounter <= 2 * i) {
            graphics2D.drawImage(dead2, worldX, worldY, null);
        }
        if (dyingcounter > 2 * i && dyingcounter <= 3 * i) {
            graphics2D.drawImage(dead3, worldX, worldY, null);
        }
        if (dyingcounter > 3 * i) {
            dying = false;
            alive = false;
        }
    }
}
