/**
 * CIS 120 Game HW (c) University of Pennsylvania
 *
 * @version 2.1, Apr 2017
 */

// importing java I/O stuff

// import map stuff

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.*;

/**
 * GameCourt
 *
 * <p>This class holds the primary game logic for how different objects interact with one another.
 * Take time to understand how the timer interacts with the different methods and how it repaints
 * the GUI on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

  // the state of the game logic
  private Bird bird;

  private ArrayList<Wall> walls; // set of walls to draw
  private ArrayList<Wall> wallsLower; // set of walls to draw

  public boolean playing = false; // whether the game is running
  private JLabel status; // Current status text, i.e. "Running..."
  private JLabel score;
  private JLabel highScore;
  private int count;

  // Game constants
  public static final int COURT_WIDTH = 300;
  public static final int COURT_HEIGHT = 300;
  public static final int BIRD_VELOCITY = 4;

  // Update interval for timer, in milliseconds
  public static final int INTERVAL = 35;

  // Stuff for Reading/Writing Scores

  Map<Integer, String> scoreToShapeMap = new TreeMap<Integer, String>();

  public GameCourt(JLabel status, JLabel score, JLabel highScore) {
    // creates border around the court area, JComponent method
    setBorder(BorderFactory.createLineBorder(Color.BLACK));

    // The timer is an object which triggers an action periodically with the given INTERVAL. We
    // register an ActionListener with this timer, whose actionPerformed() method is called each
    // time the timer triggers. We define a helper method called tick() that actually does
    // everything that should be done in a single timestep.
    Timer timer =
        new Timer(
            INTERVAL,
            new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                tick();
              }
            });
    timer.start(); // MAKE SURE TO START THE TIMER!

    // Enable keyboard focus on the court area.
    // When this component has the keyboard focus, key events are handled by its key listener.
    setFocusable(true);

    // This key listener allows the square to move as long as an arrow key is pressed, by
    // changing the square's velocity accordingly. (The tick method below actually moves the
    // square.)

    addKeyListener(
        new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
              bird.setVy(BIRD_VELOCITY);
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
              bird.setVy(-BIRD_VELOCITY - 10);
            }
          }

          public void keyReleased(KeyEvent e) {
            bird.setVx(0);
            bird.setVy(BIRD_VELOCITY);
          }
        });

    this.status = status;
    this.score = score;
    this.highScore = highScore;
      
      
  }

  public GameCourt() {
    walls = new ArrayList<Wall>();
    wallsLower = new ArrayList<Wall>();
    bird = new Square(COURT_WIDTH, COURT_HEIGHT, Color.BLACK);
    status = new JLabel("Running...");
    score = new JLabel("Score: ");
    highScore = new JLabel("Score:");
    count = 0;
  }

  /** (Re-)set the game to its initial state. */
  public void reset() {
    if (bird instanceof Circle) {
      bird = new Circle(COURT_WIDTH, COURT_HEIGHT, Color.BLACK);
    } else {
      bird = new Square(COURT_WIDTH, COURT_HEIGHT, Color.BLACK);
    }
    walls = new ArrayList<Wall>();
    wallsLower = new ArrayList<Wall>();

    playing = true;
    status.setText("Running...");
    count = 0;
    score.setText("Score is: " + (count));
    highScore.setText(getScores());

    // Make sure that this component has the keyboard focus
    requestFocusInWindow();
  }

  /** Changing the chape from square to circle or circle to square */
  public void shapeChange() {
    if (bird instanceof Square) {
      int xPos = bird.getPx();
      int yPos = bird.getPy();
      bird = new Circle(COURT_WIDTH, COURT_HEIGHT, Color.BLACK);
      bird.setPx(xPos);
      bird.setPy(yPos);
    } else if (bird instanceof Circle) {
      int xPos = bird.getPx();
      int yPos = bird.getPy();
      bird = new Square(COURT_WIDTH, COURT_HEIGHT, Color.BLACK);
      bird.setPx(xPos);
      bird.setPy(yPos);
    }
    reset();
  }

  public Bird getShape() {
    return bird;
  }

  public void addScores() {
    if (bird instanceof Square) {
      scoreToShapeMap.put(new Integer(count - 1), "Square");
    } else {
      scoreToShapeMap.put(new Integer(count - 1), "Circle");
    }

    if (scoreToShapeMap.size() >= 3) {
      try {
        BufferedWriter buffW = new BufferedWriter(new FileWriter("HighScores.txt"));
        Set<Integer> keys = scoreToShapeMap.keySet();

        List<Integer> keys2 = new ArrayList<Integer>(keys);

        Collections.reverse(keys2);
        int tCount = 0;
        for (Integer temp : keys2) {
          if (tCount >= 3) {
            break;
          }
          String temp1 = scoreToShapeMap.get(temp);
          buffW.write("(" + temp + ": " + temp1 + ")");
          buffW.newLine();
          tCount++;
        }

        buffW.close();
      } catch (Exception e) {
        System.out.println("Error occured addScores");
      }
    }
  }

  public String getScores() {
    if (scoreToShapeMap.size() < 3) {
      return ("Not enough scores");
    }
    try {
      BufferedReader reader = new BufferedReader(new FileReader("HighScores.txt"));
      String text = "";
      String line = reader.readLine();
      while (line != null) {
        text = text + line;
        line = reader.readLine();
      }
      reader.close();
      return text;
    } catch (Exception e) {
      System.out.println("Error occured");
      return ("");
    }
  }

  // Adding Walls Method
  public void addWalls() {
    for (Wall e : walls) {
      e.move();
    }
    for (Wall w : wallsLower) {
      w.move();
    }

    int len = walls.size();
    if (len >= 1) {
      Wall check = walls.get(len - 1);
      if (check.getPx() < 150) {
        int width = 30;
        int height = bird.getWallSize(count - 1);
        Wall temp = new Wall(COURT_WIDTH, COURT_HEIGHT, Color.BLUE, width, height, 300, 0);
        walls.add(temp);
        int height2 = bird.getWallSize(count - 1);
        Wall temp2 = new Wall(COURT_WIDTH, COURT_HEIGHT, Color.BLUE, width, height2, 300, 300);
        wallsLower.add(temp2);
        count++;
      }
    } else if (len == 0) {
      int width = 30;
      int height = bird.getWallSize(count - 1);
      Wall temp = new Wall(COURT_WIDTH, COURT_HEIGHT, Color.BLUE, width, height, 300, 0);
      walls.add(temp);
      int height2 = bird.getWallSize(count - 1);
      Wall temp2 = new Wall(COURT_WIDTH, COURT_HEIGHT, Color.BLUE, width, height2, 300, 300);
      wallsLower.add(temp2);
      count++;
    }
  }

  // Helper Methods for Unit Testing

  public ArrayList<Wall> getWalls() {
    return walls;
  }
    
  public Map<Integer, String> getMap(){
      return scoreToShapeMap; 
  }
    

  /** This method is called every time the timer defined in the constructor triggers. */
  void tick() {
    if (playing) {
      bird.move();

      addWalls();

      for (Wall e : walls) {
        if (bird.intersects(e)) {
          playing = false;
          status.setText("You Lose!");
          addScores();
        }

        score.setText("Score is: " + (count - 1));
      }
      for (Wall w : wallsLower) {
        if (bird.intersects(w)) {
          playing = false;
          status.setText("You Lose!");
          addScores();
        }
      }

      if (count == 3 || count == 5 || count == 7 || count == 10) {
        bird.scoreChangeShape(count);
      }

      repaint();
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    bird.draw(g);

    for (Wall e : walls) {
      if (e.getPx() > 0) {
        e.draw(g);
      }
    }
    for (Wall w : wallsLower) {
      if (w.getPx() > 0) {
        w.draw(g);
      }
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(COURT_WIDTH, COURT_HEIGHT);
  }
}
