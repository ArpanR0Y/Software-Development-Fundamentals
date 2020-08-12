public class Submarine extends Ship{

  public Submarine() {
    this.setLength(3);
    this.setHit();
  }

  @Override
  String getShipType() {
    return "submarine";
  }
}
