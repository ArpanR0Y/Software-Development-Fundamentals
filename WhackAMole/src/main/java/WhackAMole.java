import java.util.Random;
import java.util.Scanner;

public class WhackAMole {

  int score;
  int molesLeft;
  int attemptsLeft;

  char[][] moleGrid;

  public WhackAMole(int numAttempts, int gridDimension) {
    this.attemptsLeft = numAttempts;
    this.moleGrid = new char[gridDimension][gridDimension];
  }

  private void place(int x, int y) {
    if (moleGrid[x][y] == '\u0000') {
      moleGrid[x][y] = 'M';
      molesLeft--;
    }
  }

  private void whack(int x, int y) {
    if (moleGrid[x][y] == 'M') {
      moleGrid[x][y] = 'X';
      score++;
    } else {
      moleGrid[x][y] = 'W';
    }
    attemptsLeft--;
  }

  private void printGridToUser() {
    for (char[] row : moleGrid) {
      for (char element : row) {
        if (element == 'X') {
          System.out.print(" X ");
        } else if (element == 'W') {
          System.out.print(" W ");
        } else {
          System.out.print(" * ");
        }
      }
      System.out.println();
    }
  }

  private void printGrid() {
    for (char[] row : moleGrid) {
      for (char element : row) {
        if (element == 'M') {
          System.out.print(" M ");
        } else if (element == 'W') {
          System.out.print(" W ");
        } else if (element == 'X') {
          System.out.print(" X ");
        } else {
          System.out.print(" * ");
        }
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    System.out.print("Enter Grid Size: ");
    int gridSize = scanner.nextInt();

    WhackAMole whackAMole = new WhackAMole((gridSize * 2) - 1, gridSize);

    whackAMole.molesLeft = gridSize;

    while (whackAMole.molesLeft > 0) {
      whackAMole.place(random.nextInt(gridSize), random.nextInt(gridSize));
    }

    while (whackAMole.attemptsLeft >= 0) {
      System.out.println("No. of Moles: " + gridSize);
      System.out.println("Score: " + whackAMole.score);
      System.out.println("Attempts Left: " + whackAMole.attemptsLeft);
      System.out.println("Enter the coordinate to whack, (eg: 1 1):");
      whackAMole.printGridToUser();
      int x = scanner.nextInt() - 1;
      int y = scanner.nextInt() - 1;
      if (x >= 0 && y >= 0) {
        whackAMole.whack(x, y);
        if (whackAMole.score == gridSize) {
          System.out.println("You won!!");
          System.out.println("Score: " + whackAMole.score);
          whackAMole.printGrid();
          break;
        }
        if (whackAMole.attemptsLeft == 0) {
          System.out.println("Better Luck next time.");
          System.out.println("Score: " + whackAMole.score);
          whackAMole.printGrid();
          break;
        }
      } else {
        System.out.println("Game Exited!!");
        System.out.println("Score: " + whackAMole.score);
        whackAMole.printGrid();
        break;
      }
    }
  }
}