import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class MovieDatabaseTest {

  MovieDatabase db;

  @Before
  public void setUp() {
    db = new MovieDatabase();
  }

  @Test
  public void testMovieDatabase() {
    assertNotNull(db.getActorList());
    assertNotNull(db.getMovieList());
    assertEquals(0, db.getActorList().size());
    assertEquals(0, db.getMovieList().size());
  }

  @Test
  public void testAddMovieMovie() {
    db.addMovie("life", new String[]{"actor1", "actor2"});
    for (Movie m : db.getMovieList()) {
      if (m.getName().equals("life")) {
        assertTrue(true);
        return;
      }
    }
    fail();
  }

  @Test
  public void testAddMovieActor() {
    db.addMovie("life", new String[]{"superman"});
    for (Actor a : db.getActorList()) {
      if (a.getName().equals("superman")) {
        assertTrue(true);
        return;
      }
    }
    fail();
  }

  @Test
  public void testAddMovieAlreadyExist() {
    db.addMovie("dummy", new String[]{});
    db.addMovie("dummy", new String[]{});
    assertEquals(1, db.getMovieList().size());
  }

  @Test
  public void testAddMovieActorExist() {
    Actor a = new Actor();
    a.setName("bla");
    db.getActorList().add(a);
    db.addMovie("life", new String[]{"bla"});
    assertEquals(1, db.getActorList().size());
  }

  @Test
  public void testAddRating() {
    db.addMovie("life", new String[]{"suki"});
    db.addRating("life", 9.9);
    double rating = db.getMovieList().get(0).getRating();
    assertEquals(9.9, rating, 0.0001);
  }

  @Test
  public void testUpdateRating() {
    db.addMovie("life", new String[]{"suki"});
    db.addRating("life", 9.9);
    double rating = db.getMovieList().get(0).getRating();
    assertEquals(9.9, rating, 0.000001);
    db.updateRating("life", 101.101);
    rating = db.getMovieList().get(0).getRating();
    assertEquals(101.101, rating, 0.000001);
  }

  /**
   * Setup the database state in order to test getBestActor and getBestMovie
   */
  private void setup() {
    db.addMovie("bestMovie", new String[]{"a1", "a2", "a3"});
    db.addRating("bestMovie", 10);
    db.addMovie("usualMovie", new String[]{"a1", "a2", "a3", "a4"});
    db.addRating("usualMovie", 7);
    db.addMovie("worstMovie", new String[]{"a3", "a4"});
    db.addRating("worstMovie", 3);
  }

  @Test
  public void testGetBestActor() {
    setup();
    String ans = db.getBestActor();
    assertTrue("a1".equals(ans) || "a2".equals(ans));
		assertEquals("a1", db.getBestActor());
  }

  @Test
  public void testGetBestMovie() {
    setup();
    assertEquals("bestMovie", db.getBestMovie());
  }
}
