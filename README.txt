=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: shivabh
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an approprate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Modeling state using Collections. I use an Arraylist to store my walls. This is an appropriate data type
     to use as the walls need to be stored in the same order that they were entered in. 

  2. Inheritence/ Subtyping. I have an abstract Bird Class that inherits GameObj. The two different types of Birds
     are Squares and Circles. Each are drawn differently, their walls are drawn differently, and their 
     sizes change differently. For this reason, the Bird class has three abstract methods that correspond to these 
     differences. The Circle and the Square class (which extend Bird) implement all these abstract classes. 

  3. Using IO to parse a file. I add all scores into an Integer to Shape treeMap(Integer being score and shape being
     the corresponding shape related to the score). I then write the top three scores to a text file. Whenever the 
     reset button is pressed, I read from the text file to eventually show the top three scores on the display. 
  

  4. Using JUnit on a testable component of the game. I test the functionality of the addWalls, shapeChange, reset, 
     addScores, and getScores. These are the heavy-lifting methods in the GameCourt class and I was able to test them 
     independently of the GUI. 


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  GameObj—abstract class that deinfes the main capabilities of a game Object (including bird and wall)
  Bird—abstract class that extends GameObj and defines characteristcs main characteristics of a bird. 
  Wall—class that extends GameObj defines how a wall is created, drawn, and moves across the screen.
  Circle—class that extends Bird and defines characteristcs of a circle (how it is drawn, reacts to scores, etc).
  Square—class that extends Bird and defines characteristcs of a square (how it is drawn, reacts to scores, etc).
  GameCourt—Implements main data structures that keep track of the walls, score, and bird states. 
  Game—class that implements main components of the GUI—what the user sees. 


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  Coming up with a sounds hierarchy for the classes was perhaps the most thought provoking part of this project. 
  However, I think that having the Bird class extend GameObj and the Square and the Circle extend the Bird was 
  the best and cleanest solution. 


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  I think the private state is encapsulated well within the GameCourt class. Perhaps one thing to change would be
  that many of the fields within the Bird class are static (easily referenced by other classes). This may be 
  detremental in the scheme of encapsulation. Furthermore, I might better sepparate some of the functionalities 
  within the GameCourt class. 

  

========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.

  None. 

