package bomberman.btl.object;

import bomberman.btl.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D graphics2D, GamePanel gamePanel) {
        graphics2D.drawImage(image, worldX, worldY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
