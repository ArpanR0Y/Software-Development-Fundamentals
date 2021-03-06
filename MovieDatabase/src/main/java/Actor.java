import java.util.ArrayList;

public class Actor {

  private String name;
  private final ArrayList<Movie> movies;

  public Actor() {
    this.name = "";
    this.movies = new ArrayList<>();
  }

  public String getName() {
    return name.trim();
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<Movie> getMovies() {
    return movies;
  }

  public void setMovies(Movie movie) {
    this.movies.add(movie);
  }
}
