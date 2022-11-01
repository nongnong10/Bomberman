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
    public int commandNum = 0;
    DecimalFormat dformat = new DecimalFormat("#0.00");

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.arial20 = new Font("Arial", Font.BOLD, 20);
        this.arial40 = new Font("Arial", Font.BOLD, 40);

        Wallpass wallpass = new Wallpass(this.gamePanel);
        wallpassImg = wallpass.down1;
    }

    public int getXForCenterText(String text, Graphics2D graphics2D) {
        int textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        int x = gamePanel.screenWidth / 2 - textLength / 2;
        return x;
    }

    public void draw(Graphics2D graphics2D) {
        //PLAY STATE
        if (gamePanel.gameState == gamePanel.playState) {
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

        //PAUSED STATE
        if (gamePanel.gameState == gamePanel.pauseState) {
            drawPauseScreen(graphics2D);
            graphics2D.setFont(arial20);
            graphics2D.setColor(Color.white);
            graphics2D.drawString("Bomb = " + gamePanel.player.numBomb, 50, 557);
            graphics2D.drawString("Time : " + dformat.format(playTime), 200, 557);
            if (gamePanel.player.wallpass == true) {
                graphics2D.drawImage(wallpassImg, (gamePanel.maxScreenCol - 1) * gamePanel.tileSize,
                        (gamePanel.maxScreenRow) * gamePanel.tileSize + 7, gamePanel.tileSize / 3 * 2, gamePanel.tileSize / 3 * 2, null);
            }
        }

        //GAME OVER STATE
        if (gamePanel.gameState == gamePanel.gameoverState){
            drawGameOverScreen(graphics2D);
        }
    }

    public void drawGameOverScreen(Graphics2D graphics2D){
        //half transparent black
        graphics2D.setColor(new Color(0, 0, 0, 150));
        graphics2D.fillRect(0, 0,gamePanel.screenWidth, gamePanel.screenHeight);

        int x, y;
        String text;
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 110f));

        //Shadow
        text = "Game Over";
        graphics2D.setColor(Color.BLACK);
        x = getXForCenterText(text, graphics2D);
        y = gamePanel.tileSize * 4;
        graphics2D.drawString(text, x, y);

        //Main
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString(text, x-4, y-4);

        //Retry
        text = "Retry";
        graphics2D.setFont(graphics2D.getFont().deriveFont(40f));
        x = getXForCenterText(text, graphics2D);
        y += gamePanel.tileSize*4;
        graphics2D.drawString(text, x, y);
        if (commandNum == 0){
            graphics2D.drawString(">", x-40, y);
        }

        //Back to title screen
        text = "Quit";
        graphics2D.setFont(graphics2D.getFont().deriveFont(40f));
        x = getXForCenterText(text, graphics2D);
        y += gamePanel.tileSize + 10;
        graphics2D.drawString(text, x, y);
        if (commandNum == 1){
            graphics2D.drawString(">", x-40, y);
        }

    }
    public void drawFinishedScreen(Graphics2D graphics2D){
        graphics2D.setFont(arial40);
        graphics2D.setColor(Color.yellow);

        String text;
        int x, y;

        text = "YOU WIN!";
        x = getXForCenterText(text, graphics2D);
        y = (gamePanel.screenHeight - gamePanel.statusHeight) / 2;
        graphics2D.drawString(text, x, y);

        graphics2D.setFont(arial20);
        graphics2D.setColor(Color.white);
        text = "Your time play is : " + dformat.format(playTime) + "s !";
        x = getXForCenterText(text, graphics2D);
        y = (gamePanel.screenHeight - gamePanel.statusHeight) / 2 + gamePanel.tileSize;
        graphics2D.drawString(text, x, y);
    }

    public void drawPauseScreen(Graphics2D graphics2D) {
        graphics2D.setColor(new Color(0, 0, 0, 150));
        graphics2D.fillRect(0, 0,gamePanel.screenWidth, gamePanel.screenHeight);
        String text = "PAUSED";
        int x, y;
        graphics2D.setFont(arial40);
        graphics2D.setColor(Color.white);
        x = getXForCenterText(text, graphics2D);
        y = (gamePanel.screenHeight - gamePanel.statusHeight) / 2;
        graphics2D.drawString(text, x, y);
    }
}
