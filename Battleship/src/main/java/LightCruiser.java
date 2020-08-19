public class LightCruiser extends Ship {

  public LightCruiser() {
    this.setLength(5);
    this.setHit();
  }

  @Override
  public String getShipType() {
    return "light cruiser";
  }
}
