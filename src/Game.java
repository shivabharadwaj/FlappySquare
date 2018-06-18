/**
 * CIS 120 Game HW (c) University of Pennsylvania
 *
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Game Main class that specifies the frame and widgets of the GUI */
public class Game implements Runnable {
  public void run() {
    // NOTE : recall that the 'final' keyword notes immutability even for local variables.

    // Top-level frame in which game components live
    // Be sure to change "TOP LEVEL FRAME" to the name of your game
    final JFrame frame = new JFrame("Hungry Flappy Objects");
    frame.setLocation(300, 100);

    final JFrame frame2 = new JFrame("Instructions!!!");
    frame2.setLocation(100, 200);    
    final JLabel inst =
        new JLabel(
            "<html>To Play this game click reset then use the up arrow. If you hit a wall, you lose.<br>You can change shapes by clicking the shape button on the screen.<br> For squares, your shape will progressively become smaller. Circles will become progressively larger! <br> Have Fun!!</html>");
    frame2.add(inst, BorderLayout.CENTER);

    
    // Status panel
    final JPanel status_panel = new JPanel();
    frame.add(status_panel, BorderLayout.WEST);
    final JLabel status = new JLabel("Running...");
    status_panel.add(status);

    // Score panel
    final JPanel score_panel = new JPanel();
    frame.add(score_panel, BorderLayout.EAST);
    final JLabel score = new JLabel("Score: ");
    score_panel.add(score);

    // High Score panel
    final JPanel highScore_panel = new JPanel();
    frame.add(highScore_panel, BorderLayout.SOUTH);
    final JLabel highScore = new JLabel("Score:");
    final JLabel words = new JLabel("HighScores Here: ");
    highScore_panel.add(words);
    highScore_panel.add(highScore);

    // Main playing area
    final GameCourt court = new GameCourt(status, score, highScore);
    frame.add(court, BorderLayout.CENTER);

    // Reset button
    final JPanel control_panel = new JPanel();
    frame.add(control_panel, BorderLayout.NORTH);

    // Note here that when we add an action listener to the reset button, we define it as an
    // anonymous inner class that is an instance of ActionListener with its actionPerformed()
    // method overridden. When the button is pressed, actionPerformed() will be called.
    final JButton reset = new JButton("Reset");
    reset.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            court.reset();
          }
        });
    control_panel.add(reset);

    // Shape button
    frame.add(control_panel, BorderLayout.NORTH);
    final JButton shape = new JButton("Shape");
    shape.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            court.shapeChange();
          }
        });
    control_panel.add(shape);

    // Put the frame on the screen
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    frame2.pack();
    frame2.setVisible(true);

    // Start game
    court.reset();
  }

  /**
   * Main method run to start and run the game. Initializes the GUI elements specified in Game and
   * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Game());
  }
}
