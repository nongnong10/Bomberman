package bomberman.btl.main;

import bomberman.btl.entity.Entity;

public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * Check va cham voi Tile.
     *
     * @param entity
     */

    public void checkTilePlayer(Entity entity) {
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
                if (gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public boolean checkTileEntity(Entity entity) {
        boolean contactTile = false;
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1 = 0;
        int tileNum2 = 0;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;

                tileNum1 = gamePanel.tileManager.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityTopRow][entityRightCol];
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;

                tileNum1 = gamePanel.tileManager.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityBottomRow][entityRightCol];
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;

                tileNum1 = gamePanel.tileManager.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityBottomRow][entityLeftCol];
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityBottomRow][entityRightCol];
                break;
        }
        if (gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true) {
            entity.collisionOn = true;
            contactTile = true;
        }
        return contactTile;
    }

    /**
     * Check va cham voi Object
     *
     * @param entity
     * @param player kiem tra co phai player khong
     * @return gia tri cua object va cham
     */
    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gamePanel.objects.length; ++i) {
            if (gamePanel.objects[i] != null) {
                //Entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Object's solid area position
                gamePanel.objects[i].solidArea.x += gamePanel.objects[i].worldX;
                gamePanel.objects[i].solidArea.y += gamePanel.objects[i].worldY;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gamePanel.objects[i].solidArea)) {
                            if (gamePanel.objects[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            //Neu la player thi lay index cua object
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gamePanel.objects[i].solidArea)) {
                            if (gamePanel.objects[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gamePanel.objects[i].solidArea)) {
                            if (gamePanel.objects[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gamePanel.objects[i].solidArea)) {
                            if (gamePanel.objects[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gamePanel.objects[i].solidArea.x = gamePanel.objects[i].solidAreaDefaultX;
                gamePanel.objects[i].solidArea.y = gamePanel.objects[i].solidAreaDefaultY;
            }
        }

        return index;
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; ++i) {
            if (target[i] != null) {
                //Entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Object's solid area position
                target[i].solidArea.x += target[i].worldX;
                target[i].solidArea.y += target[i].worldY;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if (entity.solidArea.intersects(target[i].solidArea)) {
                    entity.collisionOn = true;
                    index = i;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }

        return index;
    }

    public boolean checkPlayer(Entity entity) {
        boolean contactPlayer = false;
        if (gamePanel.player == null) {
            return false;
        }
        //Entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        //Object's solid area position
        gamePanel.player.solidArea.x += gamePanel.player.worldX;
        gamePanel.player.solidArea.y += gamePanel.player.worldY;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }
        if (entity.solidArea.intersects(gamePanel.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        return contactPlayer;
    }

//    public boolean checkBomb(Entity entity) {
//        boolean contactBomb = false;
//        //Entity's solid area position
//        entity.solidArea.x = entity.worldX + entity.solidArea.x;
//        entity.solidArea.y = entity.worldY + entity.solidArea.y;
//
//        switch (entity.direction) {
//            case "up":
//                entity.solidArea.y -= entity.speed;
//                break;
//            case "down":
//                entity.solidArea.y += entity.speed;
//                break;
//            case "left":
//                entity.solidArea.x -= entity.speed;
//                break;
//            case "right":
//                entity.solidArea.x += entity.speed;
//                break;
//        }
//
//        int nrow = entity.solidArea.y / gamePanel.tileSize;
//        int ncol = entity.solidArea.x / gamePanel.tileSize;
//        if (gamePanel.flameTile[nrow][ncol] == 1) {
//            contactBomb = true;
//        }
//        entity.solidArea.x = entity.solidAreaDefaultX;
//        entity.solidArea.y = entity.solidAreaDefaultY;
//        return contactBomb;
//    }
}
