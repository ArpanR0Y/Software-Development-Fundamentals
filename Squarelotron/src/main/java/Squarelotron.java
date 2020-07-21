import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Squarelotron {

  int[][] squarelotron;
  int size;

  public Squarelotron(int size) {
    this.squarelotron = new int[size][size];
    this.size = size;
    int element = 1;
    for (int row = 0; row < size; row++) {
      for (int column = 0; column < size; column++) {
        squarelotron[row][column] = element++;
      }
    }
  }

  public ArrayList<Integer> getRing(int ringNo) {
    ArrayList<Integer> ring = new ArrayList<>();

    int ringPos = ringNo - 1;

    for (int row = ringPos; row < size - ringPos; row++) {
      for (int column = ringPos; column < size - ringPos; column++) {
        if (row == ringPos) {
          ring.add(squarelotron[row][column]);
        } else if (row == size - 1 - ringPos) {
          ring.add(squarelotron[row][column]);
        } else if (column == ringPos) {
          ring.add(squarelotron[row][column]);
        } else if (column == size - 1 - ringPos) {
          ring.add(squarelotron[row][column]);
        }
      }
    }

    return ring;
  }

  public Squarelotron upsideDownFlip(int ringNo) {
    Squarelotron matrix = new Squarelotron(size);

    ArrayList<Integer> ring = matrix.getRing(ringNo);

    for (int row = 0; row < size; row++) {
      for (int column = 0; column < size; column++) {
        if (ring.contains(matrix.squarelotron[row][column])) {
          matrix.squarelotron[row][column] = this.squarelotron[size - 1 - row][column];
        }
      }
    }
    return matrix;
  }

  public Squarelotron mainDiagonalFlip(int ringNo) {
    Squarelotron matrix = new Squarelotron(size);

    ArrayList<Integer> ring = matrix.getRing(ringNo);

    for (int row = 0; row < size; row++) {
      for (int column = 0; column < size; column++) {
        if (ring.contains(matrix.squarelotron[row][column])) {
          matrix.squarelotron[row][column] = this.squarelotron[column][row];
        }
      }
    }
    return matrix;
  }

  public void rotateRight(int numberOfTurns) {
    numberOfTurns = (numberOfTurns % 4 + 4) % 4;

    for (int n = 0; n < numberOfTurns; n++) {
      Squarelotron matrix = new Squarelotron(size);
      for (int row = 0; row < size; row++) {
        for (int column = 0; column < size; column++) {
          matrix.squarelotron[column][size - 1 - row] = squarelotron[row][column];
        }
      }
      this.squarelotron = matrix.squarelotron;
    }
  }

  public static void printSquarelotron (int[][] matrix) {
    int cols = matrix[0].length;
    int[] colWidths = new int[cols];
    for (int[] row : matrix) {
      for (int c = 0; c < cols; c++) {
        int width = String.valueOf(row[c]).length();
        colWidths[c] = Math.max(colWidths[c], width);
      }
    }
    for (int[] row : matrix) {
      for (int c = 0; c < cols; c++) {
        String fmt = String.format("%s%%%dd%s",
            c == 0 ? "|" : "  ",
            colWidths[c],
            c < cols - 1 ? "" : "|%n");
        System.out.printf(fmt, row[c]);
      }
    }
  }

  public static void menu () {
    System.out.println("1. To flip the Squarelotron up side down");
    System.out.println("2. To flip the Squarelotron along the main diagonal");
    System.out.println("3. To rotate the Squarelotron");
    System.out.println("0. To exit the Matrix");
  }

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Welcome Neo, to the Matrix(Squarelotron)");
    System.out.println("Enter the size of Squarelotron: ");
    int size = scanner.nextInt();
    Squarelotron matrix = new Squarelotron(size);
    printSquarelotron(matrix.squarelotron);
    menu();

    int start = 1;
    while (start >= 0) {
      if (start != 1) {
        printSquarelotron(matrix.squarelotron);
        menu();
      }
      start = 0;
      switch (scanner.nextInt()) {
        case 1:
          System.out.print("Enter the ring number to flip: ");
          printSquarelotron(matrix.upsideDownFlip(scanner.nextInt()).squarelotron);
          System.out.println();
          System.in.read();
          break;
        case 2:
          System.out.print("Enter the ring number to flip: ");
          printSquarelotron(matrix.mainDiagonalFlip(scanner.nextInt()).squarelotron);
          System.out.println();
          System.in.read();
          break;
        case 3:
          System.out.println("Enter the number of clockwise rotations (-ve for counter clockwise): ");
          matrix.rotateRight(scanner.nextInt());
          printSquarelotron(matrix.squarelotron);
          System.out.println();
          System.in.read();
          break;
        case 0:
          System.out.println("So, you took the blue pill, Sweet dreams.");
          start = -1;
          break;
        default:
          System.out.println("Invalid Option");
          System.out.println();
          break;
      }
    }
  }
}
