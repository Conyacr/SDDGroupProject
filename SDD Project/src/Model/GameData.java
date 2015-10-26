package Model;
import Controller.Main;
import Controller.EnemyFlyWeightFactory;
import Controller.EnemyFlyweight;
import View.MainMenu;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.scene.shape.Circle;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class GameData {

// USE Strategy pattern    
    static final int STATE_EXPLOSIOIN_1 = 1;
    static final int STATE_EXPLOSIOIN_2 = 2;
    static final int STATE_EXPLOSIOIN_3 = 3;
    static final int STATE_EXPLOSIOIN_4 = 4;
    static final int STATE_EXPLOSIOIN_5 = 5;
    static final int STATE_EXPLOSIOIN_6 = 6;
    static final int STATE_EXPLOSIOIN_7 = 7;
    static final int STATE_EXPLOSIOIN_8 = 8;
    static final int STATE_EXPLOSIOIN_9 = 9;
    static final int STATE_EXPLOSIOIN_10 = 10;
    static final int STATE_EXPLOSIOIN_11 = 11;
    static final int STATE_EXPLOSIOIN_12 = 12;
    static final int STATE_EXPLOSIOIN_13 = 13;
    static final int STATE_EXPLOSIOIN_14 = 14;
    static final int STATE_EXPLOSIOIN_15 = 15;
    static final int STATE_EXPLOSIOIN_16 = 16;
    private final String[] shipTypes = {"defaultship", "shipx", "shipy", "shipz", "shipv", "shipw"};
    private final static int RESOLUTION_800X1000 = 1;
    public String shipName = "error";
    

    public List<GameFigure> figures;
    public List<Item> items;
    public List<Ship> ships, enemyShips;
    public List<Background> menu;
    public List<Bullet> bullets;
    private final ShipFactory shipMaker = new ShipFactory();
    private EnemyFactory enemyMaker = new EnemyFactory();
    private WeaponPowerFactory weaponMaker = new WeaponPowerFactory();
    private Ship incomingShip;
    private int BASE_LEVEL = -1, counter = 0, spawnTicker, difficulty = 500;
    private EnemyFlyWeightFactory flyweightFactory;
    public static EnemyFlyweight flyweightItems;
    private ShipSelectMenu shipSelectionMenu;  
    private Random rand;
    
    public boolean shockOn;
    
    public GameData(String sName) {
        
        this.shipName = sName;
        menu = Collections.synchronizedList(new ArrayList<Background>());

        figures = Collections.synchronizedList(new ArrayList<GameFigure>());
        ships = Collections.synchronizedList(new ArrayList<Ship>());
        items = Collections.synchronizedList(new ArrayList<Item>());
        enemyShips = Collections.synchronizedList(new ArrayList<Ship>());
        flyweightFactory = new EnemyFlyWeightFactory();
        flyweightItems = flyweightFactory.getFlyweight();
        bullets = Collections.synchronizedList(new ArrayList<Bullet>());

        //create ships for collision test
        //9/10/2015
//-----------------------------------------------------------------------------        
        //incomingShip = shipMaker.getShip("defaultShip",300,350);
        // ships.add(incomingShip);      

        //menu.add((Background) new ShipSelectMenu(0));
        
        //String shipT = shipTypes[((int)randomize((float)0,5))];  
        
        ships.add((Ship)shipMaker.getShip(shipName,450,450));//-----------------------------

        menu.add((Background) new ShipSelectMenu(0));

        //ships.add((Ship) shipMaker.getShip("defaultship", 450, 450));

        //represent weapon power-up items
        //figures.add(new Launcher(100, 200));    
        // testing items and new item mechanics
        items.add((Item) weaponMaker.getWeapon("KINETIC", 250, 200));
        items.add((Item) weaponMaker.getWeapon("KINETIC", 250, 180));
        items.add((Item) weaponMaker.getWeapon("KINETIC", 250, 1600));
        items.add((Item) weaponMaker.getWeapon("KINETIC", 250, 220));
        items.add((Item) weaponMaker.getWeapon("KINETIC", 250, 240));
        items.add((Item) weaponMaker.getWeapon("LASER", 400, 200));
<<<<<<< HEAD
        items.add((Item) weaponMaker.getWeapon("MISSILE", 100, 200));

//         enemyShips.add((Ship)enemyMaker.getEnemyShip("defaultship", 200, 200));
        //represent weapon power-up items
        //figures.add(new Launcher(100, 200));    
        /*figures.add(new Launcher(250, 200));
         figures.add(new Launcher(400, 200));
         figures.add(new Launcher(100, 200));  */
        //figures.add((GameFigure) enemyMaker.getEnemyShip("defaultship", 20, 20));
        Ship[] enemyFormation = enemyMaker.getEnemyShipFormation("defaultship", 200, -250);
        for (int i = 0; i < enemyFormation.length; i++) {
            enemyShips.add(enemyFormation[i]);
        }
//-----------------------------------------------------------------------------
//----------------------------------------------------------------------

      //System.out.println("@@GAME DATA CONSTRUCTO@@");
    }

/*
    public GameData() {

        menu = Collections.synchronizedList(new ArrayList<Background>());

        figures = Collections.synchronizedList(new ArrayList<GameFigure>());
        ships = Collections.synchronizedList(new ArrayList<Ship>());
        items = Collections.synchronizedList(new ArrayList<Item>());
        enemyShips = Collections.synchronizedList(new ArrayList<Ship>());
        flyweightFactory = new EnemyFlyWeightFactory();
        flyweightItems = flyweightFactory.getFlyweight();
        bullets = Collections.synchronizedList(new ArrayList<Bullet>());

        //create ships for collision test
        //9/10/2015
//-----------------------------------------------------------------------------        
        //incomingShip = shipMaker.getShip("defaultShip",300,350);
        // ships.add(incomingShip);      

        //menu.add((Background) new ShipSelectMenu(0));
        
        //String shipT = shipTypes[((int)randomize((float)0,5))];  
        
        ships.add((Ship)shipMaker.getShip(shipName,450,450));//-----------------------------

        menu.add((Background) new ShipSelectMenu(0));

        ships.add((Ship) shipMaker.getShip("defaultship", 450, 450));

        //represent weapon power-up items
        //figures.add(new Launcher(100, 200));    
        // testing items and new item mechanics
        items.add((Item) weaponMaker.getWeapon("KINETIC", 250, 200));
        items.add((Item) weaponMaker.getWeapon("KINETIC", 250, 180));
        items.add((Item) weaponMaker.getWeapon("KINETIC", 250, 1600));
        items.add((Item) weaponMaker.getWeapon("KINETIC", 250, 220));
        items.add((Item) weaponMaker.getWeapon("KINETIC", 250, 240));
=======
        items.add((Item) weaponMaker.getWeapon("LASER", 400, 200));
        items.add((Item) weaponMaker.getWeapon("LASER", 400, 200));
        items.add((Item) weaponMaker.getWeapon("LASER", 400, 200));
        items.add((Item) weaponMaker.getWeapon("LASER", 400, 200));
>>>>>>> origin/master
        items.add((Item) weaponMaker.getWeapon("LASER", 400, 200));
        items.add((Item) weaponMaker.getWeapon("MISSILE", 100, 200));

//         enemyShips.add((Ship)enemyMaker.getEnemyShip("defaultship", 200, 200));
        //represent weapon power-up items
        //figures.add(new Launcher(100, 200));    
        /*figures.add(new Launcher(250, 200));
         figures.add(new Launcher(400, 200));
         figures.add(new Launcher(100, 200));  
        //figures.add((GameFigure) enemyMaker.getEnemyShip("defaultship", 20, 20));
        Ship[] enemyFormation = enemyMaker.getEnemyShipFormation("defaultship", 200, -250);
        for (int i = 0; i < enemyFormation.length; i++) {
            enemyShips.add(enemyFormation[i]);
        }
<<<<<<< HEAD
//-----------------------------------------------------------------------------
//----------------------------------------------------------------------

      //System.out.println("@@GAME DATA CONSTRUCTO@@");
    }*/

=======
        rand = new Random();
        spawnTicker = rand.nextInt(100);
        this.shockOn = false;
    }
    
>>>>>>> origin/master
    private float randomize(float in, int offset) {
        float min = in, max = in + offset;
        Random rand = new Random();
        float number = rand.nextFloat() * (max - min) + min;

        return number;
    }

    public void update() {

        mainGame();
    }

    public void spawnEnemiesForTest() {
        enemyShips.add(enemyMaker.getEnemyShip("defaultship", 20, 20));
        Ship[] enemyFormation = enemyMaker.getEnemyShipFormation("defaultship", 200, -250);
        for (int i = 0; i < enemyFormation.length; i++) {
            synchronized (enemyShips) {
                enemyShips.add(enemyFormation[i]);
            }

        }
    }
    
    void selectShip() {
//        shipSelectionMenu = (ShipSelectMenu) this.menu.get(0);
//       
//       while (shipSelectionMenu.hasPlayerSelectedAShip() == false)
//       {
//        
//       }
//       
//       
//       
//       List<Background> removeBackground = new ArrayList<Background>();
//        Background back;        
//       
//        synchronized (menu) {                                                     
//            
//            for (int i = 0; i < menu.size(); i++) {               
//                back = menu.get(i);
//                back.update();
//                if (back.getState() == Item.STATE_DONE)
//                    removeBackground.add(back);
//            }
//            menu.removeAll(removeBackground);
//          }
//         menu.add((Background) shipSelectionMenu);
    }

    void mainGame() {
//-----------------------------------------------------------------------------
// a little collision test for the playable ship and another instance of the ship
// when the ship collides the weapon level state will increment
// the player ship's weapon level state 9/15/2015
// uses the figures arraylist until an item class is created                //old
        
//the figures list only holds objects for the tutorial, collision is not needed now
//                              10/14/15
//-----------------------------------------------------------------------------                           
        Ship currentShip = (Ship) this.ships.get(0);
        if (currentShip.getWeaponState() == 1 && currentShip.getLevelState() > 1 && this.shockOn == false) {
            this.shockOn = true;
            Main.gameData.bullets.add(new LightningShot(currentShip.getX() - 60, currentShip.getY() - 60));
        }
        
            
        //set to 4 for the time being, make a new arraylist for the enemies               
        //try {
            for (int i = 0; i < this.figures.size(); i++) {                
                Rectangle hit = currentShip.getShipHitBox();                
                if (this.figures.get(i) instanceof Ship) {
                    Ship asdf = (Ship) this.figures.get(i);
                    if (hit.intersects(asdf.getShipHitBox())) {
                        items.add((Item) weaponMaker.getWeapon("KINETIC", randomize(asdf.getXofMissileShoot(), 100), randomize(asdf.getYofMissileShoot(), 100)));
                        items.add((Item) weaponMaker.getWeapon("KINETIC", randomize(asdf.getXofMissileShoot(), 100), randomize(asdf.getYofMissileShoot(), 100)));
                        items.add((Item) weaponMaker.getWeapon("KINETIC", randomize(asdf.getXofMissileShoot(), 100), randomize(asdf.getYofMissileShoot(), 100)));
                        items.add((Item) weaponMaker.getWeapon("KINETIC", randomize(asdf.getXofMissileShoot(), 100), randomize(asdf.getYofMissileShoot(), 100)));
                        items.add((Item) weaponMaker.getWeapon("KINETIC", randomize(asdf.getXofMissileShoot(), 100), randomize(asdf.getYofMissileShoot(), 100)));
                        items.add((Item) weaponMaker.getWeapon("KINETIC", randomize(asdf.getXofMissileShoot(), 100), randomize(asdf.getYofMissileShoot(), 100)));
                        items.add((Item) weaponMaker.getWeapon("KINETIC", randomize(asdf.getXofMissileShoot(), 100), randomize(asdf.getYofMissileShoot(), 100)));
                        items.add((Item) weaponMaker.getWeapon("KINETIC", randomize(asdf.getXofMissileShoot(), 100), randomize(asdf.getYofMissileShoot(), 100)));
                        items.add((Item) weaponMaker.getWeapon("KINETIC", randomize(asdf.getXofMissileShoot(), 100), randomize(asdf.getYofMissileShoot(), 100)));
                        /*START EXPLOSION TEST*/
                        currentShip.setState(4);
//=======================/*END EXPLOSION TEST*/                        
                        //   Main.controller = new KeyController(currentShip);
                        System.out.println("Ship state is " + currentShip.getState());                    
                        //ship.setShipState(STATE_DONE);
                        synchronized (figures) {
                            this.figures.remove(asdf);
                        }
                        //currentShip.setLevelState(currentShip.getLevelState());
                    }
                }
            }
//-----------------------------------------------------------------------------                           
            // checks for collision between items and the ship. If collision is 
        // detected then it either changes the ships weapon in increments the 
        // weapon level 9/23/15
        for (int i = 0; i < this.items.size(); i++) {
            //Rectangle[] hit = currentShip.getShipHitBox();
            Rectangle hit = currentShip.getShipHitBox();
            //System.out.println(this.items.size());
            Item item = (Item) this.items.get(i);

            if (hit.intersects(item.getRectangle1())) {
                int itemReference = item.getItemType();
                int shipWeaponReference = currentShip.getWeaponState();
                if (itemReference == shipWeaponReference) {
                    currentShip.setLevelState(currentShip.getLevelState());
                } else if (itemReference >= 0 && itemReference <= 2) {
                    currentShip.setWeaponState(itemReference);
                    currentShip.setLevelState(BASE_LEVEL);
                }
                synchronized (items) {
                    this.items.remove(item);
                }
            }
        }
//-----------------------------------------------------------------------------  
        
//-----------------------------------------------------------------------------                           
        // player ship bullets to enemy ship collision 10/14/15
        try {
            for (int i = 0; i < this.bullets.size(); i++) {
                Bullet shot = (Bullet) bullets.get(i);
                for (int j = 0; j < this.enemyShips.size(); j++) {
                    EnemyShip eShip = (EnemyShip) enemyShips.get(j);
                    if (shot.name != "Lightning Shot") {                        
                        if (shot.getHitBox().intersects(eShip.getShipHitBox())) {
                            eShip.getHit();
                            synchronized (bullets) {
                                this.bullets.remove(shot);
                            }
                            break;
                        }
                        if (shot.getY() < -15) {
                        System.out.println("bullet = " + shot.getY());
                        synchronized (bullets) {
                            this.bullets.remove(shot);
                        }
                        break;
                    } 
                    }
                    else if (shot.name == "Lightning Shot") {
                        if (shot.getHitCircle().intersects(eShip.getShipHitBox())) {
                            this.shockOn = false;
                            Rectangle we = eShip.getShipHitBox();
                            Ellipse2D qw = shot.getHitCircle();
                            eShip.getHit();                               
                            synchronized (bullets) {
                                this.bullets.remove(shot);
                            }
                            break;
                        }                        
                    }                     
                }
            }
        } catch (Exception e) {
            System.out.println("Gamedata");
        }
//-----------------------------------------------------------------------------                           
            //collision for enemies
            for (int i = 0; i < this.enemyShips.size(); i++) {
                EnemyShip eShip = (EnemyShip) enemyShips.get(i);
                               
                if (currentShip.getShipHitBox().intersects(eShip.getShipHitBox())) {
                    eShip.getHit();
                }

                if (eShip.getShipState() == 0) {
                    synchronized (enemyShips) {
                        this.enemyShips.remove(eShip);
                    }
                }
            }
            
        List<GameFigure> removeGameFigures = new ArrayList<GameFigure>();
        GameFigure f;

        synchronized (figures) {

            for (int i = 0; i < figures.size(); i++) {
                f = figures.get(i);
                f.update();
                if (f.getState() == GameFigure.STATE_DONE) {
                    removeGameFigures.add(f);
                }
            }
            figures.removeAll(removeGameFigures);
        }

        //careful of deleting something and causing an out of bounds error
        List<Ship> removeShips = new ArrayList<Ship>();
        Ship s;

        synchronized (ships) {

            for (int i = 0; i < ships.size(); i++) {
                s = ships.get(i);
                s.update();
//                if (s.getState() == Ship.STATE_FINISHED) {
//                    removeShips.add(s);
//                }
            }
            ships.removeAll(removeShips);
        }

        // added new items list to find and remove objects 9/23/15
        List<Item> removeItems = new ArrayList<Item>();
        Item it;

        synchronized (items) {

            for (int i = 0; i < items.size(); i++) {
                it = items.get(i);
                it.update();
                if (it.getState() == Item.STATE_DONE) {
                    removeItems.add(it);
                }
            }
            items.removeAll(removeItems);
        }
        
        // added new bullets list to find and remove bullets objects 10/14/15
        List<Bullet> removeBullets = new ArrayList<Bullet>();
        Bullet bt;

        synchronized (bullets) {

            for (int i = 0; i < bullets.size(); i++) {
                bt = bullets.get(i);
                bt.update();
                if (bt.getState() == Bullet.STATE_DONE) {
                    removeBullets.add(bt);
                }
            }
            bullets.removeAll(removeBullets);
        }
        if(counter != spawnTicker) {
            counter++;
        }
        else {
            counter = 0;
            spawnTicker = rand.nextInt(difficulty);
            spawnEnemiesForTest();
        }
    }
}
