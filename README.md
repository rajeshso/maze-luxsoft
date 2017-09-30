Maze Test
=========

This coding example will form the basis of your interview with ******** (should you progress), you should be ready to explain any and all of the choices you have made when writing the solution.
There is no stated time limit but we would not envisage it would take longer than a couple of hours.

The solution must be representative of what you would produce 'on the job', by that we mean it must be clear, maintainable, demonstrably bug-free and tested.

The zipped project provided uses either:
* Apache Maven (Java)
* Solution project for Microsoft Visual Studio Community Edition (C#)

You should feel free to use any other tools you are more comfortable with.

There are 2 classes in it and you should feel free to change these in any way you see fit, including deleting them and starting again.

The test is based on exploring any arbitrary maze (one is provided).

User Story 1
------------

As a world famous explorer of Mazes I would like a maze to exist so that I can explore it

Acceptance Criteria:

* A Maze (as illustrated in ExampleMaze.txt) consists of walls 'X', Empty spaces ' ', one and only one Start point 'S' and one and only one exit 'F'.

* After a maze has been created the number of walls and empty spaces should be available to me.

* After a maze has been created I should be able to put in a coordinate and know what exists at that point.


User Story 2
------------

As a world famous explorer of Mazes I would like to exist in a maze and be able to navigate it so that I can explore it

Acceptance Criteria:

* Given a maze the explorer should be able to drop in to the Start point.

* An explorer in a maze must be able to:
    Move forward;
    Turn left and right;
    Understand what is in front of them;
    Understand all movement options from their given location;
    Have a record of where they have been.


UserStory 3
-----------
* An explorer must be able to automatically explore a maze and find the exit, and on exit they must be able to state the route they took in an understandable fashion.

Instructions to use this program
----------------------------------
The Source code is hosted in this github site.

The code can be git clone and imported into an IDE that has Java 8 SDK in the path - :

``` git clone https://github.com/rajeshso/maze-luxsoft.git ```

The source has a java file called excelian/maze/TestClient.java, which has the main method and acts as a gateway.

The TestClient can be executed without arguments from the IDE.

The TestClient can also optionally take an argument - the location of the Maze file

Another method to execute the code would be to export the code to an executable jar
 and running from a command line.

The Screen would display a content as below - :
```
Welcome to the next move in the maze.
Your explorer ID is 1
Your current position is 4-4
Your have explored these cells [4-4]
Your available directions from the current position are [RIGHT]
If you want to exit the maze, press *
Enter the next position to move :
```

The User Input for the next position to move may be RIGHT

The User can continue to explore the Maze.

If the User decides to exit, the user may enter a *

Note: The user system should have a JRE 8 available in the classpath

Design
------

The Explorer is designed to be a Visitor visiting the Maze.

The design pattern is a Visitor Pattern.

The Maze consists of cells. 

Every cell has an identity. Every cell has links to its neighbouring Cells.

The explorer can visit a particular cell.

The explorer can move to an available neighbouring cell using instruction
 such as LEFT, RIGHT, UP or DOWN.

The Explorer is a Visitor.

The Cell is Navigable (Visitable).

A Cell with a Wall is considered as Blocked for the Explorer

A Cell without a Wall is considered as Open for the Explorer.

The Explorer can make Moves to reach the Destination.