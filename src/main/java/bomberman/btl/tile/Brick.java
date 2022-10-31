package bomberman.btl.tile;

import bomberman.btl.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Brick extends InteractiveTile{
    public Brick(GamePanel gamePanel, int col, int row){
        super(gamePanel, col, row);
        this.gamePanel = gamePanel;
        this.worldX = gamePanel.tileSize * col;
        this.worldY = gamePanel.tileSize * row;
        down1 = setupImage("/tile/brick");
        dead1 = setupImage("/tile/brick_exploded");
        dead2 = setupImage("/tile/brick_exploded1");
        dead3 = setupImage("/tile/brick_exploded2");
        destructible = true;
    }

}
