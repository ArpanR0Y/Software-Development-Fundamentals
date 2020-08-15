public class EmptySea extends Ship {

  public EmptySea() {
    this.setLength(1);
    this.setHit();
  }

  @Override
  public boolean shootAt(int row, int column) {
    this.getHit()[0] = true;
    return false;
  }

  @Override
  public boolean isSunk() {
    return false;
  }

  @Override
  public String toString() {
    return this.getHit()[0] ? "-" : ".";
  }

  @Override
  public String getShipType() {
    return "empty";
  }

}
