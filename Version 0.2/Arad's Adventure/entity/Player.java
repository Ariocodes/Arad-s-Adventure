package entity;

// import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;

    // position on the screen
    public final int screenX;
    public final int screenY;
    int idleCount = 0;
    
    int hasKey = 0;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = (gp.screenWidth / 2) - (gp.tileSize / 2);
        screenY = (gp.screenHeight / 2) - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 32;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 32;
        solidArea.height = 16;



        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 22;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            // Idle animation !!! NOT DONE !!!
            idleLeft = ImageIO.read(getClass().getResourceAsStream("/res/player/idleLeft.png"));
            idleRight = ImageIO.read(getClass().getResourceAsStream("/res/player/idleRight.png"));
            idleUp = ImageIO.read(getClass().getResourceAsStream("/res/player/idleUp.png"));
            idleDown = ImageIO.read(getClass().getResourceAsStream("/res/player/idleDown.png"));

            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/right2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void update(){
        
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){

            if(keyH.upPressed == true){
                direction = "up";
                idleCount = 0;
            }
            if(keyH.downPressed == true){
                direction = "down";
                idleCount = 0;
            }
            if(keyH.leftPressed == true){
                direction = "left";
                idleCount = 0;
            }
            if(keyH.rightPressed == true){
                direction = "right";
                idleCount = 0;
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this, keyH);
            
            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true, keyH);
            pickUpObject(objIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(keyH.upPressed == true && !this.collisionOnTop){
                worldY -= speed;
            }
            if(keyH.downPressed == true && !this.collisionOnBottom){
                worldY += speed;
            }
            if(keyH.leftPressed == true && !this.collisionOnLeft){
                worldX -= speed;
            }
            if(keyH.rightPressed == true && !this.collisionOnRight){
                worldX += speed;
            }

            // Player animation
            if(spriteNum == 0){
                spriteNum = 1;
            }
            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 0){
                    spriteNum = 2;
                }
                if (spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            

        }
        else if(!keyH.rightPressed && !keyH.leftPressed && !keyH.downPressed && !keyH.upPressed){
            idleCount++;
            spriteNum = 0;
        }
        if(idleCount > 20){
            // idle animation
        }

    }


    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.obj[i].name;
            switch(objectName){
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("Key: " + hasKey);
                    break;
                case "Door":
                    if(hasKey > 0){
                        gp.obj[i] = null;
                        System.out.println("Key: " + hasKey);
                    }
                    if(hasKey == 0){
                        System.out.println("You don't have any key.");
                    }
                    break;
            }
        }
    }

    public void draw(Graphics2D g2){
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);  // x, y, width, height

        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum == 0){
                    image = idleUp;
                }
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 0){
                    image = idleDown;
                }
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 0){
                    image = idleLeft;
                }
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 0){
                    image = idleRight;
                }
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
