import java.util.Arrays;
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
    for (char[] r : moleGrid) {
      Arrays.fill(r, '*');
    }
  }

  public boolean place(int x, int y) {
    if (moleGrid[x][y] == 'M') {
      return false;
    }
    moleGrid[x][y] = 'M';
    molesLeft++;
    return true;
  }

  public void whack(int x, int y) {
    if (moleGrid[x][y] == 'M') {
      moleGrid[x][y] = 'X';
      score++;
      molesLeft--;
    } else {
      moleGrid[x][y] = 'W';
    }
    attemptsLeft--;
  }

  public void printGridToUser() {
    for (char[] row : moleGrid) {
      for (char element : row) {
        if (element == 'W') {
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

  public void printGrid() {
    for (char[] row : moleGrid) {
      for (char element : row) {
        System.out.print(" "+element+" ");
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

    while (whackAMole.molesLeft < gridSize) {
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