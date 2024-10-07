package object;

// import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject{

    public OBJ_Key(String imageFile){
        name = "Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imageFile));
        }catch(Exception e){
            System.out.println("Could not find imageFile at: " + imageFile);
            System.out.println("image " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        
    }
}
