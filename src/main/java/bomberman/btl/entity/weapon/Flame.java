package bomberman.btl.entity.weapon;

import bomberman.btl.entity.Entity;
import bomberman.btl.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Flame extends Projectile {

    public BufferedImage image1, image2, image3;
    public BufferedImage leftLast1, leftLast2, leftLast3;
    public BufferedImage rightLast1, rightLast2, rightLast3;
    public BufferedImage upLast1, upLast2, upLast3;
    public BufferedImage downLast1, downLast2, downLast3;
    public int defaultX, defaultY;

    //left, right, up, down
    public int[] dirX = {-1, 1, 0, 0};
    public int[] dirY = {0, 0, -1, 1};
    public int[] limit = {0, 0, 0, 0};

    public int scale = 1;

    public Flame(GamePanel gamePanel, int x, int y, Entity user, int scale) {
        super(gamePanel, x, y, user);
        defaultX = x;
        defaultY = y;
        name = "flame";
        collision = true;
        solidArea = new Rectangle(3, 6, 42, 39);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        this.scale = scale;
        gamePanel.playSoundEffect(2);
        getImage();
    }

    public void getImage() {
        image1 = setupImage("/bomb/bomb_exploded");
        image2 = setupImage("/bomb/bomb_exploded1");
        image3 = setupImage("/bomb/bomb_exploded2");
        left1 = setupImage("/bomb/explosion_horizontal");
        left2 = setupImage("/bomb/explosion_horizontal1");
        left3 = setupImage("/bomb/explosion_horizontal2");
        leftLast1 = setupImage("/bomb/explosion_horizontal_left_last");
        leftLast2 = setupImage("/bomb/explosion_horizontal_left_last1");
        leftLast3 = setupImage("/bomb/explosion_horizontal_left_last2");
        rightLast1 = setupImage("/bomb/explosion_horizontal_right_last");
        rightLast2 = setupImage("/bomb/explosion_horizontal_right_last1");
        rightLast3 = setupImage("/bomb/explosion_horizontal_right_last2");
        up1 = setupImage("/bomb/explosion_vertical");
        up2 = setupImage("/bomb/explosion_vertical1");
        up3 = setupImage("/bomb/explosion_vertical2");
        upLast1 = setupImage("/bomb/explosion_vertical_top_last");
        upLast2 = setupImage("/bomb/explosion_vertical_top_last1");
        upLast3 = setupImage("/bomb/explosion_vertical_top_last2");
        downLast1 = setupImage("/bomb/explosion_vertical_down_last");
        downLast2 = setupImage("/bomb/explosion_vertical_down_last1");
        downLast3 = setupImage("/bomb/explosion_vertical_down_last2");
    }

    public void updateEdge(int scale, int dir) {
        for (int i = 1; i <= scale; ++i) {
            int nx = worldX + i * dirX[dir] * gamePanel.tileSize;
            int ny = worldY + i * dirY[dir] * gamePanel.tileSize;
            int nrow = ny / gamePanel.tileSize;
            int ncol = nx / gamePanel.tileSize;
            if (gamePanel.tileManager.mapTile[nrow][ncol] == '#') {
                return;
            }
            limit[dir] = i;
            gamePanel.flameTile[nrow][ncol] = 1;
            this.worldX = nx;
            this.worldY = ny;

            //Attack Enemies
            int enemyInd = gamePanel.collisionChecker.checkEntity(this, gamePanel.enemies);
            gamePanel.player.damageEnemy(enemyInd);

            //Attack Player8
            boolean attackPlayer = gamePanel.collisionChecker.checkPlayer(this);
            if (attackPlayer) {
                gamePanel.player.interactEnemy(1);
            }

            //Break brick
            int brickInd = gamePanel.collisionChecker.checkEntity(this, gamePanel.interactiveTiles);
            gamePanel.player.damageTile(brickInd);

            this.worldX = defaultX;
            this.worldY = defaultY;

            if (brickInd != 999) {
                break;
            }
        }
    }

    @Override
    public void update() {
        setAction();

        setMove();

        //Attack Enemies
        int enemyInd = gamePanel.collisionChecker.checkEntity(this, gamePanel.enemies);
        gamePanel.player.damageEnemy(enemyInd);

        //Attack Player
        boolean attackPlayer = gamePanel.collisionChecker.checkPlayer(this);
        if (attackPlayer) {
            gamePanel.player.interactEnemy(1);
        }

        //Break brick
        int brickInd = gamePanel.collisionChecker.checkEntity(this, gamePanel.interactiveTiles);
        gamePanel.player.damageTile(brickInd);

        for (int i = 0; i < 4; ++i) {
            updateEdge(scale, i);
        }

        this.worldX = defaultX;
        this.worldY = defaultY;

        spriteCounter++;
        if (spriteCounter > 15) {
            if (spriteNum == 1) spriteNum = 2;
            else if (spriteNum == 2) spriteNum = 3;
            else if (spriteNum == 3) spriteNum = 1;
            spriteCounter = 0;
        }
    }

    public void drawFlameEdge(Graphics graphics2D, BufferedImage image1, BufferedImage image2, int scale, int dir) {
        for (int i = 1; i <= limit[dir]; ++i) {
            int nx = worldX + i * dirX[dir] * gamePanel.tileSize;
            int ny = worldY + i * dirY[dir] * gamePanel.tileSize;
            int nrow = ny / gamePanel.tileSize;
            int ncol = nx / gamePanel.tileSize;
            if (gamePanel.tileManager.mapTile[nrow][ncol] == '#') {
                return;
            }

            if (i != scale) {
                graphics2D.drawImage(image1, nx, ny, null);
            } else {
                graphics2D.drawImage(image2, nx, ny, null);
            }
        }
    }

    public void draw(Graphics2D graphics2D) {
        if (spriteNum == 1) {
            graphics2D.drawImage(image1, worldX, worldY, null);
            drawFlameEdge(graphics2D, left1, leftLast1, scale, 0);
            drawFlameEdge(graphics2D, left1, rightLast1, scale, 1);
            drawFlameEdge(graphics2D, up1, upLast1, scale, 2);
            drawFlameEdge(graphics2D, up1, downLast1, scale, 3);
        }
        if (spriteNum == 2) {
            graphics2D.drawImage(image2, worldX, worldY, null);
            drawFlameEdge(graphics2D, left2, leftLast2, scale, 0);
            drawFlameEdge(graphics2D, left2, rightLast2, scale, 1);
            drawFlameEdge(graphics2D, up2, upLast2, scale, 2);
            drawFlameEdge(graphics2D, up2, downLast2, scale, 3);
        }
        if (spriteNum == 3) {
            graphics2D.drawImage(image3, worldX, worldY, null);
            drawFlameEdge(graphics2D, left3, leftLast3, scale, 0);
            drawFlameEdge(graphics2D, left3, rightLast3, scale, 1);
            drawFlameEdge(graphics2D, up3, upLast3, scale, 2);
            drawFlameEdge(graphics2D, up3, downLast3, scale, 3);
        }
    }
}