package bomberman.btl.main;

import bomberman.btl.object.item.Wallpass;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    public GamePanel gamePanel;
    public Font arial40;
    public BufferedImage wallpassImg;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.arial40 = new Font("Arial", Font.BOLD, 20);

        Wallpass wallpass = new Wallpass();
        wallpassImg = wallpass.image;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setFont(arial40);
        graphics2D.setColor(Color.white);
        graphics2D.drawString("Bomb = " + gamePanel.player.numBomb, 50, 557);

        if (gamePanel.player.wallpass == true) {
            graphics2D.drawImage(wallpassImg, (gamePanel.maxScreenCol-1)*gamePanel.tileSize,
                    (gamePanel.maxScreenRow)*gamePanel.tileSize+7, gamePanel.tileSize/3*2, gamePanel.tileSize/3*2, null);
        }
    }
}
