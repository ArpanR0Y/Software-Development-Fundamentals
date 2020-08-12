public class Cruiser extends Ship {

  public Cruiser() {
    this.setLength(6);
    this.setHit();
  }

  @Override
  String getShipType() {
    return "cruiser";
  }
}
