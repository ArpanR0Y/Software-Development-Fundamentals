public class LightCruiser extends Ship {

  public LightCruiser() {
    this.setLength(5);
    this.setHit();
  }

  @Override
  String getShipType() {
    return "light cruiser";
  }
}
