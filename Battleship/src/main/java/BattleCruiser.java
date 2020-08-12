public class BattleCruiser extends Ship {

  public BattleCruiser() {
    this.setLength(7);
    this.setHit();
  }

  @Override
  String getShipType() {
    return "battlecruiser";
  }
}
