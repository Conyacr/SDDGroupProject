/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.GameFigure.STATE_DONE;
import static Model.GameFigure.STATE_TRAVELING;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author atm15_000
 */
class KineticBulletLeftShot extends Bullet{

    Rectangle r1, r2;
    Image launcherImage;
    float x, y, width1 = 110, height1 = 125;
    int state = STATE_TRAVELING;
    private boolean isEnemy;
    
    public KineticBulletLeftShot(float x, float y, boolean enemy) {
        this.x = x;
        this.y = y;
        this.isEnemy = enemy;
        this.name = "Kinetic Left Shot";
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
                + "BulletTest.png");
        

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
    
    @Override
    public void render(Graphics g) {
        int width = launcherImage.getWidth(null);
        int height = launcherImage.getHeight(null);
        //g.drawImage(launcherImage, (int)x, (int)y, null);
        g.drawImage(launcherImage, (int)this.x, (int)this.y, (int)this.x + 20, (int)this.y + 20, 2, 0, 9, 11, null);        
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
            this.y -= 7;
            this.x -= 3;
        }
        //enemy shot movement
        else {
            this.y += 7;
            this.x -= 3;
        }            
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

    @Override
    public Rectangle getHitBox() {
        return this.r1;
    }

    @Override
    public Ellipse2D getHitCircle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Rectangle getRectangle() {
        return this.getHitBox();
    }

    @Override
    public void renderToolTips(Graphics g) {
        g.drawString("Kinetic based projectile bullet", (int)getX() + 25, (int)getY());
        g.drawString("Shot from the left of the vehicle", (int)getX() + 25, (int)getY() + 15);
    }

}
