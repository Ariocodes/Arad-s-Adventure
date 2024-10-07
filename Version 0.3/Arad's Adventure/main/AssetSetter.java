package main;

import object.OBJ_Chest;
import object.OBJ_Door;
// import object.OBJ_Fun;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        // setting each objects locaiton on the map
        gp.obj[0] = new OBJ_Key("/res/objects/key_wooden.png");
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;
        gp.obj[0].type = "Wooden";


        gp.obj[1] = new OBJ_Key("/res/objects/key_jungle.png");
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;
        gp.obj[1].type = "Jungle";


        gp.obj[2] = new OBJ_Key("/res/objects/key_hell1.png");
        gp.obj[2].worldX = 38 * gp.tileSize;
        gp.obj[2].worldY = 8 * gp.tileSize;
        gp.obj[2].type = "Hell";


        gp.obj[3] = new OBJ_Key("/res/objects/key_golden.png");
        gp.obj[3].worldX = 23 * gp.tileSize;
        gp.obj[3].worldY = 17 * gp.tileSize;
        gp.obj[3].type = "Golden";


        gp.obj[4] = new OBJ_Door("/res/objects/door_wooden.png");
        gp.obj[4].worldX = 10 * gp.tileSize;
        gp.obj[4].worldY = 11 * gp.tileSize;
        gp.obj[4].type = "Wooden";
        

        gp.obj[5] = new OBJ_Door("/res/objects/door_jungle.png");
        gp.obj[5].worldX = 8 * gp.tileSize;
        gp.obj[5].worldY = 28 * gp.tileSize;
        gp.obj[5].type = "Jungle";


        gp.obj[6] = new OBJ_Door("/res/objects/door_hell.png");
        gp.obj[6].worldX = 12 * gp.tileSize;
        gp.obj[6].worldY = 22 * gp.tileSize;
        gp.obj[6].type = "Hell";


        gp.obj[7] = new OBJ_Chest();
        gp.obj[7].worldX = 10 * gp.tileSize;
        gp.obj[7].worldY = 7 * gp.tileSize;
        gp.obj[7].type = "Golden";

        // BRO THIS IS SO INAPPROPRIATE
        // gp.obj[7] = new OBJ_Fun();
        // gp.obj[7].worldX = 23 * gp.tileSize;
        // gp.obj[7].worldY = 35 * gp.tileSize;
        
    }
}
