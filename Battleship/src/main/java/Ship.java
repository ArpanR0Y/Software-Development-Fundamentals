public abstract class Ship {

  private int bowRow;
  private int bowColumn;
  private int length;
  private boolean horizontal;
  private boolean[] hit;

  public int getBowRow() {
    return bowRow;
  }

  public int getBowColumn() {
    return bowColumn;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public boolean isHorizontal() {
    return horizontal;
  }

  public boolean[] getHit() {
    return hit;
  }

  public void setHit() {
    hit = new boolean[length];
  }

  abstract String getShipType();

  /**
   * Returns true if it is okay to put a ship of this length with its bow in this location, with the
   * given orientation, and returns false otherwise. The ship must not overlap another ship, or
   * touch another ship (vertically, horizontally, or diagonally), and it must not ”stick out”
   * beyond the array. Do not actually change either the ship or the Ocean, just says whether it is
   * legal to do so.
   */
  boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
    if (horizontal) {
      if (column + length > 20) {
        return false;
      }
      for (int r = row - 1; r < row + 2; r++) {
        for (int c = column - 1; c < column + length + 1; c++) {
          try {
            if (ocean.isOccupied(r, c)) {
              return false;
            }
          } catch (ArrayIndexOutOfBoundsException ignored) {
          }
        }
      }
    } else {
      if (row + length > 20) {
        return false;
      }
      for (int r = row - 1; r < row + length + 1; r++) {
        for (int c = column - 1; c < column + 2; c++) {
          try {
            if (ocean.isOccupied(r, c)) {
              return false;
            }
          } catch (ArrayIndexOutOfBoundsException ignored) {
          }
        }
      }
    }
    return true;
  }

  /**
   * ”Puts” the ship in the ocean. This involves giving values to the bowRow, bowColumn, and
   * horizontal instance variables in the ship, and it also involves putting a reference to the ship
   * in each of 1 or more locations (up to 8) in the ships array in the Ocean object. (Note: This
   * will be as many as eight identical references; you can’t refer to a ”part” of a ship, only to
   * the whole ship.)
   */
  void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
    bowRow = row;
    bowColumn = column;
    this.horizontal = horizontal;

      Ship[][] ships = ocean.getShipArray();
      if (horizontal) {
        for (int c = column; c < column + length; c++) {
          ships[row][c] = this;
        }
      } else {
        for (int r = row; r < row + length; r++) {
          ships[r][column] = this;
        }
      }
      ocean.setShipArray(ships);
  }

  /**
   * If a part of the ship occupies the given row and column, and the ship hasn't been sunk, mark
   * that part of the ship as ”hit” (in the hit array, 0 indicates the bow) and return true,
   * otherwise return false.
   */
  public boolean shootAt(int row, int column) {
    if (!isSunk()) {
      if (horizontal) {
        if (row == this.bowRow && column < this.bowColumn + length) {
          hit[column - this.bowColumn] = true;
          return true;
        }
      } else {
        if (column == this.bowColumn && row < this.bowRow + length) {
          hit[row - this.bowRow] = true;
          return true;
        }
      }
    }
    return false;
  }

  public boolean wasShotAt(int row, int column) {
    if (horizontal) {
      return this.hit[column - this.bowColumn];
    } else {
      return this.hit[row - this.bowRow];
    }
  }

  /**
   * Return true if every part of the ship has been hit, false otherwise.
   */
  boolean isSunk() {
    for (boolean isHit : this.hit) {
      if (!isHit) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns a single-character String to use in the Ocean’s print method (see below).
   * <p>
   * This method should return ”x” if the ship has been sunk, ”S” if it has not been sunk. This
   * method can be used to print out locations in the ocean that have been shot at; it should not be
   * used to print locations that have not been shot at.
   * <p>
   * Since toString behaves exactly the same for all ship types, it can be moved into the Ship
   * class.
   * <p>
   * Note that the toString method for the EmptySea class has to override the Ship class's
   * implementation. In order to figure out what needs to be done, please see the description of the
   * print method in the Ocean class.
   */
  @Override
  public String toString() {
    if (this.isSunk()) {
      return "x";
    } else {
      return "S";
    }
  }
}
