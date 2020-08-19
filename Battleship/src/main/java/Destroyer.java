public class Destroyer extends Ship {

  public Destroyer() {
    this.setLength(4);
    this.setHit();
  }

  @Override
  public String getShipType() {
    return "destroyer";
  }
}
