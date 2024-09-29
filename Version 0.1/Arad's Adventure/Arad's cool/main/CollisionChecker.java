package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    
    public void checkTile(Entity entity, KeyHandler keyH){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        entity.collisionOnTop = false;
        entity.collisionOnBottom = false;
        entity.collisionOnLeft = false;
        entity.collisionOnRight = false;



        if(keyH.upPressed){
            entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
                entity.collisionOnTop = true;
            }
        }


        if(keyH.downPressed){
            entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
                entity.collisionOnBottom = true;
            }
        }




        /*
        A single pixel on top/bottom of the collision rectangle collides with a 
        top/bottom tile if bottom or top collision are true and the player is 
        moving towards them. This prevents player from moving left/right when
        a bottom/top collision is true. To avoid this, we check if, for 
        instance, the bottom collision is ture. If it is, we will consider the
        collision at one tile above it, so the single pixel is not colliding
        anymore.

        if the collision is at the top while trying to move right/left, it must
        add 1 to entity's top row so it's considered as the tile beneath it
         */
        if(keyH.leftPressed){
            entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
            // Cheking for top/bottom collision to change the tile by one
            if(entity.collisionOnTop){
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow + 1];
            }
            else{
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            }
            if(entity.collisionOnBottom){
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow - 1];
            }
            else{
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            }


            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
                entity.collisionOnLeft = true;
            }
        }


        if(keyH.rightPressed){
            entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
            if(entity.collisionOnTop){
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow + 1];
            }
            else{
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            }
            if(entity.collisionOnBottom){
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow - 1];
            }
            else{
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            }


            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
                entity.collisionOnRight = true;
            }
        }
        
        // System.out.println("UP: " + entity.collisionOnTop + "  BOTTOM: " + entity.collisionOnBottom + "  LEFT: " + entity.collisionOnLeft + "  RIGHT: " + entity.collisionOnRight);





        // switch(entity.direction){
        //     case "up":
        //         entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
        //         tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
        //         tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
        //         if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
        //             entity.collisionOn = true;
        //             entity.collisionOnTop = true;
        //         }
        //         break;
        //     case "down":
        //         entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
        //         tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
        //         tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
        //         if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
        //             entity.collisionOn = true;
        //             entity.collisionOnBottom = true;
        //         }
        //         break;
        //     case "left":
        //         entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
        //         tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
        //         tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
        //         if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
        //             entity.collisionOn = true;
        //             entity.collisionOnLeft = true;
        //         }
        //         break;
        //     case "right":
        //         entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
        //         tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
        //         tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
        //         if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
        //             entity.collisionOn = true;
        //             entity.collisionOnRight = true;
        //         }
        //         break;
        // }
    }
}
