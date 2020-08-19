public class Submarine extends Ship{

  public Submarine() {
    this.setLength(3);
    this.setHit();
  }

  @Override
  public String getShipType() {
    return "submarine";
  }
}
