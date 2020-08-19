public class BattleShip extends Ship {

  public BattleShip() {
    this.setLength(8);
    this.setHit();
  }

  @Override
  public String getShipType() {
    return "battleship";
  }
}
