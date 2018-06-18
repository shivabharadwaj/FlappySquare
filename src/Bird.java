/**
 * CIS 120 Game HW (c) University of Pennsylvania
 *
 * @version 2.1, Apr 2017
 */

import java.awt.*;

/**
 * A basic game object starting in the upper left corner of the game court. It is displayed as a
 * square of a specified color.
 */
public abstract class Bird extends GameObj {
  public static int SIZE = 20;
  public static final int INIT_POS_X = 150;
  public static final int INIT_POS_Y = 150;
  public static final int INIT_VEL_X = 0;
  public static int INIT_VEL_Y = 5;

  public static Color color;

  /**
   * Note that, because we don't need to do anything special when constructing a Square, we simply
   * use the superclass constructor called with the correct parameters.
   */
  public Bird(int courtWidth, int courtHeight, Color color) {
    super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);
    Bird.color = color;
  }

  public abstract void draw(Graphics g);
      
  public abstract void scoreChangeShape (int sc);
    
  public abstract int getWallSize(int sc);
        
  
}
