# WhackAMole

This is a variant of the classic whack-a-mole game.
Create a class called WhackAMole.

It contains three integer instance variables called score, molesLeft, and attemptsLeft. It contains one more instance variable called moleGrid which is a 2-dimensional array of chars.


The class comprises of the following methods: 


**WhackAMole(int numAttempts, int gridDimension)** - Constructor for the game, specifies total number of whacks allowed, and the grid dimension.


**boolean place(int x, int y)** – Given a location, place a mole at that location. Return true after successfully placed.     


**void whack(int x, int y)** -  Given a location, take a whack at that location. If that location contains a mole, the score, number of attemptsLeft, and molesLeft is updated. If that location does not contain a mole, only attemptsLeft is updated.


**void printGridToUser()** – Print the grid without showing where the moles are. For every spot that has recorded a “whacked mole,” print out a W, or * otherwise.


**void printGrid()** -  Print the grid completely. This is effectively dumping the 2d array on the screen. The places where the moles are should be printed as an ‘M’. The places where the moles have been whacked should be printed as a ‘W’. The places that don’t have a mole should be printed as *.


**Putting it all together - main method**

TODO
