package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Ario extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;
    
    int x;
    int y;

    int screenX;
    int screenY;
    int duration = 0;

    public Ario(GamePanel gp){
        this.gp = gp;
        setDefaultValues();
        getArioImage();
    }
    public void setDefaultValues(){
        worldX = 420;
        worldY = 290;
        
        x = worldX;
        y = worldY;


        speed = 40;
        direction = "right";
    }
    public void getArioImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/Ario/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/Ario/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/Ario/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/Ario/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/Ario/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/Ario/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/Ario/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/Ario/right2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void update(){
        screenX = worldX - gp.player.worldX + gp.player.screenX;
        screenY = worldY - gp.player.worldY + gp.player.screenY;

        spriteCounter++;
        if(spriteCounter > 12){
            if (spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        duration++;
        if(duration > 4){
            if(direction.equals("right")){
                direction = "down";
            }
            else if(direction.equals("down")){
                direction = "left";
            }
            else if(direction.equals("left")){
                direction = "up";
            }
            else if(direction.equals("up")){
                direction = "right";
            }
            duration = 0;
        }
        if(direction.equals("up")){
            direction = "up";
            worldY -= speed;
        }
        if(direction.equals("down")){
            direction = "down";
            worldY += speed;
        }
        if(direction.equals("left")){
            direction = "left";
            worldX -= speed;
        }
        if(direction.equals("right")){
            direction = "right";
            worldX += speed;
        }
    }


    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        // System.out.println("X: " + screenX + "  Y: " + screenY);
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}
