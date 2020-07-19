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

  public Squarelotron upsideDownFlip(int ring) {
    Squarelotron matrix = new Squarelotron(size);
    //TODO: This method performs the Upside-Down Flip of the squarelotron, and returns a
    // new squarelotron. The original squarelotron should not be modified
    return matrix;
  }

  public Squarelotron mainDiagonalFlip(int ring) {
    Squarelotron matrix = new Squarelotron(size);
    //TODO: This method performs the Main Diagonal Flip of the squarelotron, and returns a
    // new squarelotron. The original squarelotron should not be modified
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
