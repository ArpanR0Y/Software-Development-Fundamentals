public class Ocean {

  private int shotsFired;
  private int hitCount;
  private int shipsSunk;

  private Ship[][] ships = new Ship[20][20];

  /**
   * The constructor. Creates an ”empty” ocean (fills the ships array with a bunch of EmptySea
   * instances).
   */
  public Ocean() {
    for (int row = 0; row < 20; row++) {
      for (int column = 0; column < 20; column++) {
        ships[row][column] = new EmptySea();
      }
    }
  }

  /**
   * Returns true if the given location contains a ship, false if it does not.
   */
  public boolean isOccupied(int row, int column) {
    return ships[row][column].toString().equals("S");
  }

  /**
   *Adds a given ship in the 20X20 array
   */
  public void setShipArray(Ship[][] ships) {
    this.ships = ships;
  }

  /**
   * Returns the 20x20 array of ships. The methods in the Ship class that take an Ocean parameter
   * really need to be able to look at the contents of this array; the placeShipAt method even needs
   * to modify it. While it is undesirable to allow methods in one class to directly access instance
   * variables in another class, sometimes there is just no good alternative.
   */
  public Ship[][] getShipArray() {
    return ships;
  }
}
