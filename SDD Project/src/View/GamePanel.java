package View;
import Controller.Animator;
import Model.Bullet;
import Model.Background;
import Model.GameData;
import Model.GameFigure;
import Model.HUD;
import Model.KineticBulletBaseLevel;
import Model.Level;
import Model.Ship;
import Model.ShipFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {
      
    public static final int PWIDTH = 800; // size of the game panel
    public static final int PHEIGHT = 800;
    
    private Animator animator;
    private GameData gameData;
    // off screen rendering
    private Graphics graphics;
    private Image dbImage = null;
    public static Level level;
     private HUD hud;
    public ShipFactory shipMaker;
    private Image titleScreen;
    private Date now; 
    private Timer timer;

    public GamePanel(Animator animator, GameData gameData) {
        this.animator = animator;
        this.gameData = gameData;
        String imagePath = System.getProperty("user.dir");
        // separator: Windows '\', Linux '/'
        String separator = System.getProperty("file.separator");

        // put images in 'images' folder, which is on the top level of
        // the NetBeans project folder.
        // Using "Files" tab of the NetBeans explorer window, right click on
        // the project folder name, and create a folder named "image"
        // You cannot see "images" folder in 'Project' tab, though
        //launcherImage = getImage(imagePath + separator + "images" + separator
     
        titleScreen = getImage(imagePath + separator + "images" + separator
                + "Inazuma no sentōki800x1000.png");


        
        level = new Level(); 
        setBackground(Color.blue);
         
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        //ship = shipMaker.getShip(0,30,30);//new DefaultShip(400,400);
        hud = new HUD();
    }

    public void startGame(final JLabel lbl) {
       
        Thread t = new Thread(animator);
        t.start();
        
        now = new Date();
        startTimer(lbl);
    }

    public void pauseGame(){
        animator.setPause(true);
        if (timer != null) {
            timer.stop();
        }
    }
    
    public void resumeGame(){
        animator.setPause(false);
        if (timer != null) {
            timer.start();
        }
    }
    
        private void startTimer(final JLabel lbl){ 
		now.setHours(0);
		now.setMinutes(0);
		now.setSeconds(0);
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date now2 = new Date(now.getTime() + 1000);
				now = now2;
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
				lbl.setText(formatter.format(now));
			}
		});
                
                timer.start();
    }
    
    
    
            
      
    
    /*public void Tetris()
    {
       Launcher launcher;
       //Test Area V================================================================================
       int x=0; 
        x++;
         if (x==3)
         {
        launcher = (Launcher) gameData.figures.get(0); // launcher
         System.out.println("Your dumb");
         } 
       //Test Area^================================================================================ 
    }*/
    public void gameRender() {
        if (dbImage == null) {
            dbImage = createImage(getWidth(), getHeight());
            //dbImage = createImage(PWIDTH, PHEIGHT);
            if (dbImage == null) {
                System.out.println("dbImage is null");
                return;
            } else {
                graphics = dbImage.getGraphics();
            }
        }
            
        graphics.clearRect(0, 0, Level.returnWidth(), Level.returnHeight());
        //draw the background image first then draw everything else ontop of it
        level.render(graphics);
        
        synchronized (gameData.enemyShips) {
            //this part works but the iteration doesn't
//            gameData.enemyShips.get(0).render(graphics);
            
            Ship ship;
            for(int n = 0; n < gameData.enemyShips.size(); n++) {
                
                ship = gameData.enemyShips.get(n);
                ship.render(graphics);
            }
        }
        synchronized (gameData.figures) {
            GameFigure f;
            for (int i = 0; i < gameData.figures.size(); i++) {
                f = (GameFigure) gameData.figures.get(i);
                f.render(graphics);
            }
        }
        synchronized (gameData.ships) {
            Ship f;
            for (int i = 0; i < gameData.ships.size(); i++) {
                f = (Ship) gameData.ships.get(i);
                f.render(graphics);
            }
        }        
        synchronized (gameData.items) {
            GameFigure f;
            for (int i = 0; i < gameData.items.size(); i++) {
                f = (GameFigure) gameData.items.get(i);
                f.render(graphics);
            }            
        }
//        synchronized (gameData.bullets) {
//            Bullet f;
//                for (int i = 0; i < gameData.bullets.size(); i++) {
//                    f = (Bullet) gameData.bullets.get(i);
//                    f.render(graphics);
//                }
//        }
        
//        synchronized (gameData.menu)
//        {
//            Background f;
//            for (int i = 0; i < gameData.menu.size(); i++) {
//                f = (Background) gameData.menu.get(i);
//                f.render(graphics);
//            }            
//        }
//        synchronized (gameData.enemyShips) {
//            Ship f;
//            for (int i = 0; i < gameData.enemyShips.size(); i++) {
//                f = (Ship) gameData.enemyShips.get(i);
//                f.render(graphics);
//            }
//        }
         hud.render(graphics);
    }

    public void printScreen() { // use active rendering to put the buffered image on-screen
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null)) {
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();  // sync the display on some systems
            g.dispose();
        } catch (Exception e) {
            System.out.println("Graphics error: " + e);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(titleScreen, 0, 0, null); // see javadoc for more info on the parameters      
        //g.drawImage(playerSpriteSheet, (int) this.x, (int) this.y, (int) x + 25, (int) y + 40, 144, 16, 169, 52, null, null);
    }

    public static Image getImage(String fileName) {
        Image image = null;
        try {
            image = ImageIO.read(new File(fileName));
        } catch (Exception ioe) {
            System.out.println("Error: Cannot open image:" + fileName);
            JOptionPane.showMessageDialog(null, "Error: Cannot open image:" + fileName);
        }
        return image;
    }
    
    public void setGameSize(int height, int width) {
        //called and sets the size of the game so the panel knows which image to go and use
        
    }
    
   
    
}
