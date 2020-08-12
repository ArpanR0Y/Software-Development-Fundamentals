public class Destroyer extends Ship {

  public Destroyer() {
    this.setLength(4);
    this.setHit();
  }

  @Override
  String getShipType() {
    return "destroyer";
  }
}
