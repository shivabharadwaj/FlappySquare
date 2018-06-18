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
public class Circle extends Bird {

  public Circle(int w, int h, Color c) {
    super(w, h, c);
  }

  public void draw(Graphics g) {
    g.setColor(Bird.color);
    g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
  }
    
  public void scoreChangeShape (int sc){
      if (sc == 3){
          setWidth(23);
          setHeight(23);
      }
      if (sc == 5){
          setWidth(26);
          setHeight(26);
      }
      if (sc == 7){
          setWidth(30);
          setHeight(30);
      }
      if (sc == 10){
          setWidth(60);
          setHeight(60);
      }
  }
    
  public int getWallSize(int sc){
      if(sc > 10){
          return (((int) (25 * Math.random())) + 1) + 20; 
      }
      
      if (sc > 7){
          return (((int) (50 * Math.random())) + 1) + 20;
      }
      
      if (sc > 5){
          return (((int) (75 * Math.random())) + 1) + 20;
      }
      
      return (((int) (100 * Math.random())) + 1) + 20;
      
  }
    

}
