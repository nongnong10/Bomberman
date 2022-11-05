package bomberman.btl.entity.enemy;

import bomberman.btl.entity.Entity;
import bomberman.btl.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends Entity {
    public BufferedImage dead;
    public BufferedImage dead1 = setupImage("/enemy/mob_dead1");
    public BufferedImage dead2 = setupImage("/enemy/mob_dead2");
    public BufferedImage dead3 = setupImage("/enemy/mob_dead3");
    public int dyingcounter = 0;
    public boolean soundCheck = false;
    public boolean isInvisible = false;

    public Enemy(GamePanel gamePanel) {
        super(gamePanel);
        this.name = "enemy";
        alive = true;
        dying = false;
    }

    public Enemy(GamePanel gamePanel, int col, int row) {
        super(gamePanel);
        this.name = "enemy";
        alive = true;
        dying = false;
        worldX = col * gamePanel.tileSize;
        worldY = row * gamePanel.tileSize;
    }

    public void checkCollision() {
        collisionOn = false;
        gamePanel.collisionChecker.checkTileEntity(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.bombs);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.interactiveTiles);
        boolean attackPlayer = gamePanel.collisionChecker.checkPlayer(this);
        if (attackPlayer) {
            gamePanel.player.dying = true;
        }
    }

    @Override
    public void setMove() {
        checkCollision();
        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
    }

    public void update() {
        setAction();

        setMove();

        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) spriteNum = 2;
            else if (spriteNum == 2) spriteNum = 3;
            else if (spriteNum == 3) spriteNum = 1;
            spriteCounter = 0;
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        try {
            if (dying == true) {
                dyingAnimation(graphics2D);
            } else {
                BufferedImage image = null;
                switch (direction) {
                    case "up":
                        if (spriteNum == 1) {
                            image = up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                        }
                        if (spriteNum == 3) {
                            image = up3;
                        }
                        break;
                    case "down":
                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                        if (spriteNum == 3) {
                            image = down3;
                        }
                        break;
                    case "left":
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                        if (spriteNum == 3) {
                            image = left3;
                        }
                        break;
                    case "right":
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                        if (spriteNum == 3) {
                            image = right3;
                        }
                        break;
                }
                if (isInvisible == false) {
                    graphics2D.drawImage(image, worldX, worldY, null);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    public void dyingAnimation(Graphics2D graphics2D) {
        dyingcounter++;
        int i = 12;
        if (dyingcounter <= i) {
            if (soundCheck == false) {
                gamePanel.playSoundEffect(6);
                soundCheck = true;
            }
            graphics2D.drawImage(dead, worldX, worldY, null);
        }
        if (dyingcounter > i && dyingcounter <= 2 * i) {
            graphics2D.drawImage(dead1, worldX, worldY, null);
        }
        if (dyingcounter > 2 * i && dyingcounter <= 3 * i) {
            graphics2D.drawImage(dead2, worldX, worldY, null);
        }
        if (dyingcounter > 3 * i && dyingcounter <= 4 * i) {
            graphics2D.drawImage(dead3, worldX, worldY, null);
        }
        if (dyingcounter > 4 * i) {
            dying = false;
            alive = false;
        }
    }

    public void searchPath(int goalCol, int goalRow) {
        int startCol = (worldX + solidArea.x) / gamePanel.tileSize;
        int startRow = (worldY + solidArea.y) / gamePanel.tileSize;

        gamePanel.pathFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if (gamePanel.pathFinder.search() == true) {
            //Next worldX, worldY
            int nextX = gamePanel.pathFinder.pathList.get(0).col * gamePanel.tileSize;
            int nextY = gamePanel.pathFinder.pathList.get(0).row * gamePanel.tileSize;

            //Entity's solidArea position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gamePanel.tileSize) {
                direction = "up";
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gamePanel.tileSize) {
                direction = "down";
            } else if (enTopY >= nextY && enBottomY < nextY + gamePanel.tileSize) {
                if (enLeftX > nextX) {
                    direction = "left";
                }
                if (enLeftX < nextX) {
                    direction = "right";
                }
            } else if (enTopY > nextY && enLeftX > nextX) {
                //up or left
                direction = "up";
                checkCollision();
                if (collisionOn == true) {
                    direction = "left";
                }
            } else if (enTopY > nextY && enLeftX < nextX) {
                //up or right
                direction = "up";
                checkCollision();
                if (collisionOn == true) {
                    direction = "right";
                }
            } else if (enTopY < nextY && enLeftX > nextX) {
                //down or left
                direction = "down";
                checkCollision();
                if (collisionOn == true) {
                    direction = "left";
                }
            } else if (enTopY < nextY && enLeftX < nextX) {
                //down or right
                direction = "down";
                checkCollision();
                if (collisionOn == true) {
                    direction = "right";
                }
            }

            int nextCol = gamePanel.pathFinder.pathList.get(0).col;
            int nextRow = gamePanel.pathFinder.pathList.get(0).row;
            if (nextCol == goalCol && nextRow == goalRow) {
                onPath = false;
            }
        }
    }
}
