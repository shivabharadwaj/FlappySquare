/**
 * CIS 120 Game HW (c) University of Pennsylvania
 *
 * @version 2.1, Apr 2017
 */

import java.awt.*;

/**
 * A basic game object starting in the upper left corner of the game court. It is displayed as a
 * circle of a specified color.
 */
public class Wall extends GameObj {
  public static final int INIT_VEL_X = -3;
  public static final int INIT_VEL_Y = 0;

  private Color color;

  public Wall(
      int courtWidth, int courtHeight, Color color, int width, int height, int initX, int initY) {
    super(INIT_VEL_X, INIT_VEL_Y, initX, initY, width, height, courtWidth, courtHeight);
    this.color = color;
  }

  public void draw(Graphics g) {
    g.setColor(this.color);
    g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
  }    
    
}