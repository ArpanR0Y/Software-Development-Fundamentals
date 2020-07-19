import java.util.ArrayList;

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
    //TODO: The argument numberOfTurns indicates the number of times the entire squarelotron
    // should be rotated 90° clockwise. Any integer, including zero and negative integers,
    // is allowable as the argument. A value of -1 indicates a 90° counterclockwise rotation.
    // This method modifies the internal representation of the squarelotron; it does not create
    // a new squarelotron.
  }

  public static void main(String[] args) {
    //TODO: Main Method
  }

}
