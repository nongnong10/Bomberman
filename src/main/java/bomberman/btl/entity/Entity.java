package bomberman.btl.entity;

import bomberman.btl.graphics.ImgCollection;
import bomberman.btl.graphics.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX;
    public int worldY;
    public int speed;

    public BufferedImage up1, up2, up3;
    public BufferedImage down1, down2, down3;
    public BufferedImage left1, left2, left3;
    public BufferedImage right1, right2, right3;

    public String direction = "down";

    public int spriteCounter = 0;
    public int spriteNum = 1;

    //Collision.
    public Rectangle solidArea;
    public boolean collisionOn = false;
}