public class BattleShip extends Ship {

  public BattleShip() {
    this.setLength(8);
    this.setHit();
  }

  @Override
  String getShipType() {
    return "battleship";
  }
}
