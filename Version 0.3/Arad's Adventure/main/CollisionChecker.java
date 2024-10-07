package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    boolean debugging = true;


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
                // entity.collisionOn = true;
                entity.collisionOnTop = true;
            }
        }


        if(keyH.downPressed){
            entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                // entity.collisionOn = true;
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
                // entity.collisionOn = true;
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
                // entity.collisionOn = true;
                entity.collisionOnRight = true;
            }
        }

        if(debugging){
            if(entity.collisionOnBottom || entity.collisionOnTop || entity.collisionOnLeft || entity.collisionOnRight){
                System.out.println("UP: " + entity.collisionOnTop + "  BOTTOM: " + entity.collisionOnBottom + "  LEFT: " + entity.collisionOnLeft + "  RIGHT: " + entity.collisionOnRight);
            }
        }





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

    public int checkObject(Entity entity, boolean player, KeyHandler keyH){
        
        int index = 999;
        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){
                
                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                if(keyH.upPressed){
                    entity.solidArea.y -= entity.speed;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision == true){
                            if(debugging){
                                System.out.println("UP");
                            }
                            entity.collisionOnTop = true;
                        }
                        if(player == true){
                            index = i;
                        }
                    }
                    entity.solidArea.y += entity.speed;
                }

                if(keyH.downPressed){
                    entity.solidArea.y += entity.speed;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision == true){
                            if(debugging){
                                System.out.println("DOWN");
                            }
                            entity.collisionOnBottom = true;
                        }
                        if(player == true){
                            index = i;
                        }
                    }
                    entity.solidArea.y -= entity.speed;
                }

                if(keyH.leftPressed){
                    entity.solidArea.x -= entity.speed;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision == true){
                            if(debugging){
                                System.out.println("LEFT");
                            }
                            entity.collisionOnLeft = true;
                        }
                        if(player == true){
                            index = i;
                        }
                    }
                    entity.solidArea.x += entity.speed;
                }

                if(keyH.rightPressed){
                    entity.solidArea.x += entity.speed;
                    if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if(gp.obj[i].collision == true){
                            if(debugging){
                                System.out.println("RIGHT");
                            }
                            entity.collisionOnRight = true;
                        }
                        if(player == true){
                            index = i;
                        }
                    }
                    entity.solidArea.x -= entity.speed;
                }

                // switch(entity.direction){
                //     case "up":
                //         entity.solidArea.y -= entity.speed;
                //         if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                //             if(gp.obj[i].collision == true){
                //                 System.out.println("UP");
                //                 entity.collisionOnTop = true;
                //             }
                //             if(player == true){
                //                 index = i;
                //             }
                //         }
                //         break;
                //     case "down":
                //         entity.solidArea.y += entity.speed;
                //         if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                //             if(gp.obj[i].collision == true){
                //                 System.out.println("DOWN");
                //                 entity.collisionOnBottom = true;
                //             }
                //             if(player == true){
                //                 index = i;
                //             }
                //         }
                //         break;
                //     case "left":
                //         entity.solidArea.x -= entity.speed;
                //         if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                //             if(gp.obj[i].collision == true){
                //                 System.out.println("LEFT");
                //                 entity.collisionOnLeft = true;
                //             }
                //             if(player == true){
                //                 index = i;
                //             }
                //         }
                //         break;
                //     case "right":
                //         entity.solidArea.x += entity.speed;
                //         if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                //             if(gp.obj[i].collision == true){
                //                 System.out.println("RIGHT");
                //                 entity.collisionOnRight = true;
                //             }
                //             if(player == true){
                //                 index = i;
                //             }
                //         }
                //         break;
                // }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }



}
