public class BattleCruiser extends Ship {

  public BattleCruiser() {
    this.setLength(7);
    this.setHit();
  }

  @Override
  public String getShipType() {
    return "battlecruiser";
  }
}
