package Model;


import Model.Item;
import static java.lang.Math.random;
import java.util.Random;

/*
 * This class inherits from item and represents an actual weapon power up item.
 */


//import Model.AbstractItem;

/**
 *
 * @author Michael McGregor
 */
public class WeaponPowerUp extends Item{

   private int weaponType, adsaf = 0;
   private float x, y;
   Random rand;
   

    public WeaponPowerUp(float x, float y, int ref) {
        super(x, y, ref, 0, 0, 32, 32);
        rand = new Random();
        int randomNum = rand.nextInt((3 - 0) + 1) + 0;
        setItem(randomNum);
    }
   
  @Override
    public void setItem(int item) {
        this.itemType = item;
    }
            
}