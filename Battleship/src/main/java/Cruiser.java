public class Cruiser extends Ship {

  public Cruiser() {
    this.setLength(6);
    this.setHit();
  }

  @Override
  public String getShipType() {
    return "cruiser";
  }
}
