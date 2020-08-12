public class Ocean {

  private Ship[][] ships = new Ship[20][20];

  public boolean isOccupied(int row, int column) {
    return false;
  }

  public void setShipArray(Ship[][] ships) {
    this.ships = ships;
  }

  public Ship[][] getShipArray() {
    return ships;
  }
}
