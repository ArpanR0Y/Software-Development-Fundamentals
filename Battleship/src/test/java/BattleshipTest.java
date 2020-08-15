import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BattleshipTest {

  private Ship battleship;
  private Ship cruiser;
  private Ship destroyer;
  private Ship submarine;
  private Ship emptySea;
  private Ocean ocean1;

  private Ocean ocean;

  @Before
  public void setUp() {
    battleship = new BattleShip();
    cruiser = new Cruiser();
    destroyer = new Destroyer();
    submarine = new Submarine();
    emptySea = new EmptySea();
    ocean1 = new Ocean();
    for (int i = 2; i < 10; i++) {
      ocean1.getShipArray()[i][3] = battleship;
    }

    ocean = new Ocean();

  }

  @Test
  public void testObjectsOfAbstractClass() {
    assertNotNull(battleship);
    assertTrue(battleship instanceof BattleShip);
    assertFalse(cruiser instanceof Destroyer);
    assertFalse(emptySea instanceof Submarine);
  }

  @Test
  public void testGetLength() {
    assertEquals(8, battleship.getLength());
    assertEquals(6, cruiser.getLength());
    assertEquals(4, destroyer.getLength());
    assertEquals(3, submarine.getLength());
    assertEquals(1, emptySea.getLength());
  }

  @Test
  public void testGetShipType() {
    assertEquals("battleship", battleship.getShipType());
    assertEquals("cruiser", cruiser.getShipType());
    assertEquals("destroyer", destroyer.getShipType());
    assertEquals("submarine", submarine.getShipType());
    assertEquals("empty", emptySea.getShipType());
    assertEquals("empty", emptySea.getShipType());
  }

  @Test
  public void testOkToPlaceShipAt() {
    assertTrue("This battleship should have been ok to place",
        battleship.okToPlaceShipAt(11, 0, true, ocean1));
    assertFalse("Diagonal adjacency is not allowed",
        destroyer.okToPlaceShipAt(1, 4, true, ocean1)); // diagonally
    // touch
    assertTrue(destroyer.okToPlaceShipAt(0, 1, true, ocean1));
    assertFalse("Two ships cannot overlap",
        destroyer.okToPlaceShipAt(2, 2, true, ocean1)); // overlap
    assertFalse("Two ships cannot touch each other vertically",
        destroyer.okToPlaceShipAt(10, 3, false, ocean1)); // vertically
    // touch
    assertFalse("Placed a ship that stuck out of the ocean",
        cruiser.okToPlaceShipAt(0, 18, true, ocean1)); // stick
    // out
    assertFalse("Two ships cannot touch each other horizontally",
        submarine.okToPlaceShipAt(2, 4, true, ocean1)); // horizontally
    // touch
  }

  @Test
  public void testPlaceShipAt() {
    cruiser.placeShipAt(11, 4, true, ocean1);
    assertEquals(11, cruiser.getBowRow());
    assertEquals(4, cruiser.getBowColumn());
    String message = "cruiser.placeShipAt(11, 4, true, ocean1); should be horizontal";
    assertTrue(message, cruiser.isHorizontal());
    assertEquals("cruiser", ocean1.getShipArray()[11][4].getShipType());
    assertEquals("cruiser", ocean1.getShipArray()[11][5].getShipType());
    assertEquals("cruiser", ocean1.getShipArray()[11][6].getShipType());
    assertEquals("cruiser", ocean1.getShipArray()[11][7].getShipType());
    assertEquals("cruiser", ocean1.getShipArray()[11][8].getShipType());
    assertEquals("cruiser", ocean1.getShipArray()[11][9].getShipType());

    battleship.placeShipAt(1, 6, false, ocean1);
    assertEquals(1, battleship.getBowRow());
    assertEquals(6, battleship.getBowColumn());
    assertFalse(battleship.isHorizontal());
    assertEquals("battleship", ocean1.getShipArray()[1][6].getShipType());
    assertEquals("battleship", ocean1.getShipArray()[2][6].getShipType());
    assertEquals("battleship", ocean1.getShipArray()[3][6].getShipType());
    assertEquals("battleship", ocean1.getShipArray()[4][6].getShipType());
    assertEquals("battleship", ocean1.getShipArray()[5][6].getShipType());
    assertEquals("battleship", ocean1.getShipArray()[6][6].getShipType());
    assertEquals("battleship", ocean1.getShipArray()[7][6].getShipType());
    assertEquals("battleship", ocean1.getShipArray()[8][6].getShipType());

    submarine.placeShipAt(0, 0, true, ocean1);
    assertEquals("submarine", ocean1.getShipArray()[0][0].getShipType());
  }

  @Test
  public void testShootAt() {
    assertFalse(battleship.shootAt(7, 7)); // miss
    assertFalse(battleship.getHit()[0]);
    battleship.placeShipAt(2, 3, false, ocean1);
    assertTrue(battleship.shootAt(2, 3)); // shoot battleship bow
    assertTrue(battleship.getHit()[0]);
    assertFalse(battleship.getHit()[2]);
    assertTrue(battleship.shootAt(4, 3)); // shoot battleship body
    assertTrue(battleship.getHit()[2]);
    assertTrue(battleship.shootAt(2, 3)); // shoot same spot before
    // sinking
    assertTrue(battleship.shootAt(3, 3)); // shoot battleship body
    assertTrue(battleship.shootAt(5, 3)); // shoot battleship stern
    assertTrue(battleship.shootAt(6, 3));
    assertTrue(battleship.shootAt(7, 3));
    assertTrue(battleship.shootAt(8, 3));
    assertTrue(battleship.shootAt(9, 3));
    assertFalse("shooting the same spot after sinking should return false",
        battleship.shootAt(2, 3));
    assertFalse(battleship.shootAt(8, 8)); // miss

    submarine.placeShipAt(0, 0, false, ocean1);
    assertTrue(submarine.shootAt(0, 0));
    assertTrue(submarine.getHit()[0]);

    emptySea.placeShipAt(9, 9, false, ocean1);
    assertFalse(emptySea.getHit()[0]);
    assertFalse(emptySea.shootAt(9, 9));
    assertTrue(submarine.getHit()[0]);
    assertFalse(emptySea.shootAt(9, 9));

    EmptySea anotherEmptySea = new EmptySea();
    anotherEmptySea.placeShipAt(8, 8, true, ocean1);
    assertFalse(anotherEmptySea.getHit()[0]);
    assertFalse(anotherEmptySea.shootAt(8, 8));
  }

  @Test
  public void testIsSunk() {
    destroyer.placeShipAt(2, 3, false, ocean1);
    assertFalse(destroyer.isSunk());
    destroyer.shootAt(2, 3);
    assertFalse(destroyer.isSunk());
    destroyer.shootAt(3, 3);
    assertFalse(destroyer.isSunk());
    destroyer.shootAt(4, 3);
    assertFalse(destroyer.isSunk());
    destroyer.shootAt(5, 3);
    assertTrue(destroyer.isSunk());

    submarine.placeShipAt(0, 0, false, ocean1);
    assertFalse(submarine.isSunk());
    submarine.shootAt(0, 0);
    assertFalse(submarine.isSunk());
  }

//  @Test
//  public void testToString() {
//    battleship.placeShipAt(2, 3, false, ocean1);
//    battleship.shootAt(2, 3);
//    assertEquals(".", ocean1.getShipArray()[9][9].toString());
//    ocean1.getShipArray()[9][9].shootAt(9, 9);
//    assertEquals("-", ocean1.getShipArray()[9][9].toString());
//    assertEquals("S", battleship.toString());
//    battleship.shootAt(3, 3);
//    battleship.shootAt(4, 3);
//    assertEquals("S", battleship.toString());
//    battleship.shootAt(5, 3);
//    battleship.shootAt(6, 3);
//    battleship.shootAt(7, 3);
//    battleship.shootAt(8, 3);
//    battleship.shootAt(9, 3);
//    assertEquals("x", battleship.toString());
//  }

//  @Test
//  public void testShipSubclasses() {
//
//    BattleShip battleship = new BattleShip();
//    assertEquals(8, battleship.getLength());
//    assertEquals("battleship", battleship.getShipType());
//    assertEquals(8, battleship.getHit().length);
//
//    Cruiser cruiser = new Cruiser();
//    assertEquals(6, cruiser.getLength());
//    assertEquals("cruiser", cruiser.getShipType());
//    // assertEquals(6, cruiser.getHit().length);
//
//    Destroyer destroyer = new Destroyer();
//    assertEquals(4, destroyer.getLength());
//    assertEquals("destroyer", destroyer.getShipType());
//    // assertEquals(4, destroyer.getHit().length);
//
//    Submarine submarine = new Submarine();
//    assertEquals(3, submarine.getLength());
//    assertEquals("submarine", submarine.getShipType());
//    // assertEquals(3, submarine.getHit().length);
//
//    EmptySea emptySea = new EmptySea();
//    assertEquals(1, emptySea.getLength());
//    assertEquals("empty", emptySea.getShipType());
//    // assertEquals(1, emptySea.getHit().length);
//    assertFalse(emptySea.isSunk());
//
//  }
//
//  @Test
//  public void testOcean() {
//    assertTrue("checking that the ocean variable is an instance of the Ocean class ",
//        ocean instanceof Ocean);
//    assertEquals("The top left corner was expected to be empty ",
//        ocean.getShipArray()[0][0].getShipType(),
//        "empty");
//    assertEquals("The bottom right corner was expect to be empty ",
//        ocean.getShipArray()[19][19].getShipType(),
//        "empty");
//    assertEquals("The hit count to begin with was not 0 ", 0, ocean.getHitCount());
//    assertEquals("The num of ships sunk to begin with was not 0", 0, ocean.getShipsSunk());
//    assertEquals("The num of shots fired to begin with was not 0 ", 0, ocean.getShotsFired());
//    assertEquals("The num of rows is not 20 ", 20, ocean.getShipArray().length);
//    assertEquals("The num of cols is not 20 ", 20, ocean.getShipArray()[0].length);
//  }
//
//  @Test
//  public void testPlaceAllShipsRandomly() {
//    int numEmptySeaBefore = 0;
//    int numBattleship = 0;
//    int numCruiser = 0;
//    int numDestroyer = 0;
//    int numSubmarine = 0;
//    int numEmptySeaAfter = 0;
//
//    for (int i = 0; i < 20; i++) {
//      for (int j = 0; j < 20; j++) {
//        if (ocean.getShipArray()[i][j].getShipType().equals("empty")) {
//          numEmptySeaBefore = numEmptySeaBefore + 1;
//        }
//      }
//    }
//    assertEquals(400, numEmptySeaBefore);
//
//    ocean.placeAllShipsRandomly();
//
//    for (int i = 0; i < 20; i++) {
//      for (int j = 0; j < 20; j++) {
//        switch (ocean.getShipArray()[i][j].getShipType()) {
//          case "battleship":
//            numBattleship = numBattleship + 1;
//            break;
//          case "cruiser":
//            numCruiser = numCruiser + 1;
//            break;
//          case "destroyer":
//            numDestroyer = numDestroyer + 1;
//            break;
//          case "submarine":
//            numSubmarine = numSubmarine + 1;
//            break;
//          case "empty":
//            numEmptySeaAfter = numEmptySeaAfter + 1;
//            break;
//          default:
//            break;
//        }
//      }
//    }
//    assertEquals(8, numBattleship);
//    assertEquals(12, numCruiser);
//    assertEquals(12, numDestroyer);
//    assertEquals(12, numSubmarine);
//    assertEquals(339, numEmptySeaAfter);
//  }
//
//  @Test
//  public void testIsOccupied() {
//    new BattleShip().placeShipAt(2, 3, false, ocean);
//    new Destroyer().placeShipAt(1, 0, false, ocean);
//    new Cruiser().placeShipAt(11, 2, true, ocean);
//    new Submarine().placeShipAt(1, 9, true, ocean);
//    assertTrue(ocean.isOccupied(1, 9)); // occupied by submarine
//    assertTrue(ocean.isOccupied(5, 3)); // occupied by battleship
//    assertTrue(ocean.isOccupied(11, 2)); // occupied by cruiser
//    assertTrue(ocean.isOccupied(11, 3)); // occupied by cruiser
//    assertTrue(ocean.isOccupied(11, 4)); // occupied by cruiser
//    assertTrue(ocean.isOccupied(11, 5));
//    assertTrue(ocean.isOccupied(11, 6));
//    assertTrue(ocean.isOccupied(11, 7));
//    assertFalse(ocean.isOccupied(11, 8));
//    assertFalse(ocean.isOccupied(0, 0));
//    assertFalse(ocean.isOccupied(9, 9));
//    new EmptySea().placeShipAt(9, 9, true, ocean);
//    assertFalse(ocean.isOccupied(9, 9)); // occupied by empty is not
//    // really occupied
//  }
//
//  @Test
//  public void testShootAtOcean() {
//    new Destroyer().placeShipAt(1, 0, false, ocean);
//    new Submarine().placeShipAt(1, 9, true, ocean);
//    assertFalse(ocean.shootAt(0, 0)); // shoot at emptySea
//    assertTrue(ocean.shootAt(1, 0)); // shoot at destroyer bow
//    assertEquals(2, ocean.getShotsFired());
//    assertEquals(1, ocean.getHitCount());
//    assertTrue(ocean.shootAt(1, 0)); // shoot at destroyer bow
//    assertEquals(3, ocean.getShotsFired());
//    assertEquals(ocean.getHitCount(), 2);
//    assertFalse(ocean.shootAt(0, 0)); // shoot at emptySea
//    assertEquals(4, ocean.getShotsFired());
//    assertEquals(2, ocean.getHitCount());
//    assertTrue(ocean.shootAt(4, 0)); // shoot at destroyer stern
//    assertEquals(3, ocean.getHitCount());
//    assertTrue(ocean.shootAt(2, 0));
//    assertTrue(ocean.shootAt(3, 0));
//    assertFalse(ocean.shootAt(1, 0)); // shoot at destroyer bow after
//    // sink
//    assertEquals(8, ocean.getShotsFired());
//    assertEquals(5, ocean.getHitCount());
//
//    assertTrue(ocean.shootAt(1, 9)); // shoot at submarine
//    assertEquals(9, ocean.getShotsFired());
//    assertEquals(6, ocean.getHitCount());
//
//    new EmptySea().placeShipAt(9, 9, true, ocean);
//    assertFalse(ocean.shootAt(9, 9)); // shoot at empty sea
//    assertEquals(10, ocean.getShotsFired());
//    assertEquals(6, ocean.getHitCount());
//  }
//
//  @Test
//  public void testIsGameOver() {
//
//    // place subs
//    new Submarine().placeShipAt(0, 1, true, ocean);
//    new Submarine().placeShipAt(16, 1, true, ocean);
//    new Submarine().placeShipAt(14, 1, true, ocean);
//    new Submarine().placeShipAt(12, 1, true, ocean);
//
//    // place destroyers
//    new Destroyer().placeShipAt(3, 10, true, ocean);
//    new Destroyer().placeShipAt(5, 10, true, ocean);
//    new Destroyer().placeShipAt(7, 10, true, ocean);
//
//    // light cruisers
//    new LightCruiser().placeShipAt(9, 10, true, ocean);
//    new LightCruiser().placeShipAt(15, 10, true, ocean);
//
//    // cruisers
//    new Cruiser().placeShipAt(17, 13, true, ocean);
//    new Cruiser().placeShipAt(19, 13, true, ocean);
//
//    // battlecruiser
//    new LightCruiser().placeShipAt(0, 19, false, ocean);
//
//    // battleship
//    new BattleShip().placeShipAt(2, 3, false, ocean);
//
//    // Shoot through the first 10 rows
//    assertFalse(ocean.isGameOver());
//    for (int i = 0; i < 10; i++) {
//      for (int j = 0; j < 20; j++) {
//        ocean.shootAt(i, j);
//      }
//    }
//    // Shoot through the rest 10 rows
//    assertFalse(ocean.isGameOver());
//    for (int i = 10; i < 20; i++) {
//      for (int j = 0; j < 20; j++) {
//        ocean.shootAt(i, j);
//      }
//    }
//
//    assertTrue(ocean.isGameOver());

  }
//}