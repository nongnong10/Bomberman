package bomberman.btl.main;

import bomberman.btl.entity.Entity;

public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
//        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
//        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
//        int entityTopWorldY = entity.worldY + entity.solidArea.y;
//        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftWorldX, entityRightWorldX, entityTopWorldY, entityBottomWorldY;
        int entityLeftCol, entityRightCol, entityTopRow, entityBottomRow;

        int tileNum1;
        int tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopWorldY = entity.worldY + entity.solidAreaTop.y;
                entityLeftWorldX = entity.worldX + entity.solidAreaTop.x;
                entityRightWorldX = entity.worldX + entity.solidAreaTop.x + entity.solidAreaTop.width;

                entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
                entityRightCol = entityRightWorldX / gamePanel.tileSize;
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;

                tileNum1 = gamePanel.tileManager.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityTopRow][entityRightCol];
                System.out.println("up");
                System.out.println(entityLeftCol + " " + entityTopRow);
                System.out.println(entityRightCol + " " + entityTopRow);
                if (gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomWorldY = entity.worldY + entity.solidAreaBottom.y + entity.solidAreaBottom.height;
                entityLeftWorldX = entity.worldX + entity.solidAreaBottom.x;
                entityRightWorldX = entity.worldX + entity.solidAreaBottom.x + entity.solidAreaBottom.width;

                entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
                entityRightCol = entityRightWorldX / gamePanel.tileSize;
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;

                tileNum1 = gamePanel.tileManager.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityBottomRow][entityRightCol];
                System.out.println("down");
                System.out.println(entityLeftCol + " " + entityBottomRow);
                System.out.println(entityRightCol + " " + entityBottomRow);
                if (gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftWorldX = entity.worldX + entity.solidAreaLeft.x;
                entityTopWorldY = entity.worldY + entity.solidAreaLeft.y;
                entityBottomWorldY = entity.worldY + entity.solidAreaLeft.y + entity.solidAreaLeft.height;

                entityTopRow = entityTopWorldY / gamePanel.tileSize;
                entityBottomRow = entityBottomWorldY / gamePanel.tileSize;
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;

                tileNum1 = gamePanel.tileManager.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityBottomRow][entityLeftCol];
                System.out.println("left");
                System.out.println(entityLeftCol + " " + entityTopRow);
                System.out.println(entityLeftCol + " " + entityBottomRow);
                if (gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightWorldX = entity.worldX + entity.solidAreaRight.x + entity.solidAreaRight.width;
                entityTopWorldY = entity.worldY + entity.solidAreaRight.y;
                entityBottomWorldY = entity.worldY + entity.solidAreaRight.y + entity.solidAreaRight.height;

                entityTopRow = entityTopWorldY / gamePanel.tileSize;
                entityBottomRow = entityBottomWorldY / gamePanel.tileSize;
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityBottomRow][entityRightCol];
                System.out.println("right");
                System.out.println(entityRightCol + " " + entityTopRow);
                System.out.println(entityRightCol + " " + entityBottomRow);
                if (gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
