package entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entity {
    // position on the world
    public int worldX, worldY;
    
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, idleLeft, idleRight, idleUp, idleDown;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;

    public boolean collisionOnTop = false;
    public boolean collisionOnBottom = false;
    public boolean collisionOnLeft = false;
    public boolean collisionOnRight = false;
}
