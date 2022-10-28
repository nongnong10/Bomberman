package bomberman.btl.main;

import java.awt.*;

public class EventHandler {
    public GamePanel gamePanel;
    public Rectangle eventRect;
    public int eventRectDefaultX;
    public int eventRectDefaultY;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        eventRect = new Rectangle(0, 0, gamePanel.tileSize, gamePanel.tileSize);
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {
        if (hit(2, 1, "any")) {
            damagePit();
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;
        gamePanel.player.solidArea.x += gamePanel.player.worldX;
        gamePanel.player.solidArea.y += gamePanel.player.worldY;
        eventRect.x += eventCol * gamePanel.tileSize;
        eventRect.y += eventRow * gamePanel.tileSize;

        if (gamePanel.player.solidArea.intersects(eventRect)) {
            if (gamePanel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;
        return hit;
    }

    public void damagePit() {
        System.out.println("You died");
    }
}
