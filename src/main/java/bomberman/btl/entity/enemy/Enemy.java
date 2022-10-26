package bomberman.btl.entity.enemy;

import bomberman.btl.entity.Entity;
import bomberman.btl.main.GamePanel;

public class Enemy extends Entity {
    public Enemy(GamePanel gamePanel){
        super(gamePanel);
        this.name = "enemy";
    }
}
