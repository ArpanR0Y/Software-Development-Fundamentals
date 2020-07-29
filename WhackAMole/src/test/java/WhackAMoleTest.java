import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class WhackAMoleTest {

  private WhackAMole whack;

  @Before
  public void setUp() {
    whack = new WhackAMole(5, 3);
  }

  @Test
  public void testConstructorNumOfAttempts() {
    int expected = 2;
    int actual = whack.attemptsLeft;
    assertEquals("Expected number of attempts: " + expected + ", but actual: " + actual, expected,
        actual);
  }

  @Test
  public void testConstructorGrid() {
    int expectedRow = 3;
    int actualRow = whack.moleGrid.length;
    assertEquals("Expected number of rows: " + expectedRow + ", but actual: " + actualRow,
        expectedRow, actualRow);
    for (int i = 0; i < 3; i++) {
      int expectedCol = 3;
      int actualCol = whack.moleGrid[i].length;
      assertEquals("Expected number of columns for row " + i + ": " + expectedCol + ", but "
          + "actual: " + actualCol, expectedCol, actualCol);
      for (int j = 0; j < 3; j++) {
        char expectedChar = '*';
        char actualChar = whack.moleGrid[i][j];
        assertEquals(
            "Expected char at row " + i + " column " + j + " is " + expectedChar + ", but "
                + "actual " + actualChar, expectedChar, actualChar);
      }
    }
  }

  @Test
  public void testPlaceSucceed() {
    assertTrue(whack.place(1, 1));
    assertTrue(whack.place(1, 2));
    int expectedNumMoles = 2;
    int actualNumMoles = whack.molesLeft;
    assertEquals("Expected number of moles: " + expectedNumMoles + ", but "
        + "actual: " + actualNumMoles, expectedNumMoles, actualNumMoles);
    char expectedOneOne = 'M';
    char actualOneOne = whack.moleGrid[1][1];
    assertEquals("Expected char at (1, 1): M, but actual: " + actualOneOne, expectedOneOne,
        actualOneOne);
    assertEquals("Expected char at (1, 2): M, but actual: " + whack.moleGrid[1][2], 'M',
        whack.moleGrid[1][2]);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (!((i == 1 && j == 1) || (i == 1 && j == 2))) {
          assertEquals("Mole placed at wrong place: (" + i + ", " + j + ")", '*',
              whack.moleGrid[i][j]);
        }
      }
    }
  }

  @Test
  public void testPlaceFail() {
    assertTrue(whack.place(1, 1));
    assertFalse(whack.place(1, 1));
    assertEquals("(1, 1) doesn't have a mole", 'M', whack.moleGrid[1][1]);
  }

  @Test
  public void testWhackSucceedAttempts() {
    whack.place(1, 1);
    assertEquals(
        "Expected number of attemptsLeft before whack: 5, but actual: " + whack.attemptsLeft, 5,
        whack.attemptsLeft);
    whack.whack(1, 1);
    assertEquals("Expected number of attempts after whack: 4, but actual: " + whack.attemptsLeft,
        4, whack.attemptsLeft);
  }

  @Test
  public void testWhackFailAttempts() {
    whack.place(1, 1);
    assertEquals(
        "Expected number of attemptsLeft before whack: 5, but actual: " + whack.attemptsLeft, 5,
        whack.attemptsLeft);
    whack.whack(0, 0);
    assertEquals("Expected number of attempts after whack: 4, but actual: " + whack.attemptsLeft,
        4, whack.attemptsLeft);
  }

  @Test
  public void testWhackSucceedMolesLeft() {
    whack.place(1, 1);
    assertEquals("Expected number of moles left before whack: 1, but actual: " + whack.molesLeft,
        1, whack.molesLeft);
    whack.whack(1, 1);
    assertEquals(
        "Expected number of moles left after successful whack: 0, but actual: " + whack.molesLeft,
        0, whack.molesLeft);
  }

  @Test
  public void testWhackFailMolesLeft() {
    whack.place(1, 1);
    assertEquals("Expected number of moles left before whack: 1, but actual: " + whack.molesLeft,
        1, whack.molesLeft);
    whack.whack(0, 0);
    assertEquals(
        "Expected number of moles left after failed whack: 1, but actual: " + whack.molesLeft, 1,
        whack.molesLeft);
  }

}