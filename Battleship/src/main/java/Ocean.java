public class Ocean {

  private Ship[][] ships = new Ship[20][20];

  //TODO: Temporary implementation, remove null check after implementing Empty Sea
  public boolean isOccupied(int row, int column) {
    return ships[row][column] != null && ships[row][column].toString().equals("S");
  }

  public void setShipArray(Ship[][] ships) {
    this.ships = ships;
  }

  public Ship[][] getShipArray() {
    return ships;
  }
}
