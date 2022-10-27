package bomberman.btl.entity.weapon;

import bomberman.btl.entity.Entity;
import bomberman.btl.main.GamePanel;

public class Projectile extends Entity {
    public Entity user;
    //Thoi gian cho no
    public int lifeTime;
    public boolean alive;
    public Projectile(GamePanel gamePanel, int x, int y, Entity user) {
        super(gamePanel);
        alive = true;
        lifeTime = 0;
        alive = true;
        direction = "down";
        this.worldX = x;
        this.worldY = y;
        this.user = user;
    }

    public void set(int x, int y, Entity user) {
        this.worldX = x;
        this.worldY = y;
        this.user = user;
    }

    @Override
    public void setAction() {
        lifeTime++;
        if (lifeTime >= 60) {
            alive = false;
            lifeTime = 0;
        }
    }

    @Override
    public void setMove() {
        collisionOn = false;
        gamePanel.collisionChecker.checkTileEntity(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkPlayer(this);
    }

    @Override
    public void update() {
        setAction();

        setMove();

        spriteCounter++;
        if (spriteCounter > 15) {
            if (spriteNum == 1) spriteNum = 2;
            else if (spriteNum == 2) spriteNum = 3;
            else if (spriteNum == 3) spriteNum = 1;
            spriteCounter = 0;
        }
    }
}
