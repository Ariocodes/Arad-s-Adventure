package main;

import java.awt.Dimension;
import java.awt.Graphics;   // A class that has many functions to draw object on the screen. (like a pencil)
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;
import entity.Ario;

public class GamePanel extends JPanel implements Runnable{ // the class "Runnable" is the key to use "Thread".
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16; // 16 tiles horizontally
    public final int maxScreenRow = 12; // 12 tiles vertically
    
    // screen size
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 756 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    BufferedImage backgroundImage;
    

    // FPS
    int FPS = 60;

    // TileManager
    TileManager tileM = new TileManager(this);


    // the keyHandler class object
    KeyHandler keyH = new KeyHandler();


    Thread gameThread;  // when you want to repeat some process again and again such
                        // as drawing screen 60 times every one second, thread is very useful.

    public CollisionChecker cChecker = new CollisionChecker(this);
    

    public Player player = new Player(this, keyH);
    Ario ario = new Ario(this);

    
    public GamePanel(){
        try{
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/res/background/bg01.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // better rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true); // With this, this GamePanel can be "focused" to receive key input.
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        // THE GAMELOOP

        // Using "Delta/Accumulator" method
        double drawInterval = 1000000000/FPS; // one second/FPS
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;


        while(gameThread != null){  // as long as the gameThread exists
            // Things that need to be done:
            // 1 UPDATE : update information such as character positions (The "update" method)
            // 2 DRAW : draw the screen with the updated information (The "paintComponent" method)

            // Delta/Accumulator:
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            // adds up the time passed in terms of drawInterval. Which means by the time "delta"
            // reaches the value of 1, it will have finished the drawInterval.
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();  // to call the "paintComponent" method (Dude wtf is all this shit).
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }




    }


    public void update(){
        ario.update();
        player.update();
    }


    public void paintComponent(Graphics g){   // this is one of the standard methods to draw things on JPanel
        super.paintComponent(g); // calling the "paintComponent" method of JPanel
        
        Graphics2D g2 = (Graphics2D)g; // downcasing or upcasting whatthefuckerver we're doing here. (downcasting I guess)
        // Graphics2D class extends the Graphics class to provide MORE SOPHISTICATED CONTROL over
        // geometry, coordinate transformations, color management, and text layout.
        
        // g2.drawImage(backgroundImage, 0, 0, tileSize * maxScreenCol, tileSize * maxScreenRow, null);
        tileM.draw(g2);
        ario.draw(g2);
        player.draw(g2);
        g2.dispose();   // Dispose of this graphics context and release anny system resources that it is using.
    }
}
