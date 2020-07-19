import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class SquarelotronTest {

  private Squarelotron matrix;

  @Before
  public void setUp() {
    matrix = new Squarelotron(4);
  }

  @Test
  public void testConstructorSquarlotron() {
    int expectedSize = 4;
    int[][] expectedSquarlotron = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    int actualSize = matrix.size;
    int[][] actualSquarlotron = matrix.squarelotron;

    assertEquals("Expected size of Squarelotron: " + expectedSize + ", but actual size: " + actualSize,
        expectedSize, actualSize);
    assertArrayEquals("Expected Squarelotron: " + Arrays.deepToString(expectedSquarlotron)
            + ", but actual Squarelotron: " + Arrays.deepToString(actualSquarlotron),
        expectedSquarlotron, actualSquarlotron);
  }

  @Test
  public void testGetRing1() {
    ArrayList<Integer> expectedRing = new ArrayList<>(Arrays.asList(1,2,3,4,5,8,9,12,13,14,15,16));
    ArrayList<Integer> actualRing = matrix.getRing(1);

    assertEquals(expectedRing, actualRing);
  }

  @Test
  public void testGetRing2() {
    ArrayList<Integer> expectedRing = new ArrayList<>(Arrays.asList(6,7,10,11));
    ArrayList<Integer> actualRing = matrix.getRing(2);

    assertEquals(expectedRing, actualRing);
  }

  @Test
  public void testCenterRing() {
    Squarelotron matrix2 = new Squarelotron(5);
    ArrayList<Integer> expectedRing = new ArrayList<>(Arrays.asList(13));
    ArrayList<Integer> actualRing = matrix2.getRing(3);

    assertEquals(expectedRing, actualRing);
  }

  @Test
  public void testUpsideDownFlipRing1() {
    int[][] expectedSquarlotron = {{13, 14, 15, 16}, {9, 6, 7, 12}, {5, 10, 11, 8}, {1, 2, 3, 4}};
    Squarelotron actualSquarlotron = matrix.upsideDownFlip(1);
    assertArrayEquals(expectedSquarlotron, actualSquarlotron.squarelotron);
  }

  @Test
  public void testUpsideDownFlipRing2() {
    int[][] expectedSquarlotron = {{1, 2, 3, 4}, {5, 10, 11, 8}, {9, 6, 7, 12}, {13, 14, 15, 16}};
    Squarelotron actualSquarlotron = matrix.upsideDownFlip(2);
    assertArrayEquals(expectedSquarlotron, actualSquarlotron.squarelotron);
  }

  @Test
  public void testMainDiagonalFlipRing1() {
    int[][] expectedSquarlotron = {{1, 5, 9, 13}, {2, 6, 7, 14}, {3, 10, 11, 15}, {4, 8, 12, 16}};
    Squarelotron actualSquarlotron = matrix.mainDiagonalFlip(1);
    assertArrayEquals(expectedSquarlotron, actualSquarlotron.squarelotron);
  }

  @Test
  public void testMainDiagonalFlipRing2() {
    int[][] expectedSquarlotron = {{1, 2, 3, 4}, {5, 6, 10, 8}, {9, 7, 11, 12}, {13, 14, 15, 16}};
    Squarelotron actualSquarlotron = matrix.mainDiagonalFlip(2);
    assertArrayEquals(expectedSquarlotron, actualSquarlotron.squarelotron);
  }

}