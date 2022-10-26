package bomberman.btl.main;

import bomberman.btl.object.item.Wallpass;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    public static double playTime = 0;
    public GamePanel gamePanel;
    public Font arial20;
    public Font arial40;
    public BufferedImage wallpassImg;
    public boolean gameFinished = false;
    DecimalFormat dformat = new DecimalFormat("#0.00");

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.arial20 = new Font("Arial", Font.BOLD, 20);
        this.arial40 = new Font("Arial", Font.BOLD, 40);

        Wallpass wallpass = new Wallpass();
        wallpassImg = wallpass.image;
    }

    public void draw(Graphics2D graphics2D) {
        if (gameFinished == true) {
            graphics2D.setFont(arial40);
            graphics2D.setColor(Color.yellow);

            String text;
            int textLength;
            int x, y;

            text = "YOU WIN!";
            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = (gamePanel.screenHeight - gamePanel.statusHeight) / 2;
            graphics2D.drawString(text, x, y);

            graphics2D.setFont(arial20);
            graphics2D.setColor(Color.white);
            text = "Your time play is : " + dformat.format(playTime) + "s !";
            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = (gamePanel.screenHeight - gamePanel.statusHeight) / 2 + gamePanel.tileSize;
            graphics2D.drawString(text, x, y);

            gamePanel.gameThread = null;
        } else {
            graphics2D.setFont(arial20);
            graphics2D.setColor(Color.white);
            graphics2D.drawString("Bomb = " + gamePanel.player.numBomb, 50, 557);

            playTime += (double) 1 / 60;
            graphics2D.drawString("Time : " + dformat.format(playTime), 200, 557);


            if (gamePanel.player.wallpass == true) {
                graphics2D.drawImage(wallpassImg, (gamePanel.maxScreenCol - 1) * gamePanel.tileSize,
                        (gamePanel.maxScreenRow) * gamePanel.tileSize + 7, gamePanel.tileSize / 3 * 2, gamePanel.tileSize / 3 * 2, null);
            }
        }
    }
}
