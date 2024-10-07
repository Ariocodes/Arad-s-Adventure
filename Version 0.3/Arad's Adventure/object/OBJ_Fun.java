package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Fun extends SuperObject{
    public OBJ_Fun(){
        name = "Fun";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/dick.png"));
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
