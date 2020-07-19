import static org.junit.Assert.*;

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
  public void testUpsideDownFlipRing1() {
    int[][] expectedSquarlotron = {{13, 14, 15, 16}, {9, 6, 7, 12}, {5, 10, 11, 8}, {1, 2, 3, 4}};
    Squarelotron actualSquarlotron = matrix.upsideDownFlip(1);
    assertArrayEquals("Expected Squarelotron: " + Arrays.deepToString(expectedSquarlotron)
            + ", but actual Squarelotron: " + Arrays.deepToString(actualSquarlotron.squarelotron),
        expectedSquarlotron, actualSquarlotron.squarelotron);
  }

}