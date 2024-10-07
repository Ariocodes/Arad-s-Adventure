package object;

// import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject{
    
    public OBJ_Door(String imageFile){
        
        name = "Door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imageFile));
        }catch(Exception e){
            System.out.println("Could not find imageFile at: " + imageFile);
            System.out.println("image " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        collision = true;
    }
}
