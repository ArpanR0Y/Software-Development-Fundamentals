import java.util.Random;

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

  public int getShotsFired() {
    return shotsFired;
  }

  public int getHitCount() {
    return hitCount;
  }

  public int getShipsSunk() {
    return shipsSunk;
  }

  /**
   * Adds a given ship in the 20X20 array
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

  /**
   * Place all ships randomly on the (initially empty) ocean. Large ships are placed before the
   * small ones to avoid running out of legal spaces to place them.
   */
  void placeAllShipsRandomly() {
    Random random = new Random();
    //Create new ship objects
    BattleShip battleShip = new BattleShip();
    BattleCruiser battleCruiser = new BattleCruiser();
    Cruiser cruiser = new Cruiser();
    LightCruiser lightCruiser = new LightCruiser();
    Destroyer destroyer = new Destroyer();
    Submarine submarine = new Submarine();

    //List of battle ships to be placed in order
    Ship[] battleShips = new Ship[]{battleShip, battleCruiser, cruiser, cruiser, lightCruiser,
        lightCruiser, destroyer, destroyer, destroyer, submarine, submarine, submarine, submarine};

    for (Ship ship : battleShips) {
      //Keep Searching for space to place the Ship and break when placed.
      while (true) {
        int row = random.nextInt(20);
        int column = random.nextInt(20);
        boolean isHorizontal = random.nextBoolean();
        if (ship.okToPlaceShipAt(row, column, isHorizontal, this)) {
          ship.placeShipAt(row, column, isHorizontal, this);
          break;
        }
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
   * Returns true if the given location contains a ”real” ship, still afloat, (not an EmptySea),
   * false if it does not. In addition, this method updates the number of shots that have been
   * fired, and the number of hits. Note: If a location contains a ”real” ship, shootAt should
   * return true every time the user shoots at that same location. Once a ship has been ”sunk”,
   * additional shots at its location should return false.
   */
  boolean shootAt(int row, int column) {
    if (this.isOccupied(row, column)) {
      this.hitCount++;
      this.shotsFired++;
      ships[row][column].shootAt(row, column);
      if (ships[row][column].toString().equals("x")) {
        this.shipsSunk++;
      }
      return true;
    }
    this.shotsFired++;
    return false;
  }

  /**
   * Returns true if all ships have been sunk, otherwise false.
   */
  boolean isGameOver() {
    return shipsSunk == 13;
  }
}
