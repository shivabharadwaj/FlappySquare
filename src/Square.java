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
public class Square extends Bird {

  public Square(int w, int h, Color c) {
    super(w, h, c);
  }

  public void draw(Graphics g) {
    g.setColor(Bird.color);
    g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
  }
    
    
  public void scoreChangeShape (int sc){
     if (sc == 3){
          setWidth(17);
          setHeight(17);
      }
      if (sc == 5){
          setWidth(14);
          setHeight(14);
      }
      if (sc == 7){
          setWidth(10);
          setHeight(10);
      } 
  }
  
  public int getWallSize(int sc){
      if(sc > 10){
          return (((int) (100 * Math.random())) + 1) + 30; 
      }
      
      if (sc > 7){
          return (((int) (75 * Math.random())) + 1) + 30;
      }
      
      if (sc > 5){
          return (((int) (50 * Math.random())) + 1) + 30;
      }
      
      return (((int) (25 * Math.random())) + 1) + 30;
      
  }
    
}
