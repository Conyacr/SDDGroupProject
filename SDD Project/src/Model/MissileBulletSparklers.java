/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Main;
import static Model.GameFigure.STATE_DONE;
import static Model.GameFigure.STATE_INIT_LEFT;
import static Model.GameFigure.STATE_INIT_RIGHT;
import static Model.GameFigure.STATE_TRAVELING;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.util.Timer;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author atm15_000
 */
public class MissileBulletSparklers extends Bullet{
    Rectangle r1, r2;
    Image launcherImage;
    float x, y, width1 = 110, height1 = 125, pos, dist, targetx, targety, speed, sep;
    int state = STATE_TRAVELING;
    private boolean isEnemy;    
    double angleToTarget;    
    Timer timer = new Timer();
    int timeElapsed = 110;
    private float offset;
    
    
    public MissileBulletSparklers(float x, float y, boolean enemy, int num) {        
        this.pos = this.x = x;
        this.y = y;
        this.isEnemy = enemy;
        this.name = "Missile Base Level";        
        this.dist = 2;                
        this.offset = this.x;        
                
       if (num == 1)
        state = STATE_INIT_LEFT;
       else
        state = STATE_INIT_RIGHT;                
        
        String imagePath = System.getProperty("user.dir");
        // separator: Windows '\', Linux '/'
        String separator = System.getProperty("file.separator");
        // put images in 'images' folder, which is on the top level of
        // the NetBeans project folder.
        // Using "Files" tab of the NetBeans explorer window, right click on
        // the project folder name, and create a folder named "image"
        // You cannot see "images" folder in 'Project' tab, though
        //launcherImage = getImage(imagePath + separator + "images" + separator
        launcherImage = getImage(imagePath + separator + "images" + separator
                + "redMissile.png");
        

        //setRectangle(); // initialize the hit box when object is created for testing   

       setLauncherHitBox();       
    }
    
    public Image getImage(String fileName) {
        Image image = null;
        try {
            image = ImageIO.read(new File(fileName));
        } catch (Exception ioe) {
            System.out.println("Error: Cannot open image:" + fileName);
            JOptionPane.showMessageDialog(null, "Error: Cannot open image:" + fileName);
        }
        return image;
    }
    
    private void setLauncherHitBox() {
        //this.r1 = new Rectangle((int) this.x + 5, (int) this.y + 10, (int) this.width1, (int) this.height1);        
        this.r1 = new Rectangle((int) this.x, (int) this.y, 10, 10);  
    }
    
    public Rectangle getHitBox(){
        return this.r1;
    }
    
    @Override
    public Ellipse2D getHitCircle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void render(Graphics g) {
        int width = launcherImage.getWidth(null);
        int height = launcherImage.getHeight(null);
        //g.drawImage(launcherImage, (int)x, (int)y, null);
        //g.drawImage(launcherImage, (int)this.x, (int)this.y, (int)this.x + 20, (int)this.y + 20, 2, 18, 9, 29, null);        
        g.drawImage(launcherImage, (int)this.x, (int)this.y, (int)this.x + 20, (int)this.y + 20, 21, 5, 38, 22, null);
        //----------------------------------------------------------------------
        //set up and display hit boxes for the launcher objects
        //used for dubugging 9/10/2015
        //----------------------------------------------------------------------
        g.setColor(Color.yellow);
        //g.drawRect((int) this.x + 5, (int) this.y + 10, (int) this.width1, (int) this.height1);
        g.drawRect((int) this.x, (int) this.y, 10, 10);
        g.setColor(Color.BLUE);
        setLauncherHitBox();        
        g.setColor(Color.BLUE);     
        //----------------------------------------------------------------------
    }

    @Override
    public void update() {
        setLauncherHitBox();           
        //friendly shot movement
        if (isEnemy == false) {            
            
            if (state == STATE_INIT_LEFT) {
                this.x-=3;
                if (this.x < offset - 30)
                    state = STATE_TRAVELING;
            } else if (state == STATE_INIT_RIGHT) {
                this.x += 3;
                if (this.x > offset + 30) {
                    state = STATE_TRAVELING;
                }
            } else if (state == STATE_TRAVELING) {                
                this.y -= dist;                                
                
                if (dist > 40) {
                    if (dist % 2 == 0)
                        //timer.schedule(new SpawnShot(5, (int)x, (int)y), timeElapsed);
                        Main.gameData.friendlyBullets.add((Bullet)new MissileSparkleDust(x, y + 80, false));
                    //Main.gameData.friendlyBullets.add((Bullet)new MissileSparkleDust(x, y + 15, false));                            
                    //Main.gameData.friendlyBullets.add((Bullet)new MissileSparkleDust(x, y + 15, false));                            
                }
                
                if (dist < 60)
                dist += 10;
            }                
        }                    
        //enemy shot movement
        else            
            this.y += 4;
        //if (this.x < 1){
        //    System.out.println("bullet = " + this.x);
        //    this.state = STATE_DONE;
        //}
    }

    @Override
    public int getState() {
        return state;
        
    }

    @Override
    void setState() {
        state = STATE_DONE;
    }

    @Override
    float getX() {
        return this.x;
    }
    
    @Override
    float getY() {
        return this.y;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Rectangle getRectangle() {
        return this.getHitBox();
    }

    @Override
    public void renderToolTips(Graphics g) {
        g.drawString("Sparklers", (int)getX() + 25, (int)getY());
        g.drawString("Leaves deadly shrapenel in its wake", (int)getX() + 25, (int)getY() + 15);

    }
}