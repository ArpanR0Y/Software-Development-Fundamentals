import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieDatabase {

  private ArrayList<Movie> movieList;
  private ArrayList<Actor> actorList;

  public MovieDatabase() {
    this.movieList = new ArrayList<>();
    this.actorList = new ArrayList<>();
  }

  public ArrayList<Movie> getMovieList() {
    return movieList;
  }

  public ArrayList<Actor> getActorList() {
    return actorList;
  }

  /**
   * This method takes in the name of a movie that is not currently in the database along with a
   * list of actors for that movie. If the movie is already in the database, the code ignores this
   * request (this specification is an oversimplification). If the movie is a new movie, a movie
   * object is created and added to the movieList. If any of the actors happen to be new, they will
   * be added to the actorList.
   *
   * @param name   Name of the movie to be added.
   * @param actors Name of the actor to be added.
   */
  public void addMovie(String name, String[] actors) {
    if (movieList.isEmpty() || movieList.stream()
        .noneMatch(movie -> movie.getName().equals(name))) {
      Movie newMovie = new Movie(name, actors);
      movieList.add(newMovie);
      for (String actor : actors) {
        if (actorList.stream().noneMatch(actor1 -> actor1.getName().equals(actor))) {
          Actor newActor = new Actor();
          newActor.setName(actor);
          newActor.setMovies(newMovie);
          actorList.add(newActor);
        }
      }
    }
  }

  /**
   * Add a rating for this movie. Assume that the name argument will definitely be a name that is
   * currently in the database.
   *
   * @param name   Name of the movie in the Database.
   * @param rating Rating for the movie to be added.
   */
  public void addRating(String name, double rating) {
    for (Movie movie : movieList) {
      if (movie.getName().equals(name)) {
        movie.setRating(rating);
      }
    }
  }

  /**
   * Overwrite the current rating of a movie with this new rating. Again, assume that the name
   * argument will definitely be a name that is currently in the database.
   *
   * @param name      Name of the movie in the Database.
   * @param newRating New rating for the movie, overriding the existing one.
   */
  public void updateRating(String name, double newRating) {
    addRating(name, newRating);
  }

  /**
   * Returns the name of the actor that has the best average rating for their movies.
   *
   * @return The name of the best actor based on the movie ratings.
   */
  public String getBestActor() {
    double maxRating = 0.0;
    String bestActor = "";
    for (Actor actor : actorList) {
      double sumRating = 0.0;
      ArrayList<Movie> actorMovies = actor.getMovies();
      for (Movie movie : actorMovies) {
        sumRating += movie.getRating();
      }
      double avgRating = sumRating / actorMovies.size();
      if (avgRating > maxRating) {
        maxRating = avgRating;
        bestActor = actor.getName();
      }
    }

    return bestActor;
  }

  /**
   * Returns the name of the movie with the highest rating.
   *
   * @return The name of the highest rated movie.
   */
  public String getBestMovie() {
    String bestMovie = "";
    double highestRating = 0.0;
    for (Movie movie : movieList) {
      if (movie.getRating() > highestRating) {
        bestMovie = movie.getName();
        highestRating = movie.getRating();
      }
    }
    return bestMovie;
  }

  /**
   * This method takes in the movieDatabase object and populates its list of actors with the name
   * of the actors and the name of the movies they have acted in from the csv dataset
   * @throws FileNotFoundException Throws exception when dataset is not found in the resources.
   */
  public void actorDataLoader() throws FileNotFoundException {

    String projectPath = Paths.get(".").normalize().toAbsolutePath().toString();
    Scanner sc = new Scanner(new File(projectPath + "\\src\\main\\resources\\movies.txt"));

    while (sc.hasNext()) {
      String line = sc.nextLine();

      String actor = line.substring(0, line.indexOf(","));
      String movies = line.substring(line.indexOf(",") + 1);

      //Create a new Actor object
      Actor newActor = new Actor();
      newActor.setName(actor);

      //Add all the movies the actor has acted in Actor.movies
      String[] movieArr = movies.split(",");

      for (String movie : movieArr) {
        Movie newMovie = new Movie(movie);
        newActor.setMovies(newMovie);
      }

      this.actorList.add(newActor);
    }
    sc.close();
  }

  /**
   * This method takes in the movieDatabase object and populates its list of movies name, ratings and
   * the name of the actors from the dataset.
   * @throws FileNotFoundException Throws exception when dataset is not found in the resources.
   */
  public void movieDataLoader() throws FileNotFoundException {

    String projectPath = Paths.get(".").normalize().toAbsolutePath().toString();
    Scanner sc = new Scanner(new File(projectPath + "\\src\\main\\resources\\ratings.txt"));

    /*
    Regex pattern to separate the name of the movie from the ratings
    1st group - Captures everything 1 or more times
    2nd group - Captures a whitespace and 2 or more digits at the end of the string
     */
    String regex = "(.+)(\\t+\\d{2,}$)";
    Pattern pattern = Pattern.compile(regex);

    while (sc.hasNext()) {
      String line = sc.nextLine();
      Matcher matcher = pattern.matcher(line);
      String movieName = "";
      double rating = 0.0;

      if (matcher.find()) {
        movieName = matcher.group(1);
        rating = Double.parseDouble(matcher.group(2));
      }

      //Create a new Movie object
      Movie movie = new Movie(movieName, rating);

      for (Actor actor : actorList) {
        String finalMovieName = movieName;
        if (actor.getMovies().stream().anyMatch(actorMovie -> actorMovie.getName().equals(finalMovieName))) {
          movie.setActors(actor);
        }
      }

      this.movieList.add(movie);
    }
    sc.close();
  }

  public static void main(String[] args) throws FileNotFoundException {
    //TODO: 1. You create a new instance of a movieDatabase.
    //      2. Add all the movies in the file movies.txt.
    //      3. Go through the ratings of the movies in the file ratings.txt and add the ratings for
    //         the movies.
    //      4. Now call the methods that you created and print out the name of the best actor and the
    //         name of the highest rated movie.
    MovieDatabase movieDatabase = new MovieDatabase();
    movieDatabase.actorDataLoader();
    movieDatabase.movieDataLoader();

    int n = 0;
    while (n < 10) {
//      Actor actor = movieDatabase.actorList.get(n);
      Movie movie = movieDatabase.movieList.get(n);
      System.out.println("Movie Name: " + movie.getName());
      System.out.println("Movie Actor: " + movie.getActors().get(0).getName());
      System.out.println("Movie Rating: " + movie.getRating());
      n++;
    }
  }
}
