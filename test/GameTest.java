import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
  private GameCourt game;

  @Before
  public void setUp() {
    // We initialize a fresh gameCourt for each test
    game = new GameCourt();
  }

  // TESTING ADD WALLS FUNCTIONALITY=======================
  // ======================================================
  // ======================================================

  // Testing that adding a wall function updates wall list

  @Test
  public void testAddWallsFunctionality() {
    game.addWalls();

    int expected = 1;
    List<Wall> temp = game.getWalls();

    int actual = temp.size();

    assertEquals("Wall_Size", expected, actual);
  }

  // TESTING SHAPE CHANGE Functionality====================
  // ======================================================
  // ======================================================

  // Shape change from square to circle.
  @Test
  public void testSquareToCircle() {

    game.shapeChange();

    Bird actual = game.getShape();

    assertTrue(actual instanceof Circle);
  }
  // Shape change from circle to square.
  @Test
  public void testCircleToSquare() {
    game.shapeChange();
    game.shapeChange();
    Bird actual = game.getShape();
    assertTrue(actual instanceof Square);
  }

  // TESTING Reset Functionality===========================
  // ======================================================
  // ======================================================

  // Resetting back to circle.
  @Test
  public void testResetShapeCircle() {
    game.shapeChange();
    game.reset();
    Bird actual = game.getShape();
    assertTrue(actual instanceof Circle);
  }

  // Resetting back to square.
  @Test
  public void testResetShapeSquare() {
    game.shapeChange();
    game.shapeChange();
    game.reset();
    Bird actual = game.getShape();
    assertTrue(actual instanceof Square);
  }

  // Resetting walls to size 0.
  @Test
  public void testResetWalls() {
    game.addWalls();
    game.reset();
    int expected = 0;
    List<Wall> temp = game.getWalls();
    int actual = temp.size();
    assertEquals("Wall_Size", expected, actual);
  }

  // TESTING ADD SCORES AND GET SCORES Functionality=======
  // ======================================================
  // ======================================================

  // Testing Add Scores to tree Map
  @Test
  public void addScores() {
    game.addScores();
    int expected = 1;
    Map<Integer, String> temp = game.getMap();
    int actual = temp.size();
    assertEquals("Map_Size", expected, actual);
  }

  // Testing Get Scores
  @Test
  public void getScores() {
    game.addScores();
    String expected = "Not enough scores";
    String actual = game.getScores();
    assertTrue(expected.equals(actual));
  }
}
