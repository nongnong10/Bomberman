package bomberman.btl.entity.weapon;

import bomberman.btl.entity.Entity;
import bomberman.btl.main.GamePanel;

public class Projectile extends Entity {
    public Entity user;
    public int life = 120;
    public boolean alive = false;
    public Projectile(GamePanel gamePanel, int x, int y, Entity user) {
        super(gamePanel);
        alive = true;
        this.worldX = x;
        this.worldY = y;
        this.user = user;
    }

    public void set(int x, int y, Entity user) {
        this.worldX = x;
        this.worldY = y;
        this.user = user;
    }
}
