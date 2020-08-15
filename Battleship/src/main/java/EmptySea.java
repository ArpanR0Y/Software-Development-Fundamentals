public class EmptySea extends Ship {

  /**
   * This constructor sets the inherited length variable to 1.
   */
  public EmptySea() {
    this.setLength(1);
    this.setHit();
  }

  /**
   * This method overrides shootAt(int row, int column) that is inherited from Ship, and always
   * returns false to indicate that nothing was hit.
   */
  @Override
  public boolean shootAt(int row, int column) {
    this.getHit()[0] = true;
    return false;
  }

  /**
   * This method overrides isSunk() that is inherited from Ship, and always returns false to
   * indicate that you didn’t sink anything.
   */
  @Override
  public boolean isSunk() {
    return false;
  }

  /**
   * Returns a single-character String to use in the Ocean’s print method.
   */
  @Override
  public String toString() {
    return this.getHit()[0] ? "-" : ".";
  }

  /**
   * This method just returns the string ”empty”
   */
  @Override
  public String getShipType() {
    return "empty";
  }

}
