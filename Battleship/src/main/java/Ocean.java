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
    for (int i = 0; i < 20; i++) {
      for (int j = 0; j < 20; j++) {
        EmptySea emptySea = new EmptySea();
        emptySea.placeShipAt(i, j, true, this);
      }
    }
    this.shotsFired = 0;
    this.hitCount = 0;
    this.shipsSunk = 0;
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
  public void placeAllShipsRandomly() {
    Random random = new Random();
    Ship[] ships = new Ship[13];
    for (int i = 0; i < 13; i++) {
      if (i == 0) {
        ships[i] = new BattleShip();
      } else if (i == 1) {
        ships[i] = new BattleCruiser();
      } else if (i < 4) {
        ships[i] = new Cruiser();
      } else if (i < 6) {
        ships[i] = new LightCruiser();
      } else if (i < 9) {
        ships[i] = new Destroyer();
      } else if (i < 13) {
        ships[i] = new Submarine();
      }
    }

    for (Ship ship : ships) {
      while (true) {
        int row = random.nextInt(20);
        int column = random.nextInt(20);
        boolean horizontal = random.nextBoolean();
        if (ship.okToPlaceShipAt(row, column, horizontal, this)) {
          ship.placeShipAt(row, column, horizontal, this);
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
   * fired, the number of hits and shipsSunk. Note: If a location contains a ”real” ship, shootAt
   * should return true every time the user shoots at that same location. Once a ship has been
   * ”sunk”, additional shots at its location should return false.
   */
  public boolean shootAt(int row, int column) {
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
    return ships[row][column].shootAt(row, column); // when shot at an empty sea, returns false.
  }

  /**
   * Returns true if all ships have been sunk, otherwise false.
   */
  public boolean isGameOver() {
    return shipsSunk == 13;
  }

  /**
   * Prints the ocean. To aid the user, row numbers should be displayed along the left edge of the
   * array, and column numbers should be displayed along the top. Numbers should be 00 to 19, not 1
   * to 20.
   * <p>
   * The top left corner square should be 0, 0.
   * <p>
   * Use ’S’ to indicate a location that you have fired upon and hit a (real) ship, ’-’ to indicate
   * a location that you have fired upon and found nothing there, ’x’ to indicate a location
   * containing a sunken ship,
   * <p>
   * and ’.’ (a period) to indicate a location that you have never fired upon.
   */
  public void print() {
    System.out.println(toString());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(" ");
    for (int i = 0; i < 20; i++) {
      sb.append(String.format("%3d", i));
    }
    sb.append("\n");

    for (int i = 0; i < 20; i++) {
      sb.append(String.format("%2d ", i));
      for (int j = 0; j < 20; j++) {

        if (!ships[i][j].wasShootAt(i, j)) { // never been fired
          sb.append(".");
        } else {
          sb.append(ships[i][j].toString());
        }

        sb.append("  ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
